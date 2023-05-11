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
        String message = postMessage.getMessageType();
        ENSIASt ensiaSt = postMessage.getEnsiaSt();
        String email = ensiaSt.getEmail();
        PostRequest postRequest = postMessage.getRequest();
        Integer id = postRequest.getPostId();
        switch (message) {
            case "CREATE" -> createPost(postRequest, email);
            case "UPDATE" -> updatePost(id, postRequest, email);
            case "DELETE" -> deletePost(id, email);
        }
//        if(message.equals("CREATE")) {
//            createPost(postRequest, email);
//        }else{
//            if(message.equals("UPDATE")){
//                updatePost(id, postRequest, email);
//            }
//            else{
//                if(message.equals("DELETE")){
//                    deletePost(id,email);
//                }
//            }
//        }
        System.out.println(message);
        System.out.println(email);
        System.out.println(postRequest.getCaption());
    }

    @Override
    public Post createPost(PostRequest postRequest,String email) {
        Post post = Post.builder()
                .caption(postRequest.getCaption())
                .createdAt(Instant.now())
                .ensiastEmail(email)
                .build();
        postRepository.saveAndFlush(post);
        return post;
    }

    @Override
    public String deletePost(Integer postId, String email) {

        Optional<Post> post = postRepository.findById(postId);
        String ensiastEmail = postRepository.findById(postId).get().getEnsiastEmail();
        if(post.isPresent()
        && ensiastEmail.equals(email)){
            postRepository.delete(post.get());
            return "Post deleted";
        }
        return "Post not found";
    }

    @Override
    public String updatePost(
            Integer postId,
            PostRequest request,
            String email){
        Optional<Post> post = postRepository.findById(postId);
        String ensiastEmail = postRepository.findById(postId).get().getEnsiastEmail();
        if(post.isPresent()
                && ensiastEmail.equals(email)){
            Post post2 = postRepository.findById(postId).get();
            post2.setCaption(request.getCaption());
            post2.setUpdatedAt(Instant.now());
            postRepository.saveAndFlush(post2);
            return "Post updated";
        }
        return "Post couldn't be updated";
    }

    @Override
    public List<Post> postsByENSIASt(String ensiast) {
        return postRepository.findByEnsiastEmailOrderByCreatedAtDesc(ensiast);
    }
}
