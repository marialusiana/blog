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
import com.example.blog.model.Comment;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService CommentService;

    @GetMapping("/comment")
    public ResponseEntity<ResponseBaseDTO<Iterable<Comment>>> listComment(){ 
        ResponseBaseDTO<Iterable<Comment>> response = new ResponseBaseDTO<Iterable<Comment>>(); 
        try
        {         
         Iterable<Comment> commentlist = CommentService.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(commentlist);         
         
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

}