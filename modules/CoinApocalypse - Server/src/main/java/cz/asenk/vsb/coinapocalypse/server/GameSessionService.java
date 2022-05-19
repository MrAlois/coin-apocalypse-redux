package cz.asenk.vsb.coinapocalypse.server;

import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService {

    @Bean
    public CompletableFuture<String> save(){
        return CompletableFuture.completedFuture("Dobrota");
    }
}
