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
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.request.BlogRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogService BlogService;

    // @GetMapping("/blog")
    // public ResponseEntity<ResponseBaseDTO<List<Blog>>> listBlog(){ 
    //     ResponseBaseDTO<List<Blog>> response = new ResponseBaseDTO<List<Blog>>(); 
    //     try
    //     {        
    //         List<Blog> blogList = BlogService.findAll();
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(blogList);         
            
    //         return new ResponseEntity<>(response ,HttpStatus.OK);
    //     }
    //     catch(Exception e)
    //     {
    //         // catch error when get user
    //         response.setStatus(false);
    //         response.setCode("500");
    //         response.setMessage(e.getMessage());

    //         return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    //     }
    // }

    @GetMapping("/blog2")
    public BaseResponseDTO<List<Blog>> listBlog(
        @RequestParam(required = false, name = "categoryId") Integer categories_id,
        @RequestParam(required = false, name = "title") String title) {
        
        // Filtering
        if (categories_id != null) {
            return BlogService.findPostByCategoriesId(categories_id);
        }
        if (title != null) {
            // TODO validate length
            return BlogService.findByTitle(title);
        }

        return BlogService.findAll();
    }

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

    @DeleteMapping("/blog")
    public BaseResponseDTO<BlogResponse> deletePost(@RequestBody BlogDeleteRequest request) {
        return BlogService.delete(request);
    }


    @PutMapping("/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") Integer id, @RequestBody BlogDTO blog) {
		Optional<Blog> BlogData = BlogService.findById(id);

		if (BlogData.isPresent()) {
			Blog _blog = BlogData.get();
			_blog.setTitle(blog.getTitle());
			return new ResponseEntity<>(BlogService.update(_blog), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}