package cz.asenk.vsb.coinapocalypse.server.controller;

import cz.asenk.vsb.coinapocalypse.server.model.GameRecord;
import cz.asenk.vsb.coinapocalypse.server.repository.GameRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor

@RestController
public class GameRecordController {
    private final GameRecordRepository repository;

    @PostMapping("/score")
    public void addItem(@RequestBody GameRecord record){
        repository.save(record);
    }

    @GetMapping("/score")
    public List<GameRecord> getItems(){
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/score/{id}")
    public Optional<GameRecord> getItem(@PathVariable("id") Long id){
        return repository.findById(id);
    }

    //TODO Better new input checking
    @PutMapping("/score/{id}")
    public void updateItem(@PathVariable("id") Long id, @RequestBody GameRecord record){
        var updatedItem = repository.findById(id).get().builder()
                .gameState(record.getGameState())
                .playerName(record.getPlayerName())
                .score(record.getScore())
                .build();

        repository.save(updatedItem);
    }

    @DeleteMapping("/score/{id}/delete")
    public void deleteItem(@PathVariable("id") Long id){
        repository.delete(repository.findById(id).get());
    }
}
