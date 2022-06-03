package cz.asenk.vsb.coinapocalypse.server.controller;

import cz.asenk.vsb.coinapocalypse.server.model.entites.Item;
import cz.asenk.vsb.coinapocalypse.server.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor

@RestController
public class ItemManagementController {
    private final ItemRepository repository;

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
    public void updateItem(@PathVariable("id") Long id, @RequestBody Item newItem){
        val updatedItem = repository.findById(id);

        updatedItem.ifPresentOrElse(item -> {
            val updated = item.toBuilder()
                    .name(newItem.getName())
                    .effect(newItem.getEffect())
                    .price(newItem.getPrice())
                    .build();

            repository.save(updated);
            log.info("Item sucessfully updated. {}", updated);
        }, () -> log.error("Item couldn't be saved to repository. {}", updatedItem));
    }

    @DeleteMapping("/item/{id}/delete")
    public void deleteItem(@Param("id") Long id){
        val itemToBeDeleted = repository.findById(id);

        itemToBeDeleted.ifPresentOrElse(item -> {
            repository.delete(item);
            log.info("Item has been deleted. {}", itemToBeDeleted);
        }, () -> log.error("Item couldn't be deleted. {}", itemToBeDeleted));
    }
}
