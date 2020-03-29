package com.example.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.blog.model.Blog;
import com.example.blog.model.Tags;
import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogService BlogService;

    @GetMapping("/blog")
    public ResponseEntity<ResponseBaseDTO<Iterable<Blog>>> listBlog(){ 
        ResponseBaseDTO<Iterable<Blog>> response = new ResponseBaseDTO<Iterable<Blog>>(); 
        try
        {        
            Iterable<Blog> blogList = BlogService.findAll();
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(blogList);         
            
            return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
            // catch error when get user
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    // @GetMapping("/posts")
    // public ResponseEntity<ResponseBaseDTO<Iterable<Blog>>> listPosts(
    //     @RequestParam(required = false, name = "categoryId") Integer category_id,
    //     @RequestParam(required = false, name = "title") String title) {
        
    //     ResponseBaseDTO<Iterable<Blog>> response = new ResponseBaseDTO<Iterable<Blog>>(); 
    //     // Filtering
    //     if (category_id != null) {
    //         Iterable<Blog> blogList =  BlogService.findPostByCategoriesId(category_id);

    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(blogList);
    
    //         return new ResponseEntity<>(response ,HttpStatus.OK);
    //     }
    //     if (title != null) {
    //         // TODO validate length
    //         Iterable<Blog> blogList =  BlogService.findByTitle(title);

    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(blogList);
    
    //         return new ResponseEntity<>(response ,HttpStatus.OK);
    //     }

    //     Iterable<Blog> blogList = BlogService.findAll();
    //     response.setStatus(true);
    //     response.setCode("200");
    //     response.setMessage("success");
    //     response.setData(blogList);

    //     return new ResponseEntity<>(response ,HttpStatus.OK);
    // }


    @PostMapping("/blog")
	public ResponseEntity<ResponseBaseDTO<Blog>> createBlog(@RequestBody BlogDTO blog) {
        ResponseBaseDTO<Blog> response = new ResponseBaseDTO<Blog>(); 
        try
        {         
         Blog blogSaved = BlogService.save(blog);
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(blogSaved);         
         
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

}