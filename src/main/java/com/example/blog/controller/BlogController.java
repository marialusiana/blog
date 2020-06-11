package com.example.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.common.dto.response.ResponseBlogDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.model.Author;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.CategoriesRepository;
import com.example.blog.service.roleMenuService;

@RestController
public class BlogController {

    @Autowired
    BlogService BlogService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagService tagService;

    @Autowired
    private roleMenuService roleMenuService;


    @GetMapping("/posts")
    public BaseResponseDTO<MyPage<ResponseBlogDTO>> listBlog(
        MyPageable pageable, @RequestParam(required = false, name="title") String title, HttpServletRequest request,
        @RequestParam(required = false, name = "category_id") Integer categories_id,
        @RequestParam(required = false, name = "author_id") Integer author_id,
        @RequestParam(required = false) String tag_name) {

            boolean roleAccess = roleMenuService.roleAccess("/posts", request.getMethod());

            if(roleAccess ==false){
                return BaseResponseDTO.error("99", "Role anda tidak dapat mengakses menu posts blog");
            }

            Page<ResponseBlogDTO> blog;

            if (title != null) {
                blog = BlogService.search(MyPageable.convertToPageable(pageable), title);
            }else if (categories_id != null){
                blog = BlogService.findByCategories(MyPageable.convertToPageable(pageable), categories_id);
            }else if (author_id != null){
                blog = BlogService.findByAuthor(MyPageable.convertToPageable(pageable), author_id);
            } else if (tag_name != null) {
                blog = BlogService.findByTag(MyPageable.convertToPageable(pageable), tag_name);
            }else {
                blog = BlogService.findAll(MyPageable.convertToPageable(pageable));
            }
     
            PageConverter<ResponseBlogDTO> converter = new PageConverter<>();
            String url = String.format("%s://%s:%d/posts",request.getScheme(),  request.getServerName(), request.getServerPort());
     
            String search = "";
     
            if(title != null) {
                search += "&title="+title;
            } else if (author_id != null) {
                search += "&author_id="+author_id;
            } else if (categories_id != null) {
                search += "&categories_id="+categories_id;
            } else if (tag_name != null) {
                search += "&tag_name="+tag_name;
            }
     
            MyPage<ResponseBlogDTO> response = converter.convert(blog, url, search);
     
            return BaseResponseDTO.ok(response);
        
    }

    @GetMapping(value = "/posts/{id}")
    public BaseResponseDTO<ResponseBlogDTO> getOne(@PathVariable Integer id) {
        return BaseResponseDTO.ok(BlogService.findById(id));
    }

    @PostMapping(value = "/posts", consumes = MediaType.ALL_VALUE)
    public BaseResponseDTO createTag(BlogDTO request ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author userAuth = (Author) auth.getPrincipal();

        Author author = authorRepository.findByUserId(request.getAuthor_id());

        if(!userAuth.getId().equals(author.getId())){
           return BaseResponseDTO.error("404", "hanya bisa create author diri sendiri");
        }

        return BaseResponseDTO.ok(BlogService.save(request));
    }

    @DeleteMapping("/posts")
    public BaseResponseDTO<BlogResponse> deleteBlog(@RequestBody BlogDeleteRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author userAuth = (Author) auth.getPrincipal();
           
        if (!userAuth.getRole().equals("superadmin")) {
            return BaseResponseDTO.error("99", "Hanya Role SuperAdmin yang dapat akses Author");
        }
        return BlogService.delete(request);
    }

    @PutMapping(value = "/posts/{id}")
    public BaseResponseDTO UpdateBlog(@PathVariable("id") Integer id, @Valid @RequestBody BlogDTO request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author userAuth = (Author) auth.getPrincipal();

        Author author = authorRepository.findByUserId(request.getAuthor_id());
        if(!userAuth.getId().equals(author.getId())){
            return BaseResponseDTO.ok( BlogService.update(request, id));
        }else{
            return BaseResponseDTO.error("404", "hanya bisa create author diri sendiri");
        }
    }

}