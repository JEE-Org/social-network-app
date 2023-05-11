package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Post;
import com.ENSIAS.model.PostMessage;
import com.ENSIAS.model.PostRequest;
import com.ENSIAS.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @KafkaListener(
            topics = "${spring.kafka.topic2.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(PostMessage postMessage){
        String message = postMessage.getMessage();
        ENSIASt ensiaSt = postMessage.getEnsiaSt();
        String email = ensiaSt.getEmail();
        PostRequest postRequest = postMessage.getRequest();
        createPost(postRequest,email);
    }

    @Override
    public Post createPost(PostRequest postRequest,String email) {
        Post post = Post.builder()
                .caption(postRequest.getCaption())
                .createdAt(Instant.now())
                .ensiastEmail(email)//to change
                .build();
        postRepository.saveAndFlush(post);
        return post;
    }

    @Override
    public String deletePost(Integer postId, String email) {

        Optional<Post> ensiast = postRepository.findById(postId);
        String ensiastEmail = postRepository.findById(postId).get().getEnsiastEmail();
        if(ensiast.isPresent()
        && ensiastEmail.equals(email)){
            postRepository.delete(ensiast.get());
            return "Post deleted";
        }
        return "Post not found";
    }

    @Override
    public String updatePost(Integer postId, PostRequest request){
        if(postRepository.findById(postId).isPresent())
            {
                Post post = postRepository.findById(postId).get();
                post.setCaption(request.getCaption());
                post.setUpdatedAt(Instant.now());
                postRepository.saveAndFlush(post);
                return "Post updated";
            }
        return "Post couldn't be updated";
    }

    @Override
    public List<Post> postsByENSIASt(String ensiast) {
        return postRepository.findByEnsiastEmailOrderByCreatedAtDesc(ensiast);
    }
}
