package com.example.assignment3.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResponse {
    private String message;
    private String username;
    private String timestamp;

    public DeleteResponse(String message, String username, String timestamp) {
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

}
