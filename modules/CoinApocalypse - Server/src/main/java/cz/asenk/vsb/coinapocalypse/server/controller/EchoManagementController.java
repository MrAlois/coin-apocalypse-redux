package cz.asenk.vsb.coinapocalypse.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EchoManagementController {
    @GetMapping("/")
    public String ping(){
        log.info("Received ping request..");
        return "Connection OK";
    }
}
