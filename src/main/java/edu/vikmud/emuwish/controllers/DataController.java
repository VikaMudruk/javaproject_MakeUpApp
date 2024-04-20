package edu.vikmud.emuwish.controllers;

import com.google.common.collect.Lists;
import edu.vikmud.emuwish.models.Item;
import edu.vikmud.emuwish.services.ExcelService;
import edu.vikmud.emuwish.services.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ExcelService excelService;

    public void generalLayout(Model model){
        List<Item> wishlist = selectItems();

        model.addAttribute("wishlist", wishlist);
    }
    public List<Item> selectItems() {
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

    @PostMapping("/excel")
    public ResponseEntity<byte[]> excelify(Model model){

        try {
            List<Item> wishItems = selectItems();
            byte[] excel = excelService.createExcelFile(wishItems);
            //byte[] createdFile = excelService.getExcelFile();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "wishlist.xlsx");
            return ResponseEntity.ok().headers(headers).body(excel);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
