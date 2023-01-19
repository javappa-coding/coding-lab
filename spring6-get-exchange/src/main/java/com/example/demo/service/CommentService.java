package com.example.demo.service;

import com.example.demo.domain.Comment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface CommentService {

    @GetExchange("/comments/{id}")
    Comment findComment(@PathVariable Long id);
}