package cz.asenk.vsb.coinapocalypse.webclient;

import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PingerScheduler {
    private static final String URL = "http://localhost:8080";
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);;

    public static void start(){
        Runnable serverPinger = () -> {
            log.info("Sending ping request to server: {}", URL);
            var response = ServerConnector.getPingBuilder().get();

            if(response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL){
                log.info("Ping request received! Connection established.");
            } else {
                log.warn("Coudln't connect to server!");
            }
        };

        scheduler.scheduleAtFixedRate(serverPinger, 0, 10, TimeUnit.SECONDS);
        scheduler.schedule(scheduler::shutdown, 1, TimeUnit.MINUTES);
    }

    public static void stop(){
        scheduler.shutdown();
    }
}
