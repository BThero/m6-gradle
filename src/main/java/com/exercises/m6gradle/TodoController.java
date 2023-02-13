package com.exercises.m6gradle;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TodoController {
    private final AtomicLong counter = new AtomicLong();
    private final ArrayList<Todo> todos;

    TodoController() {
        todos = new ArrayList<>();
    }

    @GetMapping("/todos")
    public List<Todo> getAll() {
        return todos;
    }

    @PostMapping("/todos")
    public Todo createNew(@RequestBody String status) {
        Todo res = new Todo(counter.incrementAndGet(), status != null ? status : "to do");
        todos.add(res);
        return res;
    }

    @GetMapping("/todos/{id}")
    public Todo getOne(@PathVariable Long id) {
        for (Todo todo : todos) {
            if (todo.id() == id) {
                return todo;
            }
        }

        return null;
    }

    @PostMapping("/todos/{id}")
    public String update(@PathVariable Long id, @RequestBody String status) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).id() == id) {
                todos.set(i, new Todo(id, status));
                return "Success";
            }
        }

        return "Not found";
    }
}