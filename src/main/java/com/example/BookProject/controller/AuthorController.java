package com.example.BookProject.controller;

import com.example.BookProject.entity.Author;
import com.example.BookProject.entity.Book;
import com.example.BookProject.repos.AuthorRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepos authorRepos;
    @GetMapping("/author")
    public String author(Map<String, Object> model) {
        Iterable<Author> author = authorRepos.findAll();
        model.put("name", author);
        return "author";
    }

    @GetMapping("/author/{id}")
    public String authorDetails(@PathVariable Integer id, Model model) {
        Optional<Author> author = authorRepos.findById(id);
        author.ifPresent(value -> model.addAttribute("author", value));
        return "author";
    }

    @PostMapping("/author")
    public String addAuthor(@RequestParam int age, @RequestParam String country, Map<String,Object> model){
        Author author = new Author(age,country);
        authorRepos.save(author);
        return "redirect:/author";
    }
}
