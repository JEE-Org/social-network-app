package com.ENSIAS.service;

import com.ENSIAS.model.ENSIASt;
import com.ENSIAS.model.Post;
import com.ENSIAS.model.PostMessage;
import com.ENSIAS.model.PostRequest;
import com.ENSIAS.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository ;

    @InjectMocks
    private PostService postService ;

    @Test
    public void createPostShouldSavePostAndReturnIt(){
        PostRequest postRequest = new PostRequest();
        postRequest.setCaption("Test caption");
        String email = "elmrabti@gmail.com";

        // When
        Post result = postService.createPost(postRequest, email);

        // Then
        assertNotNull(result);
        assertEquals("Test caption", result.getCaption());
        assertNotNull(result.getCreatedAt());
        assertEquals(email, result.getEnsiastEmail());
        //Verify
        verify(postRepository, times(1)).saveAndFlush(result);

    }



    @Test
    void deletePostShouldDeletePostWhenFoundAndEmailMatches() {
        // given
        int postId = 1;
        String email = "elmrabti@gmail.com";
        Post post = new Post();
        post.setEnsiastEmail(email);
        Optional<Post> postOptional = Optional.of(post);
        when(postRepository.findById(postId)).thenReturn(postOptional);

        // when
        String result = postService.deletePost(postId, email);

        // then
        assertEquals("Post deleted", result);
        verify(postRepository, times(1)).delete(post);
    }


    @Test
    void deletePostShouldReturnNotFoundWhenEmailDoesNotMatch() {
        // given
        int postId = 1;
        String email = "elmrabti@gmail.com";
        Post post = new Post();
        post.setEnsiastEmail("hamza@gmail.com");
        Optional<Post> postOptional = Optional.of(post);
        //when
        when(postRepository.findById(postId)).thenReturn(postOptional);
        String result = postService.deletePost(postId, email);

        assertEquals("Post not found", result);
        verify(postRepository, never()).delete(any(Post.class));
    }

    @Test
    void updatePostShouldReturnSuccessMessageWhenPostIsUpdated() {
        int postId = 1;
        String email = "elmrabti@gmail.com";
        PostRequest request = new PostRequest();
        request.setCaption("Updated Caption");

        Post post = new Post();
        post.setId(postId);
        post.setEnsiastEmail(email);
        //when
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));
        //Call
        String result = postService.updatePost(postId, request, email);
        // Verify
        verify(postRepository, times(3)).findById(postId);
        verify(postRepository, times(1)).saveAndFlush(post);

        // Assert
        assertEquals("Post updated", result);
    }


    @Test
    void updatePostShouldReturnErrorMessageWhenPostBelongsToAnotherUser() {
        int postId = 1;
        String email = "elmrabti@gmail.com";
        PostRequest request = new PostRequest();
        request.setCaption("Updated Caption");

        //Mock
        Post post = new Post();
        post.setEnsiastEmail("hamza@gmail.com");
        Optional<Post> postOptional = Optional.of(post);
        when(postRepository.findById(postId)).thenReturn(postOptional);
        // Call
        String result = postService.updatePost(postId, request, email);
        // Verify
        verify(postRepository, times(2)).findById(postId);
        // Assert
        assertEquals("Post couldn't be updated", result);
    }


    @Test
    void postsByENSIAStShouldReturnListOfPostsOrderedByCreatedAtDesc() {
        // Préparer les données de test
        String ensiastEmail = "elmrabti@gmail.com";
        Post post1 = new Post();
        post1.setEnsiastEmail(ensiastEmail);
        post1.setCreatedAt(Instant.now().minus(Duration.ofDays(2)));

        Post post2 = new Post();
        post2.setEnsiastEmail(ensiastEmail);
        post2.setCreatedAt(Instant.now().minus(Duration.ofDays(1)));

        List<Post> expectedPosts = Arrays.asList(post2, post1);

        // when
        when(postRepository.findByEnsiastEmailOrderByCreatedAtDesc(ensiastEmail)).thenReturn(expectedPosts);
        List<Post> result = postService.postsByENSIASt(ensiastEmail);

        // Verify
        verify(postRepository).findByEnsiastEmailOrderByCreatedAtDesc(ensiastEmail);

        // Assert
        assertEquals(expectedPosts, result);
    }
























}
