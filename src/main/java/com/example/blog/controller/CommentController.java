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

import com.example.blog.model.Blog;
import com.example.blog.model.Comment;
import com.example.blog.common.dto.CommentDTO;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.CommentRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.ResponseCommentDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService CommentService;

    @GetMapping(value = "/posts/{id}/comment")
    public BaseResponseDTO<MyPage<ResponseCommentDTO>> listComment(@PathVariable Integer id,
        MyPageable pageable, @RequestParam(required = false) String param, HttpServletRequest request
    ) { 
       Page<ResponseCommentDTO> categories;

       if (param != null) {
           categories = CommentService.findByName(MyPageable.convertToPageable(pageable), param);
       } else {
           categories = CommentService.findAllByBlogId(MyPageable.convertToPageable(pageable), id);
       }

       PageConverter<ResponseCommentDTO> converter = new PageConverter<>();
       String url = String.format("%s://%s:%d/posts/"+id+"/comment",request.getScheme(),  request.getServerName(), request.getServerPort());

       String search = "";

       if(param != null){
           search += "&param="+param;
       }

       MyPage<ResponseCommentDTO> response = converter.convert(categories, url, search);

       return BaseResponseDTO.ok(response);
    }

    @GetMapping(value = "/posts/{blog}/comment/{id}")
    public BaseResponseDTO<ResponseCommentDTO> getOneComment(@PathVariable Integer blog, @PathVariable Integer id) {
        return BaseResponseDTO.ok(CommentService.findByBlogId(blog, id));
    }

    @DeleteMapping(value = "/posts/{id}/comment")
    public BaseResponseDTO deleteComment(@PathVariable("id") Integer id) {
        
       return BaseResponseDTO.ok(CommentService.deleteById(id));
    }

    @PostMapping(value = "/posts/{id}/comment")
    public BaseResponseDTO createComment(@PathVariable("id") Integer id, @Valid @RequestBody CommentDTO request) {
        return BaseResponseDTO.ok(CommentService.save(request, id));
    }

}