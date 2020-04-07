package com.example.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.blog.common.dto.BlogDTO;
import com.example.blog.common.dto.ResponseBaseDTO;
import com.example.blog.common.dto.request.BlogDeleteRequest;
import com.example.blog.common.dto.response.BaseResponseDTO;
import com.example.blog.common.dto.response.BlogAuthorResponse;
import com.example.blog.common.dto.response.BlogCategoriesResponse;
import com.example.blog.common.dto.response.BlogResponse;
import com.example.blog.model.Author;
import com.example.blog.model.Blog;
import com.example.blog.model.Categories;
import com.example.blog.model.Tags;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoriesRepository;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.blog.common.dto.exception.ResourceNotFoundException;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    private static final String RESOURCE = "Tags";
    private static final String FIELD = "id";

    @Override
    public BaseResponseDTO<List<Blog>> findAll() {
        List<Blog> blogs = blogRepository.findAll();

        return BaseResponseDTO.ok(blogs);
    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog update(Blog blogs) {
        return blogRepository.save(blogs);
    }

    // @Override
    // public Iterable<Blog> findPostByCategoriesId(Integer category_id) {
    // return blogRepository.findByCategoriesId(category_id);
    // }

    // @Override
    // public Iterable<Blog> findByTitle(String title) {
    // return blogRepository.findByTitle(title);

    // }

    @Override
    public BaseResponseDTO<List<Blog>> findPostByCategoriesId(Integer categories_id) {
        List<Blog> blogs = blogRepository.findByCategoriesId(categories_id);

        return BaseResponseDTO.ok(blogs);
    }

    @Override
    public BaseResponseDTO<List<Blog>> findByTitle(String title) {
        List<Blog> blogs = blogRepository.findByTitle(title);

        return BaseResponseDTO.ok(blogs);
    }

    public Blog save(BlogDTO reqBlog) {
        try {
            Blog blog = new Blog();

            blog.setTitle(reqBlog.getTitle());
            blog.setContent(reqBlog.getContent());
            blog.setCreatedAt(new Date());
            blog.setUpdatedAt(new Date());
            blog.setCategories(reqBlog.getCategories());
            blog.setAuthor(reqBlog.getAuthor());

            return blogRepository.save(blog);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Blog save2(BlogDTO reqBlog) {
         
        try{
            Blog blog = new Blog();

            blog.setTitle(reqBlog.getTitle());
            blog.setContent(reqBlog.getContent());
            blog.setCreatedAt(new Date());
            blog.setUpdatedAt(new Date());
            blog.setCategories(reqBlog.getCategories());
            blog.setAuthor(reqBlog.getAuthor());

            tagService.saveAll(blog, reqBlog.getTags());

            return blogRepository.save(blog);

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Blog update2(BlogDTO reqBlog, Integer id) {

        Blog blog = blogRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id.toString(), FIELD, RESOURCE));
         
        try{
           
            blog.setTitle(reqBlog.getTitle());
            blog.setContent(reqBlog.getContent());
            blog.setCategories(reqBlog.getCategories());
            blog.setAuthor(reqBlog.getAuthor());

            blog = blogRepository.save(blog);

            tagService.deleteAllByPostId(blog.getId());
            tagService.saveAll(blog, reqBlog.getTags());

            return blog;

        }catch (Exception e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
            throw e;
        }
    }


    public BaseResponseDTO<BlogResponse> delete(BlogDeleteRequest request) {
        try {
            Blog blog = blogRepository.getOne(request.getId());
            blogRepository.delete(blog);

            BlogResponse blogResponse = new BlogResponse();
            blogResponse.setId(blog.getId());
            blogResponse.setTitle(blog.getTitle());
            blogResponse.setContent(blog.getContent());
            blogResponse.setCreated_at(blog.getCreatedAt());
            blogResponse.setUpdated_at(blog.getUpdatedAt());
            
            // Author
            BlogAuthorResponse authorResponse = new BlogAuthorResponse();
            authorResponse.setFirstname(blog.getAuthor().getFirst_name());
            authorResponse.setLastname(blog.getAuthor().getLast_name());
            blogResponse.setAuthor(authorResponse);

            // Category
            BlogCategoriesResponse categoriesResponse = new BlogCategoriesResponse();
            categoriesResponse.setName(blog.getCategories().getName());
            blogResponse.setCategories(categoriesResponse);
            
            return BaseResponseDTO.ok(blogResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponseDTO.error("500", "Failed to delete post.");
        }
    }
}
