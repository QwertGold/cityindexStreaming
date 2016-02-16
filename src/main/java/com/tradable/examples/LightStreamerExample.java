package com.tradable.examples;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tradable.examples.dto.ApiLogOnRequestDTO;
import com.tradable.examples.dto.ApiLogOnResponseDTO;
import com.tradable.examples.lightstreamer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public class LightStreamerExample {


    public static void main(String[] args) throws Exception {
        new LightStreamerExample().run();
    }


    public final String REST_ENDPOINT = "https://ciapi.cityindex.com/TradingApi";
    public final String STREAMING_ENDPOINT = "https://push.cityindex.com";
    private RestTemplate template = new RestTemplate();

    public void run() throws Exception {

        String userName = "DM438269";
        String password = "trade123";

        ApiLogOnRequestDTO request = new ApiLogOnRequestDTO().setAppVersion("1").setAppKey("tradable").setUserName(userName)
                .setPassword(password);
        ResponseEntity<ApiLogOnResponseDTO> response = template.postForEntity(uri("/session"), request, ApiLogOnResponseDTO.class);
        String session = response.getBody().getSession();


        ILightStreamer client = new LightStreamerV5();
        client = new LightStreamerV6();
        client.connect(userName, session, STREAMING_ENDPOINT, "STREAMINGALL", new MultiVersionConnectionListener());

        String[] fields = {"OrderId", "MarketId", "Status",
                "Type", "OriginalQuantity", "Quantity", "LastChangedTime",
                "ExecutionPrice"};

        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();


        client.subscribe("ORDERS", "ORDERS", Mode.DISTINCT, fields, true, new DataListener() {
            @Override
            public void onUpdate(Map<String, String> values) {
                try {
                    String json = writer.writeValueAsString(values);
                    System.out.println(json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSnapshotEnd(int itemPos, String itemName) {
                System.out.println("Snapshot end " + itemName + " " + itemPos);
            }
        });


        TimeUnit.HOURS.sleep(1);
    }

    private URI uri(String relativeUrl) {
        try {
            return new URI(REST_ENDPOINT + relativeUrl);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }


}
