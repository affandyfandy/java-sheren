package com.example.restTemplate.resttemplate.web.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@XStreamAlias("Foo")
@Getter
@Setter
public class Foo {
    private Long id;
    private String name;

    public Foo() {
        super();
    }

    public Foo(final String name) {
        super();

        this.name = name;
    }

    public Foo(final Long id, final String name) {
        super();

        this.id = id;
        this.name = name;
    }
}
