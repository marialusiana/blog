package com.example.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @DeleteMapping(value = "/tags")
    public BaseResponseDTO deleteTag(@RequestBody Tags tag) {
        
       return BaseResponseDTO.ok(tagService.deleteById(tag.getId()));
    }

    @PostMapping(value = "/tags")
    public BaseResponseDTO createTag(@Valid @RequestBody TagsRequest request) {
        return BaseResponseDTO.ok(tagService.save(request));
    }

    @PutMapping(value = "/tags/{id}")
    public BaseResponseDTO updateTag(
         @Valid @RequestBody TagsRequest request, @PathVariable("id") Integer id
    ) {
       tagService.update(id, request);
       return BaseResponseDTO.ok(tagService.update(id, request));
    }
}
