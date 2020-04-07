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
import com.example.blog.model.Blog;
import com.example.blog.model.Categories;
import com.example.blog.model.Tags;
import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.request.BlogRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.common.dto.response.ResponseBlogDTO;
import com.example.blog.common.dto.response.ResponseTagsDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.AuthorService;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.CategoriesRepository;

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


    @GetMapping("/posts")
    public BaseResponseDTO<MyPage<ResponseBlogDTO>> listBlog(
        MyPageable pageable, @RequestParam(required = false, name="title") String title, HttpServletRequest request,
        @RequestParam(required = false, name = "category_id") Integer categories_id,
        @RequestParam(required = false, name = "author_id") Integer author_id) {

            Page<ResponseBlogDTO> blog;

            if (title != null) {
                blog = BlogService.findByName(MyPageable.convertToPageable(pageable), title);
            }else if (categories_id != null){
                blog = BlogService.findByCategoriesId(MyPageable.convertToPageable(pageable), categories_id);
            }else if (author_id != null){
                blog = BlogService.findByAuthorId(MyPageable.convertToPageable(pageable), author_id);
            }
            else {
                blog = BlogService.findAll(MyPageable.convertToPageable(pageable));
            }
     
            PageConverter<ResponseBlogDTO> converter = new PageConverter<>();
            String url = String.format("%s://%s:%d/posts",request.getScheme(),  request.getServerName(), request.getServerPort());
     
            String search = "";
     
            if(title != null){
                search += "&param="+title;
            }
     
            MyPage<ResponseBlogDTO> response = converter.convert(blog, url, search);
     
            return BaseResponseDTO.ok(response);
        
    }

    @PostMapping(value = "/posts2")
    public BaseResponseDTO createTag(@Valid @RequestBody BlogDTO request) {
        return BaseResponseDTO.ok(BlogService.save2(request));
    }

    @PostMapping("/posts")
	public ResponseEntity<ResponseBaseDTO<Blog>> createBlog(@RequestBody BlogDTO blog) {
        ResponseBaseDTO<Blog> response = new ResponseBaseDTO<Blog>(); 
        try
        {         
         Blog blogSaved = BlogService.save(blog);
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
        //  response.setData("");         
         
         return new ResponseEntity<>(response ,HttpStatus.OK); 

        }catch(Exception e)
        {
         // catch error when get user
         response.setStatus(false);
         response.setCode("500");
         response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/posts")
    public BaseResponseDTO<BlogResponse> deleteBlog(@RequestBody BlogDeleteRequest request) {
        return BlogService.delete(request);
    }


    @PutMapping("/posts/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") Integer id, @RequestBody BlogDTO blog) {
		Optional<Blog> BlogData = BlogService.findById(id);

		if (BlogData.isPresent()) {
			Blog _blog = BlogData.get();
            _blog.setTitle(blog.getTitle());
            _blog.setContent(blog.getContent());
            _blog.setAuthor(blog.getAuthor());
            _blog.setCategories(blog.getCategories());
			return new ResponseEntity<>(BlogService.update(_blog), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PutMapping(value = "/posts2/{id}")
    public BaseResponseDTO UpdateBlog2(@PathVariable("id") Integer id, @Valid @RequestBody BlogDTO request) {
        return BaseResponseDTO.ok( BlogService.update2(request, id));
    }

}