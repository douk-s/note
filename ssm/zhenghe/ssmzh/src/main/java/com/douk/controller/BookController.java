package com.douk.controller;

import com.douk.pojo.Books;
import com.douk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> books = bookService.queryAllBooks();
        model.addAttribute("list",books);

        return "allBook";
    }
    @RequestMapping("/")
    public String in(){

        return "index";
    }
}
