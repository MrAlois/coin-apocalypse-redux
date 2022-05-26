package cz.asenk.vsb.coinapocalypse.server.controller;

import cz.asenk.vsb.coinapocalypse.server.model.Player;
import cz.asenk.vsb.coinapocalypse.server.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor

@RestController
public class PlayerController {

    private final PlayerRepository repository;

    @PostMapping("/player")
    public void addPlayer(@RequestBody Player player){
        repository.save(player);
    }

    @GetMapping("/player")
    public List<Player> getPlayers(){
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/player/{player}")
    public Player getPlayer(@PathParam("player") Long id){
        return repository.findById(id).get();
    }

    @PutMapping("/player/{player}")
    public void updatePlayer(@PathParam("player") Long id, @RequestBody Player player){
        var updatedPlayer = repository.findById(id).get().builder()
                .name(player.getName())
                .surname(player.getSurname())
                .nickname(player.getNickname())
                .lastScore(player.getLastScore())
                .bestScore(player.getBestScore())
                .build();

        repository.save(updatedPlayer);
    }

    @DeleteMapping("/player/{player}/delete")
    public void deletePlayer(@PathParam("player") Long id){
        repository.delete(repository.findById(id).get());
    }
}
