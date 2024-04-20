package edu.vikmud.emuwish.services;

import edu.vikmud.emuwish.models.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Parser {

    public Item parseSearch(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

            String name = doc.select(".product-item__name").first().ownText();
            String price = doc.select(".price_item").get(2).ownText();
            String old_price = doc.select(".price_item").get(3).ownText();

            Item item = new Item();
            item.setUrl(url);
            item.setName(name);
            item.setPrice(Integer.parseInt(price) > 0 ? Integer.parseInt(price) : null);
            item.setOldPrice(Integer.parseInt(old_price));

            return item;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
