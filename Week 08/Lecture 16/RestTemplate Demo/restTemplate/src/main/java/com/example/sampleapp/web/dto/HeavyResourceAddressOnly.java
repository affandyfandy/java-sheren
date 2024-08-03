package com.example.sampleapp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeavyResourceAddressOnly {

    private Integer id;
    private String address;

    public HeavyResourceAddressOnly() {
    }

    public HeavyResourceAddressOnly(Integer id, String address) {
        this.id = id;
        this.address = address;
    }
}
