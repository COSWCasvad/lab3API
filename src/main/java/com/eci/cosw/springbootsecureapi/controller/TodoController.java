package com.eci.cosw.springbootsecureapi.controller;


import com.eci.cosw.springbootsecureapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "api" )
public class TodoController {
    @Autowired
    private TodoService todoService;
}
