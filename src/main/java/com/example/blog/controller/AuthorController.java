package com.example.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping(value = "/author")
    public BaseResponseDTO<MyPage<ResponseAuthorDTO>> listCategories(
        MyPageable pageable, @RequestParam(required = false) String param, HttpServletRequest request
    ) { 
       Page<ResponseAuthorDTO> author;

       if (param != null) {
           author = authorService.findByName(MyPageable.convertToPageable(pageable), param);
       } else {
           author = authorService.findAll(MyPageable.convertToPageable(pageable));
       }

       PageConverter<ResponseAuthorDTO> converter = new PageConverter<>();
       String url = String.format("%s://%s:%d/api/author",request.getScheme(),  request.getServerName(), request.getServerPort());

       String search = "";

       if(param != null){
           search += "&param="+param;
       }

       MyPage<ResponseAuthorDTO> response = converter.convert(author, url, search);

       return BaseResponseDTO.ok(response);
    }

    @GetMapping(value = "/author/{id}")
    public BaseResponseDTO<ResponseAuthorDTO> getOne(@PathVariable Integer id) {
        return BaseResponseDTO.ok(authorService.findById(id));
    }

    @DeleteMapping(value = "/author")
    public BaseResponseDTO deleteAuthor(@Valid @RequestBody Author author) {
        
       return BaseResponseDTO.ok(authorService.deleteById(author.getId()));
    }

    @PostMapping(value = "/author")
    public BaseResponseDTO createAuthor(@Valid @RequestBody AuthorRequest request) {
        return BaseResponseDTO.ok(authorService.save(request));
    }

    @PutMapping(value = "/author/{id}")
    public BaseResponseDTO updateAuthor(
         @Valid @RequestBody AuthorDTO request, @PathVariable("id") Integer id
    ) {
       authorService.update(id, request);
       return BaseResponseDTO.ok(authorService.update(id, request));
    }

    @PutMapping("/{id}/password")
    public BaseResponseDTO updatePasswordAuthor(
         @Valid @RequestBody AuthorPasswordDTO request, @PathVariable("id") Integer id
    ) {
       authorService.updatePassword(id, request);
       return BaseResponseDTO.ok(authorService.updatePassword(id, request));
    }

}
