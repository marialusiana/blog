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

import javassist.NotFoundException;

import com.example.blog.model.Author;
import com.example.blog.common.dto.AuthorDTO;
import com.example.blog.common.dto.AuthorPasswordDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.service.AuthorService;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/author")
    public ResponseEntity<ResponseBaseDTO<Iterable<Author>>> listAuthor(){ 
        ResponseBaseDTO<Iterable<Author>> response = new ResponseBaseDTO<Iterable<Author>>(); 
        try
        {         
         Iterable<Author> authorList = authorService.findAll();
         response.setStatus(true);
         response.setCode("200");
         response.setMessage("success");
         response.setData(authorList);         
         
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

    @GetMapping("/author/{id}")
	public ResponseEntity<ResponseBaseDTO> getAuthorById(@PathVariable("id") long id) {
        Optional<Author> tagData = authorService.findById(id);
        
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
    
    @PostMapping("/author")
	public ResponseEntity<ResponseBaseDTO> createAuthor(@RequestBody Author author) {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
		try {
            Author _author = authorService.save(
                new Author(author.getUsername(), author.getFirstname(), author.getLastname(), author.getPassword()));
            
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");
            response.setData(_author);

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
    }

    @PutMapping("/author/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author author) {
		Optional<Author> authorData = authorService.findById(id);

		if (authorData.isPresent()) {
			Author _author = authorData.get();
            _author.setUsername(author.getUsername());
            _author.setFirstname(author.getFirstname());
            _author.setLastname(author.getLastname());
            _author.setPassword(author.getPassword());
			return new ResponseEntity<>(authorService.save(_author), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBaseDTO> putAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDto) throws NotFoundException {
        ResponseBaseDTO response = new ResponseBaseDTO(); 
        Author author = authorService.findById(id).orElseThrow(() -> new NotFoundException("Author id " + id + " NotFound"));

        try {
            author.setFirstname(authorDto.getFirst_name());
            author.setLastname(authorDto.getLast_name());
            author.setUsername(authorDto.getUsername());
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");

            response.setData(authorService.update(id, author));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<ResponseBaseDTO> putPaswordAuthor(@PathVariable Integer id, @RequestBody AuthorPasswordDTO authorPasswordDto) throws NotFoundException {
        ResponseBaseDTO response = new ResponseBaseDTO<>();
        Author author = authorService.findById(id).orElseThrow(() -> new NotFoundException("Author id " + id + " NotFound"));

        try {
            author.setPassword(authorPasswordDto.getPassword());
            response.setStatus(true);
            response.setCode("200");
            response.setMessage("success");

            response.setData(authorService.changePassword(id, author));
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode("500");
            response.setMessage(e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping("/author/{id}")
	public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") long id) {
		try {
			authorService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}


}