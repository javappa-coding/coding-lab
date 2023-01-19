package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {

    private Integer id;
    private String name;
    private String body;

    @JsonCreator
    public Comment(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("body") String body) {
        this.id = id;
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Comment {" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}