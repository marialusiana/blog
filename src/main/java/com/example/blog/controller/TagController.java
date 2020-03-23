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
import com.example.blog.model.ResponseBaseDTO;
import com.example.blog.repository.TagRepository;

@RestController
@RequestMapping("/api")
public class TagController {

	@Autowired
    TagRepository tagRepository;

    @GetMapping("/tags")
    public ResponseEntity<ResponseBaseDTO> listUser(){ 
        ResponseBaseDTO response = new ResponseBaseDTO(); 
        try
        {         
         List<Tags> userList = tagRepository.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(userList);         
         
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
        
        // return userService.findAll();
    }


	@GetMapping("/tag")
	public ResponseEntity<ResponseBaseDTO> getAllTags() {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
        try {
            final List<Tags> tag = new ArrayList<Tags>();
            tagRepository.findAll().forEach(tag::add);

                response.setStatus(true);
                response.setCode("200");
                response.setMessage("success");
                response.setData(tag);     
 

            if (tag.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tag/{id}")
	public ResponseEntity<ResponseBaseDTO> getTutorialById(@PathVariable("id") long id) {
        Optional<Tags> tagData = tagRepository.findById(id);
        
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
	public ResponseEntity<ResponseBaseDTO> createTutorial(@RequestBody Tags tag) {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
		try {
            Tags _tag = tagRepository.save(new Tags(tag.getName()));
            
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData( _tag);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
    }

    @PutMapping("/tag/{id}")
	public ResponseEntity<Tags> updateTutorial(@PathVariable("id") long id, @RequestBody Tags tutorial) {
		Optional<Tags> tutorialData = tagRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tags _tutorial = tutorialData.get();
			_tutorial.setName(tutorial.getName());
			return new ResponseEntity<>(tagRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @DeleteMapping("/tag/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tagRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}


}
