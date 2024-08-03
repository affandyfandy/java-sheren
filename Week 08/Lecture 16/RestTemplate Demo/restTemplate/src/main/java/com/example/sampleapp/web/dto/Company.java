package com.example.sampleapp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {

    private long id;
    private String name;

    public Company() {
        super();
    }

    public Company(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company [id = " + id + ", name = " + name + "]";
    }
}
