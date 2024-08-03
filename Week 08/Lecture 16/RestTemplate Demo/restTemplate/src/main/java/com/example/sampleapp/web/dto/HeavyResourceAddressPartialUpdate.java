package com.example.sampleapp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeavyResourceAddressPartialUpdate {

    private Integer id;
    private String address;

    public HeavyResourceAddressPartialUpdate() {
    }

    public HeavyResourceAddressPartialUpdate(Integer id, String address) {
        this.id = id;
        this.address = address;
    }
}
