package com.exercises.m6gradle;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TodoController {
    private final AtomicLong counter = new AtomicLong();
    private final ArrayList<Todo> todos;

    TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("/inbox/items")
    public Todo createNew() {
        Todo res = new Todo(counter.incrementAndGet(), "todo");
        todos.add(res);
        return res;
    }

    @GetMapping("/inbox/items/{id}")
    public Todo getOne(@PathVariable Long id) {
        for (Todo todo : todos) {
            if (todo.id() == id) {
                return todo;
            }
        }

        return null;
    }

    @PostMapping("/inbox/items/{id}")
    public String update(@PathVariable Long id, @RequestBody Todo newTodo) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).id() == id) {
                todos.set(i, newTodo);
                return "Success";
            }
        }

        return "Not found";
    }
}