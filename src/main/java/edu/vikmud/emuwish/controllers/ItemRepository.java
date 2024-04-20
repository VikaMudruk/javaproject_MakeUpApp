package edu.vikmud.emuwish.controllers;


import edu.vikmud.emuwish.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ItemRepository extends CrudRepository<Item, Integer> {

}
