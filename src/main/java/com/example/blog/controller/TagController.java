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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.Tags;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.TagsRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.ResponseTagsDTO;
import com.example.blog.common.dto.util.PageConverter;
import com.example.blog.common.dto.MyPage;
import com.example.blog.common.dto.MyPageable;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    TagService tagService;

    // @GetMapping("/tags")
    // public ResponseEntity<ResponseBaseDTO<List<Tags>>> listTags(@RequestParam(required = false) String name){ 
    //     ResponseBaseDTO<List<Tags>> response = new ResponseBaseDTO<List<Tags>>(); 
    //     try
    //     {  
  
    //     if (name != null){
    //         List<Tags> tagList = tagService.findByName(name);
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(tagList);  
    //         return new ResponseEntity<>(response, HttpStatus.OK);
    //     }
                
    //         List<Tags> tagList = tagService.findAll();
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(tagList);         
            
    //         return new ResponseEntity<>(response ,HttpStatus.OK);
    //     }
    //     catch(Exception e)
    //     {
    //      // catch error when get user
    //      response.setStatus(false);
    //      response.setCode("500");
    //      response.setMessage(e.getMessage());
    //      return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    //     }
    
    // }

    @GetMapping(value = "/tags")
    public BaseResponseDTO<MyPage<ResponseTagsDTO>> listTags(
        MyPageable pageable, @RequestParam(required = false) String param, HttpServletRequest request
    ) { 
       Page<ResponseTagsDTO> tags;

       if (param != null) {
           tags = tagService.findByName(MyPageable.convertToPageable(pageable), param);
       } else {
           tags = tagService.findAll(MyPageable.convertToPageable(pageable));
       }

       PageConverter<ResponseTagsDTO> converter = new PageConverter<>();
       String url = String.format("%s://%s:%d/api/tags",request.getScheme(),  request.getServerName(), request.getServerPort());

       String search = "";

       if(param != null){
           search += "&param="+param;
       }

       MyPage<ResponseTagsDTO> response = converter.convert(tags, url, search);

       return BaseResponseDTO.ok(response);
    }

    @GetMapping(value = "/tags/{id}")
    public BaseResponseDTO<ResponseTagsDTO> getOne(@PathVariable Integer id) {
        return BaseResponseDTO.ok(tagService.findById(id));
    }


    // @GetMapping("/tags/{id}")
	// public ResponseEntity<ResponseBaseDTO> getTagById(@PathVariable("id") long id) {
    //     Optional<Tags> tagData = tagService.findById(id);
        
    //     ResponseBaseDTO response = new ResponseBaseDTO(); 

	// 	if (tagData.isPresent()) {
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(tagData);     
    //         return new ResponseEntity<>(response, HttpStatus.OK);

	// 	} else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
    // }
    
    // @PostMapping("/tag")
	// public ResponseEntity<ResponseBaseDTO> createTag(@RequestBody Tags tag) {
    //     ResponseBaseDTO response = new ResponseBaseDTO(); 
	// 	try {
    //         Tags _tag = tagService.save(new Tags(tag.getName()));
            
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(_tag);

	// 		return new ResponseEntity<>(response, HttpStatus.CREATED);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	// 	}
    // }

    // @PutMapping("/tag/{id}")
	// public ResponseEntity<Tags> updateTag(@PathVariable("id") long id, @RequestBody Tags tutorial) {
	// 	Optional<Tags> tutorialData = tagService.findById(id);

	// 	if (tutorialData.isPresent()) {
	// 		Tags _tutorial = tutorialData.get();
	// 		_tutorial.setName(tutorial.getName());
	// 		return new ResponseEntity<>(tagService.save(_tutorial), HttpStatus.OK);
	// 	} else {
	// 		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
    // }
    
    @DeleteMapping(value = "/tags")
    public BaseResponseDTO deleteTag(@RequestBody Tags tag) {
       tagService.deleteById(tag.getId());
       return BaseResponseDTO.ok();
    }

    @PostMapping(value = "/tags")
    public BaseResponseDTO createTag(@RequestBody TagsRequest request) {
        tagService.save(request);
        return BaseResponseDTO.created();
    }

    // @DeleteMapping("/tag")
	// public ResponseEntity<ResponseBaseDTO> deleteTag(@RequestBody Tags tag) {
	// 	try {
    //         ResponseBaseDTO response = new ResponseBaseDTO(); 

    //         Optional<Tags> tagData = tagService.findById(tag.getId());

    //         tagService.delete(tag.getId());
            
    //         response.setStatus(true);
    //         response.setCode("200");
    //         response.setMessage("success");
    //         response.setData(tagData);    

	// 		return new ResponseEntity<>(response, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	// 	}
	// }


}
