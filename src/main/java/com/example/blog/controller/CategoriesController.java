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
import com.example.blog.model.Categories;
import com.example.blog.model.Tags;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.CategoriesService;
import com.example.blog.service.TagService;

@RestController
@RequestMapping("/api")
public class CategoriesController {

    @Autowired
    CategoriesService CategoriesService;

    @GetMapping("/categories")
    public ResponseEntity<ResponseBaseDTO<Iterable<Categories>>> listCategories(){ 
        ResponseBaseDTO<Iterable<Categories>> response = new ResponseBaseDTO<Iterable<Categories>>(); 
        try
        {         
         Iterable<Categories> categoriesList = CategoriesService.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(categoriesList);         
         
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

    @PostMapping("/categories")
	public ResponseEntity<ResponseBaseDTO> createCategories(@RequestBody Categories categories) {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
		try {
             Categories _categories = CategoriesService.save(new Categories(categories.getName()));
            
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(_categories);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
    }

    @PutMapping("/categories/{id}")
	public ResponseEntity<Categories> updateCategories(@PathVariable("id") long id, @RequestBody Categories categories) {
		Optional<Categories> categoriesData = CategoriesService.findById(id);

		if (categoriesData.isPresent()) {
			Categories _Categories = categoriesData.get();
			_Categories.setName(categories.getName());
			return new ResponseEntity<>(CategoriesService.save(_Categories), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/categories/{id}")
	public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("id") long id) {
		try {
			CategoriesService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
    

}