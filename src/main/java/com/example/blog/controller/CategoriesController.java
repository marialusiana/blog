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
import com.example.blog.model.Categories;
import com.example.blog.model.Tags;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.CategoriesRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.ResponseCategoriesDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

@RestController
public class CategoriesController {

    @Autowired
    CategoriesService CategoriesService;

    @GetMapping(value = "/categories")
    public BaseResponseDTO<MyPage<ResponseCategoriesDTO>> listCategories(
        MyPageable pageable, @RequestParam(required = false) String param, HttpServletRequest request
    ) { 
       Page<ResponseCategoriesDTO> categories;

       if (param != null) {
           categories = CategoriesService.findByName(MyPageable.convertToPageable(pageable), param);
       } else {
           categories = CategoriesService.findAll(MyPageable.convertToPageable(pageable));
       }

       PageConverter<ResponseCategoriesDTO> converter = new PageConverter<>();
       String url = String.format("%s://%s:%d/api/categories",request.getScheme(),  request.getServerName(), request.getServerPort());

       String search = "";

       if(param != null){
           search += "&param="+param;
       }

       MyPage<ResponseCategoriesDTO> response = converter.convert(categories, url, search);

       return BaseResponseDTO.ok(response);
    }

    @DeleteMapping(value = "/categories")
    public BaseResponseDTO deleteCategories(@RequestBody Categories categories) {
        
       return BaseResponseDTO.ok(CategoriesService.deleteById(categories.getId()));
    }

    @PostMapping(value = "/categories")
    public BaseResponseDTO createCategories(@Valid @RequestBody CategoriesRequest request) {
        return BaseResponseDTO.ok(CategoriesService.save(request));
    }

    @PutMapping(value = "/categories/{id}")
    public BaseResponseDTO updateCategories(
         @Valid @RequestBody CategoriesRequest request, @PathVariable("id") Integer id
    ) {
       CategoriesService.update(id, request);
       return BaseResponseDTO.ok(CategoriesService.update(id, request));
    }

    @GetMapping(value = "/categories/{id}")
    public BaseResponseDTO<ResponseCategoriesDTO> getOne(@PathVariable Integer id) {
        return BaseResponseDTO.ok(CategoriesService.findById(id));
    }
    
}