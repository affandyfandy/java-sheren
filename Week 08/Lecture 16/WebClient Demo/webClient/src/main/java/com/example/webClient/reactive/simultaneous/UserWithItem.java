package com.example.webClient.reactive.simultaneous;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithItem {

    private User user;
    private Item item;

    public UserWithItem(User user, Item item) {
        this.user = user;
        this.item = item;
    }
}
