package com.ENSIAS.repository;

import com.ENSIAS.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findByEnsiastEmailOrderByCreatedAtDesc(String email);
}
