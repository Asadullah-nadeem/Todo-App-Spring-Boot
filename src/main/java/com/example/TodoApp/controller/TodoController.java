package com.example.TodoApp.controller;


import com.example.TodoApp.Repository.TodoRepository;
import com.example.TodoApp.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {


    private final TodoRepository todoRepository;

//    add Templates here name is Index.html
    @GetMapping({"", "/", "/home"})
    public String showHomePage(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "index";
    }
// Add
    @PostMapping("/add")
    public String add(@RequestParam String title){
        TodoEntity newTodo = TodoEntity
                .builder()
                .title(title)
                .completed(false)
                .build();
        todoRepository.save(newTodo);
        return "redirect:/";
    }

//    Update
    @GetMapping("/update/{id}")
    public String Update(@PathVariable Long id){
        TodoEntity existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not Found: " + id));
        existingTodo.setCompleted(true);
        todoRepository.save(existingTodo);
        return "redirect:/";
    }

//    Delete
    @GetMapping("/delete/{id}")
    public String Delete(@PathVariable Long id){
        TodoEntity existingTodo = todoRepository.findById(id)
                .orElseThrow(()
                        -> new RuntimeException("Todo not Found: " + id));
        todoRepository.delete(existingTodo);
        return "redirect:/";
    }
}
