package com.ENSIAS.controller;


import com.ENSIAS.model.Post;
import com.ENSIAS.model.PostRequest;
import com.ENSIAS.service.PostService;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public String get(){
        return "hello world";
    }

//    @PostMapping("/post")
//    public String createPost(@RequestBody PostRequest postRequest){
//        if(postService.createPost(postRequest)!=null){
//            return "Post created";
//        }
//        return "Post not created";
//    }

    @PutMapping("/update/{id}")
    public String updatePost(@PathVariable Integer id,
                             @RequestBody PostRequest request){
        return postService.updatePost(id,request);
    }


    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") Integer id,
                             String email){
        return postService.deletePost(id,"test@gmail.com");
    }

    @GetMapping("/me")
    public List<Post> findCurrentENSIAStsPosts(){
        //to change later
        String ensiastEmail = "test@gmail.com";
        return postService.postsByENSIASt(ensiastEmail);
    }

    @GetMapping("/{ensiastEmail}")
    public List<Post> findENSIAStsPosts(@PathVariable String ensiastEmail){
        //String ensiastEmail = "test@gmail.com";
        return postService.postsByENSIASt(ensiastEmail);
    }
}
