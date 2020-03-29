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
import com.example.blog.model.BlogTags;
import com.example.blog.model.Comment;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogTagsService;
import com.example.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class BlogTagsController {

    @Autowired
    BlogTagsService BlogTagsService;

    @GetMapping("/blogTags")
    public ResponseEntity<ResponseBaseDTO<Iterable<BlogTags>>> listBlogTag(){ 
        ResponseBaseDTO<Iterable<BlogTags>> response = new ResponseBaseDTO<Iterable<BlogTags>>(); 
        try
        {         
         Iterable<BlogTags> blogTagsList = BlogTagsService.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(blogTagsList);         
         
         return new ResponseEntity<>(response ,HttpStatus.OK);
        }
        catch(Exception e)
        {
         // catch error when get user
         response.setStatus(false);
         response.setCode("500");
         response.setMessage(e.getMessage());
        }
        
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

    }

    @PostMapping("/bloTags")
	public ResponseEntity<ResponseBaseDTO> createBlogTags(@RequestBody BlogTags blogTag) {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
		try {
             BlogTags _blogTag = BlogTagsService.save(new BlogTags(blogTag.getBlog(), blogTag.getTags()));
            
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(_blogTag);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
    }

    @PutMapping("/blogTags/{id}")
	public ResponseEntity<BlogTags> updateBlogTags(@PathVariable("id") long id, @RequestBody BlogTags blogTag) {
		Optional<BlogTags> blogTagsData = BlogTagsService.findById(id);

		if (blogTagsData.isPresent()) {
			BlogTags _blogTags = blogTagsData.get();
            _blogTags.setBlog(blogTag.getBlog());
            _blogTags.setTags(blogTag.getTags());
			return new ResponseEntity<>(BlogTagsService.save(_blogTags), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/blogTags/{id}")
	public ResponseEntity<HttpStatus> deleteBlogTags(@PathVariable("id") long id) {
		try {
			BlogTagsService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}