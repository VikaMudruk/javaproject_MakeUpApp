package edu.vikmud.emuwish.controllers;

import edu.vikmud.emuwish.services.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/hello")
    public String helloWorld(@RequestParam(value="name", defaultValue = "World") String name){
        return "index";
    }


    @GetMapping("/search")
    public String searchPage(@RequestParam(value="query") String searchQuery, Model model){

        return "search";
    }

}
