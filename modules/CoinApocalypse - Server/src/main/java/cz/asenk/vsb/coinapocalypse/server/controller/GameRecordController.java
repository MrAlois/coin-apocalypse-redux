package cz.asenk.vsb.coinapocalypse.server.controller;

import cz.asenk.vsb.coinapocalypse.server.model.entites.GameRecord;
import cz.asenk.vsb.coinapocalypse.server.repository.GameRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor

@RestController
public class GameRecordController {
    private final GameRecordRepository repository;

    @PostMapping("/score")
    public void addItem(@RequestBody GameRecord record){
        log.info("Received add request for GameRecord. Payload = {}", record);
        repository.save(record);
    }

    @GetMapping("/score")
    public List<GameRecord> getItems(){
        log.info("Received get request for GameRecords..");
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/score/{id}")
    public Optional<GameRecord> getItem(@PathVariable("id") Long id){
        log.info("Received get request for GameRecord. Id = {}", id);
        return repository.findById(id);
    }

    @PutMapping("/score/{id}")
    public void updateItem(@PathVariable("id") Long id, @RequestBody GameRecord record){
        log.info("Received update request for GameRecord. Id = {}, with Payload = {}", id, record);
        val updatedItem = repository.findById(id);

        updatedItem.ifPresentOrElse(gameRecord -> {
            val updated = gameRecord.toBuilder()
                    .gameState(record.getGameState())
                    .playerName(record.getPlayerName())
                    .score(record.getScore())
                    .build();

            repository.save(updated);
            log.info("GameRecord sucessfully updated. {}", updated.toString());
        }, () -> log.error("GameRecord couldn't be saved to repository. {}", updatedItem));
    }

    @DeleteMapping("/score/{id}/delete")
    public void deleteItem(@PathVariable("id") Long id){
        log.info("Received delete request for GameRecord. Id = {}", id);
        val itemToBeDeleted = repository.findById(id);

        itemToBeDeleted.ifPresentOrElse(gameRecord -> {
            repository.delete(gameRecord);
            log.info("GameRecord has been deleted. {}", itemToBeDeleted);
        }, () -> log.error("GameRecord couldn't be deleted. {}", itemToBeDeleted));
    }
}
