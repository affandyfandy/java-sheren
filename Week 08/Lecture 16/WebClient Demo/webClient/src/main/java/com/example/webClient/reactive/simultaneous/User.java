package com.example.webClient.reactive.simultaneous;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;

    @JsonCreator
    public User(@JsonProperty("id") int id) {
        this.id = id;
    }
}
