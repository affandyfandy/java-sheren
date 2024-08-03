package com.example.sampleapp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Foo {
    private long id;
    private String name;

    public Foo() {
        super();
    }

    public Foo(final String name) {
        super();

        this.name = name;
    }

    public Foo(final long id, final String name) {
        super();

        this.id = id;
        this.name = name;
    }
}
