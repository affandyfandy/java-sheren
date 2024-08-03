package com.example.sampleapp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeavyResource {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String address;


    public HeavyResource() {

    }

    public HeavyResource(Integer id, String name, String surname, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
    }
}
