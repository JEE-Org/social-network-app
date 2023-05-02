package com.ENSIAS.service;

import com.ENSIAS.model.PostRequest;
import com.ENSIAS.repository.PostRepository;
import com.ENSIAS.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {

    public Post createPost(PostRequest postRequest);
    public void deletePost(String postId, String username);
    public List<Post> postsByENSIASt(String ensiast);


}