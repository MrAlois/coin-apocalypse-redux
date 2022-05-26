package cz.asenk.vsb.coinapocalypse.server.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class GameServerService {
    @Bean
    public CompletableFuture<String> save(){
        return CompletableFuture.completedFuture("Dobrota");
    }
}
