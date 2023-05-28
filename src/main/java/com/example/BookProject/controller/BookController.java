package com.example.BookProject.controller;

import com.example.BookProject.entity.Book;
import com.example.BookProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller

public class BookController {
    @Autowired
    private BookService bookRepos;

    @GetMapping("/books")
    public String book(@RequestParam(name = "name", required = false, defaultValue = "Book name") String name,
                       Map<String, Object> model) {
        model.put("name", name);

        return "books";
    }

    @GetMapping

    public String main(Map<String, Object> model) {
        Iterable<Book> books = bookRepos.getAllBooks(); // вывожу полный список книг
        model.put("AllBooks", books);

        return "main";


    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable("id") Integer id, Model model) {
        //Переход на страницу книги
        Book book = bookRepos.getBookById(id);
        model.addAttribute("book", book);

        return "books";
    }
    @PostMapping()
    public String add(@RequestParam String name, @RequestParam String author, Map<String, Object> model) {
        Book book = new Book();
        book.setName(name);// добавляю новый объект
        book.setAuthor(author);// добавляю новый объект
        bookRepos.createBook(book); //сохраняю его
        Iterable<Book> books = bookRepos.getAllBooks(); //вывожу полный список
        model.put("AllBooks", books); //сохраняю в мапу
        return "main";

    }
    @GetMapping("{id}")
    public String delete(@PathVariable ("id") int id){
        bookRepos.deleteById(id);
        return "redirect:/";

    }
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Book book = bookRepos.updateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    }






