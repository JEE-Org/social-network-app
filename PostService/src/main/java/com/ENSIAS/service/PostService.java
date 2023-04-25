package com.ENSIAS.service;

import com.ENSIAS.model.Post;
import com.ENSIAS.model.PostRequest;

import java.util.List;

public class PostService implements IPostService {

    @Override
    public Post createPost(PostRequest postRequest) {
        return null;
    }

    @Override
    public void deletePost(String postId, String username) {

    }

    @Override
    public List<Post> postsByENSIASt(String ensiast) {
        return null;
    }
}
