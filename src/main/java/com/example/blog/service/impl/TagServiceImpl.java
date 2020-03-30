package com.example.blog.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.blog.model.Tags;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tags> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tags> findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Optional<Tags> findById(long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tags save(Tags tags) {
        return tagRepository.save(tags);
    }

    @Override
    public void delete(long id) {
        tagRepository.deleteById(id);
    }
}
