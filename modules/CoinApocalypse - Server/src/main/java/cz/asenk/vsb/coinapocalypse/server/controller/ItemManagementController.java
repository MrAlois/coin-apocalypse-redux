package cz.asenk.vsb.coinapocalypse.server.controller;

import cz.asenk.vsb.coinapocalypse.server.model.Item;
import cz.asenk.vsb.coinapocalypse.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor

@RestController
public class ItemManagementController {
    private ItemRepository repository;

    @PostMapping("/item")
    public void addItem(@RequestBody Item item){
        repository.save(item);
    }

    @GetMapping("/item")
    public List<Item> getItems(){
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/item/{id}")
    public Optional<Item> getItem(@PathVariable("id") Long id){
        return repository.findById(id);
    }

    @PutMapping("/item/{id}")
    public void updateItem(@PathVariable("id") Long id, @RequestBody Item item){
        var updatedItem = repository.findById(id).get().builder()
                .name(item.getName())
                .effect(item.getEffect())
                .price(item.getPrice())
                .build();

        repository.save(updatedItem);
    }

    @DeleteMapping("/item/{id}/delete")
    public void deleteItem(@Param("id") Long id){
        repository.delete(repository.findById(id).get());
    }
}
