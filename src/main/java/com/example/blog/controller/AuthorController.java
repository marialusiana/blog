package com.example.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import javassist.NotFoundException;

import com.example.blog.model.Author;
import com.example.blog.common.dto.AuthorDTO;
import com.example.blog.common.dto.AuthorPasswordDTO;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.AuthorRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.ResponseAuthorDTO;
import com.example.blog.common.dto.response.ResponseOauthDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.service.AuthorService;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @Autowired
	private DataSource dataSource;

	@Autowired
    private ClientDetailsService clientDetailsStore;

    @Autowired
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
	}

    @Autowired
	private AuthenticationManager authenticationManager;

    @GetMapping(value = "/authors")
    public BaseResponseDTO<MyPage<ResponseAuthorDTO>> listAuthor(
        MyPageable pageable, @RequestParam(required = false) String param, HttpServletRequest request
    ) { 
       Page<ResponseAuthorDTO> author;

       if (param != null) {
           author = authorService.findByName(MyPageable.convertToPageable(pageable), param);
       } else {
           author = authorService.findAll(MyPageable.convertToPageable(pageable));
       }

       PageConverter<ResponseAuthorDTO> converter = new PageConverter<>();
       String url = String.format("%s://%s:%d/author",request.getScheme(),  request.getServerName(), request.getServerPort());

       String search = "";

       if(param != null){
           search += "&param="+param;
       }

       MyPage<ResponseAuthorDTO> response = converter.convert(author, url, search);

       return BaseResponseDTO.ok(response);
    }

    @GetMapping(value = "/authors/{id}")
    public BaseResponseDTO<ResponseAuthorDTO> getOne(@PathVariable Integer id) {
        return BaseResponseDTO.ok(authorService.findById(id));
    }

    @DeleteMapping(value = "/authors")
    public BaseResponseDTO deleteAuthor(@Valid @RequestBody Author author) {
        
       return BaseResponseDTO.ok(authorService.deleteById(author.getId()));
    }

    @PostMapping(value = "/authors")
    public BaseResponseDTO createAuthor(@Valid @RequestBody AuthorRequest request) {
        return BaseResponseDTO.ok(authorService.save(request));
    }

    @PutMapping(value = "/authors/{id}")
    public BaseResponseDTO updateAuthor(
         @Valid @RequestBody AuthorDTO request, @PathVariable("id") Integer id
    ) {
       authorService.update(id, request);
       return BaseResponseDTO.ok(authorService.update(id, request));
    }

    @PutMapping("/authors/{id}/password")
    public BaseResponseDTO updatePasswordAuthor(
         @Valid @RequestBody AuthorPasswordDTO request, @PathVariable("id") Integer id
    ) {
       authorService.updatePassword(id, request);
       return BaseResponseDTO.ok(authorService.updatePassword(id, request));
    }


    //Normal Login
	@RequestMapping(value="/api/login", method = RequestMethod.POST)
	public  ResponseEntity<ResponseOauthDTO> login(@RequestParam HashMap<String, String> params) throws Exception
	{
		ResponseOauthDTO response = new ResponseOauthDTO();
		Author checkUser =  authorService.findByUsername(params.get("username"));

	    if (checkUser != null)
		{
			try {
				OAuth2AccessToken token = this.getToken(params);
			
				response.setStatus(true);
				response.setCode("200");
				response.setMessage("success");
				response.setData(token);

				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (Exception exception) {
				
                    response.setStatus(false);
                    response.setCode("500");
                    response.setMessage(exception.getMessage());
			}
		} else {
			throw new Exception();
		}
		

		return new ResponseEntity<ResponseOauthDTO>(response, HttpStatus.UNAUTHORIZED);
    }
    
    private OAuth2AccessToken getToken(HashMap<String, String> params) throws HttpRequestMethodNotSupportedException {
		if (params.get("username") == null ) {
			throw new UsernameNotFoundException("username not found");
		}

		if (params.get("password") == null) {
			throw new UsernameNotFoundException("password not found");
		}

		if (params.get("client_id") == null) {
			throw new UsernameNotFoundException("client_id not found");
		}

		if (params.get("client_secret") == null) {
			throw new UsernameNotFoundException("client_secret not found");
		}

		DefaultOAuth2RequestFactory defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetailsStore);

		AuthorizationRequest authorizationRequest = defaultOAuth2RequestFactory.createAuthorizationRequest(params);
		authorizationRequest.setApproved(true);

		OAuth2Request oauth2Request = defaultOAuth2RequestFactory.createOAuth2Request(authorizationRequest);
		
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				params.get("username"), params.get("password"));
		Authentication authentication = authenticationManager.authenticate(loginToken);

		OAuth2Authentication authenticationRequest = new OAuth2Authentication(oauth2Request, authentication);
		authenticationRequest.setAuthenticated(true);

		OAuth2AccessToken token = tokenServices().createAccessToken(authenticationRequest);


		return token;
    } 
    
    @Autowired
	public AuthorizationServerTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setAccessTokenValiditySeconds(-1);

		defaultTokenServices.setTokenStore(tokenStore());
		return defaultTokenServices;
	}

}
