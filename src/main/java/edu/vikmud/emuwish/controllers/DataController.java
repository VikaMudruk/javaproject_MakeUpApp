package edu.vikmud.emuwish.controllers;

import com.google.common.collect.Lists;
import edu.vikmud.emuwish.models.Item;
import edu.vikmud.emuwish.services.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private ItemRepository itemRepository;

    public void generalLayout(Model model){
        List<Item> wishlist = selectItem();

        model.addAttribute("wishlist", wishlist);
    }
    public List<Item> selectItem() {
        return Lists.newArrayList(itemRepository.findAll());
    }

    @GetMapping("/")
    public String indexPage(Model model){
        generalLayout(model);
        return "index";
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public String addNewItem (@RequestParam("url") String url, Model model) {
        Parser parser = new Parser();
        Item n = parser.parseSearch(url);
        itemRepository.save(n);

        generalLayout(model);

        return "index";
    }

    @PostMapping(path="/delete")
    public String deleteItem (@RequestParam("idToDelete") String idToDelete, Model model) {

        itemRepository.deleteById(Integer.parseInt(idToDelete));

        System.out.println(idToDelete);
        generalLayout(model);
        return "index";
    }
}
