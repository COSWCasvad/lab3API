package com.eci.cosw.springbootsecureapi.controller;


import com.eci.cosw.springbootsecureapi.model.Todo;
import com.eci.cosw.springbootsecureapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api" )
public class TodoController {
    @Autowired
    public TodoService todoService ;

    @CrossOrigin
    @RequestMapping( value = "/todo", method = RequestMethod.GET )
    public List<Todo> getTodos() {

        return todoService.getTodoList();
    }

    @CrossOrigin
    @RequestMapping( value = "/todo", method = RequestMethod.POST )
    public Todo addTodo(@RequestBody Todo todo ) {
        return todoService.addTodo(todo);
    }
}
