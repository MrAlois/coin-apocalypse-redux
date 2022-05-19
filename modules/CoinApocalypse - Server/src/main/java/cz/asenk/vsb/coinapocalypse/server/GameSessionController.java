package cz.asenk.vsb.coinapocalypse.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequiredArgsConstructor
public class GameSessionController {
    private final GameRecordRepository gameRecordRepository;
    private final EntityManager entityManager;

    @PostMapping("/sessionend")
    @Transactional
    public void saveGameScore(@RequestBody GameRecord payload){
        var data = new GameRecord(payload.getPlayerName(), payload.getScore(), payload.getGameState());
        log.error("Trying to save data: [{}]", data);
        entityManager.persist(data);
    }

    @GetMapping("/score")
    @Transactional
    public List<GameRecord> getGameRecordsByPlayer(){
        return entityManager.get;
    }

    @GetMapping("/score")
    @Transactional
    public List<GameRecord> getGameRecordsByPlayer(@RequestParam String player){
        return gameRecordRepository.
    }
}
