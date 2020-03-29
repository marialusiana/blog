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

import com.example.blog.model.Tags;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<ResponseBaseDTO<Iterable<Tags>>> listTags(){ 
        ResponseBaseDTO<Iterable<Tags>> response = new ResponseBaseDTO<Iterable<Tags>>(); 
        try
        {         
         Iterable<Tags> tagList = tagService.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(tagList);         
         
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

    @GetMapping("/tags/{id}")
	public ResponseEntity<ResponseBaseDTO> getTagById(@PathVariable("id") long id) {
        Optional<Tags> tagData = tagService.findById(id);
        
        ResponseBaseDTO response = new ResponseBaseDTO(); 

		if (tagData.isPresent()) {
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(tagData);     
            return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/tag")
	public ResponseEntity<ResponseBaseDTO> createTag(@RequestBody Tags tag) {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
		try {
             Tags _tag = tagService.save(new Tags(tag.getName()));
            
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(_tag);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
    }

    @PutMapping("/tag/{id}")
	public ResponseEntity<Tags> updateTag(@PathVariable("id") long id, @RequestBody Tags tutorial) {
		Optional<Tags> tutorialData = tagService.findById(id);

		if (tutorialData.isPresent()) {
			Tags _tutorial = tutorialData.get();
			_tutorial.setName(tutorial.getName());
			return new ResponseEntity<>(tagService.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/tag/{id}")
	public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
		try {
			tagService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}


}
