package com.example.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogAuthorResponse;
import com.example.blog.common.dto.response.BlogCategoriesResponse;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.common.dto.response.BlogTagResponse;
import com.example.blog.common.dto.response.ResponseBlogDTO;
import com.example.blog.model.Blog;
import com.example.blog.model.Tags;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.example.blog.common.dto.exception.ResourceNotFoundException;
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
  
    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    private static final String RESOURCE = "Blog";
    private static final String FIELD = "id";

    
    private ResponseBlogDTO fromEntity(Blog blog) {
        ResponseBlogDTO response = new ResponseBlogDTO();
        BeanUtils.copyProperties(blog, response);
        response.setCategories(new BlogCategoriesResponse());
        response.getCategories().setId(blog.getCategories().getId());
        response.getCategories().setName(blog.getCategories().getName());

        response.setAuthor(new BlogAuthorResponse());
        response.getAuthor().setId(blog.getAuthor().getId());
        response.getAuthor().setFirstname(blog.getAuthor().getFirst_name());
        response.getAuthor().setLastname(blog.getAuthor().getLast_name());

        List<Tags> tags = tagRepository.findByBlog(blog);

        if (!tags.isEmpty()) {
            ArrayList<BlogTagResponse> blog_tag_list = convertToBlog(tags);
            response.setTags(blog_tag_list);
        }

        return response;
    }

    private ArrayList<BlogTagResponse> convertToBlog(List<Tags> tags) {
        ArrayList<BlogTagResponse> blog_tag_list = new ArrayList<>();

        for (Tags tag : tags) {
            BlogTagResponse blog_tag_temp = new BlogTagResponse();
             BeanUtils.copyProperties(tag, blog_tag_temp);

             blog_tag_temp.setId(tag.getId());
             blog_tag_temp.setName(tag.getName());

             blog_tag_list.add(blog_tag_temp);
        }

        return blog_tag_list;
    }

    public Blog save(BlogDTO reqBlog) {
         
        try{
            Blog blog = new Blog();
            tagService.saveAll(blog, reqBlog.getTags());

            return blogRepository.save(blog);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Blog update(BlogDTO reqBlog, Integer id) {

        Blog blog = blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
         
        try{
            blog = blogRepository.save(blog);
            tagService.deleteAllByPostId(blog.getId());
            tagService.saveAll(blog, reqBlog.getTags());

            return blog;

        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


    public BaseResponseDTO<BlogResponse> delete(BlogDeleteRequest request) {
        try {
            Blog blog = blogRepository.findById(request.getId()).orElseThrow(()->new ResourceNotFoundException(request.getId().toString(), FIELD, RESOURCE));
            blogRepository.delete(blog);
            
            return BaseResponseDTO.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponseDTO.error("500", "Failed to delete post.");
        }
    }

    @Override
    public Page<ResponseBlogDTO> findAll(Pageable pageable) {
        try {
            return blogRepository.findAll(pageable).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseBlogDTO findById(Integer id) {
        try {
            Blog blog = blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
            
            return fromEntity(blog);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<ResponseBlogDTO> search(Pageable pageable, String param) {
        try {
            param = param.toLowerCase();
            return blogRepository.search(pageable, param).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<ResponseBlogDTO> findByAuthor(Pageable pageable, Integer author_id) {
        try {
            return blogRepository.findByAuthor(pageable, author_id).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<ResponseBlogDTO> findByCategories(Pageable pageable, Integer categories_id) {
        try {
            return blogRepository.findByCategories(pageable, categories_id).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Page<ResponseBlogDTO> findByTag(Pageable pageable, String tag_name) {
        try {
            return blogRepository.findByTag(pageable, tag_name).map(this::fromEntity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
