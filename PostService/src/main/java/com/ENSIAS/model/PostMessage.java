package com.ENSIAS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMessage {
    private String message;
    private ENSIASt ensiaSt;
    private PostRequest request;
}
