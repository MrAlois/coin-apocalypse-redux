package cz.asenk.vsb.coinapocalypse.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.asenk.vsb.coinapocalypse.game.entities.Record;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerConnector {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String baseUrl = "";

    public ServerConnector(String url){
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(url);
        Invocation.Builder request = resource.request().accept(MediaType.APPLICATION_JSON);
    }

    public static void withUrl(String url){
        baseUrl = url;
    }

    public static void sendRecord(Record record){
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(baseUrl).path("/score");

        try {
            var serializedToJson = mapper.writeValueAsString(record);
            log.debug("GameRecord serialized to json, Payload=[{}]", serializedToJson);

            resource.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(serializedToJson, MediaType.APPLICATION_JSON));

        } catch (JsonProcessingException e) {
            log.warn("GameRecord couldn't be saved to respository / serialized.", e);
            throw new RuntimeException(e);
        }
    }
    public static Invocation.Builder getPingBuilder() {
        return ClientBuilder.newClient().target("http://localhost:8080").request().accept(MediaType.APPLICATION_JSON);
    }
}
