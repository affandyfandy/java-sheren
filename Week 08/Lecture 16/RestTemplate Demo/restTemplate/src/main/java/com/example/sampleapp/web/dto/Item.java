package com.example.sampleapp.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;

    public Item() {
        super();
    }

    public Item(final int id, final String itemName, final String ownerName) {
        this.id = id;
        this.itemName = itemName;
        this.ownerName = ownerName;
    }
}
