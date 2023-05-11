package com.ENSIAS.service;

import com.ENSIAS.model.PostRequest;
import com.ENSIAS.repository.PostRepository;
import com.ENSIAS.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {

    public Post createPost(PostRequest postRequest, String email);
    public String deletePost(Integer postId, String email);
    public String updatePost(Integer postId, PostRequest request, String email);
    public List<Post> postsByENSIASt(String ensiast);


}
