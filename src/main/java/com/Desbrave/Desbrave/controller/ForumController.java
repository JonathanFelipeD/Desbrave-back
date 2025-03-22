package com.Desbrave.Desbrave.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.Desbrave.Desbrave.model.Forum;
import com.Desbrave.Desbrave.repository.ForumRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/forum")
@RequiredArgsConstructor
public class ForumController {


    
    private final ForumRepository forumRepository;

    @GetMapping
    public List<Forum> getAllForuns(){

        return forumRepository.findAll();
    }
    @PostMapping 
    public Forum createForum(@RequestBody Forum forum){
        return forumRepository.save(forum);


    }
    @GetMapping("{id}")
    public Forum getForumById(@PathVariable Integer id){
        return forumRepository.findById(id).orElseThrow(()-> new RuntimeException("Forum Não Encontrado"));

    }

    @DeleteMapping("/{id}")
        public void deletarForum(@PathVariable Integer id){
            forumRepository.deleteById(id);   
        } 
    





}
