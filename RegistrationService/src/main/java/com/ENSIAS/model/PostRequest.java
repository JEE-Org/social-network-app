package com.ENSIAS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostRequest {
    private String caption;
    private Integer postId;
    public PostRequest(Integer id){
        this.postId=id;
    }
}
