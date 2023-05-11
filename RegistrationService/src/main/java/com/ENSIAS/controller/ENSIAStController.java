package com.ENSIAS.controller;


import com.ENSIAS.model.*;
import com.ENSIAS.service.ENSIAStService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/ENSIASts")
@Slf4j
public class ENSIAStController {

    private final ENSIAStService ensiaStService;

    @GetMapping("/home")
    public String get(){
        return "hello world";
    }

    @GetMapping
    public List<ENSIASt> findAll(){
        return ensiaStService.findAll();
    }

    @GetMapping("/lastnames")
    public List<String> findByLastName(@RequestParam(value = "lastname") String lastName ){
        Optional<List<ENSIASt>> ensiaSt = ensiaStService.findByLastName(lastName);
        return ensiaSt
                .stream()
                .flatMap(List::stream)
                .map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/promos")
    public List<ENSIASt> findByPromo(@RequestParam(value = "promo") Integer promo){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromo(promo);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/{promo}")
    public List<ENSIASt> findByPromoAndField(@PathVariable(value = "promo") Integer promo,
                                             @RequestParam(value = "field") String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByPromoAndField(promo,field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @GetMapping("/fields")
    public List<ENSIASt> findByField(@RequestParam(value = "field") String field){
        Optional<List<ENSIASt>> ensiaSts = ensiaStService.findByField(field);
        return ensiaSts
                .stream()
                .flatMap(List::stream)
                //.map(ENSIASt::toString)
                .toList();
    }

    @PostMapping("/post/create")
    public void createPost(@RequestBody PostRequest request){
        String message = "CREATE";
        ENSIASt ensiaSt = ensiaStService.currentENSIASt();

        PostMessage postMessage = new PostMessage();
        postMessage.setMessageType(message);
        postMessage.setEnsiaSt(ensiaSt);
        postMessage.setRequest(request);

        ensiaStService.sendPostMessage(postMessage);
        log.info(String.format("%s created a post",ensiaSt.getEmail()));
    }
    @PutMapping("/post/update/{id}")
    public void updatePost(@RequestBody PostRequest request,
                           @PathVariable Integer id){
        String message = "UPDATE";
        ENSIASt ensiaSt = ensiaStService.currentENSIASt();
        PostRequest postRequest = new PostRequest(request.getCaption(),id);
        PostMessage postMessage = new PostMessage(
                message,
                postRequest,
                ensiaSt
        );
        ensiaStService.sendPostMessage(postMessage);
        log.info(String.format("%s updated a post",ensiaSt.getEmail()));
    }
    @DeleteMapping("/post/delete/{id}")
    public void deletePost(@PathVariable Integer id){
        String message = "DELETE";
        ENSIASt ensiaSt = ensiaStService.currentENSIASt();
        PostMessage postMessage = new PostMessage(
                message,
                new PostRequest(id),
                ensiaSt
        );
        ensiaStService.sendPostMessage(postMessage);
        log.info(String.format("%s deleted a post",ensiaSt.getEmail()));
    }
//    @GetMapping("/post/me")
//    public void myPosts(){
//        String message = "ME";
//        ENSIASt ensiaSt = ensiaStService.currentENSIASt();
//        PostMessage postMessage = new PostMessage(
//                message,
//                ensiaSt
//        );
//        ensiaStService.sendPostMessage(postMessage);
//        log.info(String.format("%s created a post",ensiaSt.getEmail()));
//    }
//    @GetMapping("/post")
//    public void ensiastsPosts(){
//        String message = "ENSIASts";
//        ENSIASt ensiaSt = ensiaStService.currentENSIASt();
//        PostMessage postMessage = new PostMessage(
//                message,
//                ensiaSt
//        );
//        ensiaStService.sendPostMessage(postMessage);
//        log.info(String.format("%s created a post",ensiaSt.getEmail()));
//    }
}
