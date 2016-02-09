package com.tradable.examples;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightstreamer.client.ClientListener;
import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Klaus Groenbaek
 *         Created 09/02/16.
 */
public class StreamExample {


    public static final String REST_ENDPOINT = "https://ciapi.cityindex.com/TradingApi";
    public static final String STREAMING_ENDPOINT = "https://push.cityindex.com";

    public static void main(String[] args) throws Exception {

        String userName = "DM438269";
        String password = "trade123";

        RestTemplate template = new RestTemplate();
        ApiLogOnRequestDTO request = new ApiLogOnRequestDTO().setAppVersion("1").setAppKey("tradable").setUserName(userName)
                .setPassword(password);
        ResponseEntity<ApiLogOnResponseDTO> response = template.postForEntity(uri("/session"), request, ApiLogOnResponseDTO.class);
        String session = response.getBody().getSession();

        LightstreamerClient lightStreamClient = new LightstreamerClient(STREAMING_ENDPOINT, "STREAMINGALL");
        lightStreamClient.connectionDetails.setUser(userName);
        lightStreamClient.connectionDetails.setPassword(session);
        lightStreamClient.addListener(new MyClientListener());


        Subscription orderSubscription = new Subscription("DISTINCT", "ORDERS", new String[] {"OrderId", "MarketId", "Status",
                "Type", "OriginalQuantity", "Quantity", "LastChangedTime",
                "ExecutionPrice"    // values for this field is always  "CityIndex.Atlas.Business.OrderExecutionPrice"
        });
        orderSubscription.setDataAdapter("ORDERS");
        final ObjectMapper objectMapper = new ObjectMapper();
        final Semaphore snapshotSemaphore = new Semaphore(0);
        AbstractSubscriptionListener orderListener = new AbstractSubscriptionListener() {
            @Override
            public void onItemUpdate(ItemUpdate itemUpdate) {

                try {
                    String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(itemUpdate.getFields());
                    System.out.println("Order update " + s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onEndOfSnapshot(String itemName, int itemPos) {
                // some times this is not called, and when it is not streaming does not work
                snapshotSemaphore.release();
            }
        };
        orderSubscription.addListener(orderListener);

        lightStreamClient.subscribe(orderSubscription);
        lightStreamClient.connect();

        boolean gotPermit = snapshotSemaphore.tryAcquire(10, TimeUnit.SECONDS);
        if (!gotPermit) {
            System.err.println("onEndOfSnapshot not called within 10 seconds after connecting. Streaming will not work!!!");
        } else {
            System.out.println("onEndOfSnapshot called.");
        }

        int c;
        do {
            c = System.in.read();
        } while (c != 'q');
        System.out.println("Quitting....");
        lightStreamClient.disconnect();
    }

    private static URI uri(String relativeUrl) {
        try {
            return new URI(REST_ENDPOINT + relativeUrl);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }


    private static class MyClientListener implements ClientListener {
        @Override
        public void onListenEnd(LightstreamerClient lightstreamerClient) {
        }

        @Override
        public void onListenStart(LightstreamerClient lightstreamerClient) {
        }

        @Override
        public void onServerError(int i, String s) {
            System.err.println(s);
        }

        @Override
        public void onStatusChange(String s) {
            System.out.println("Status is " + s);
        }

        @Override
        public void onPropertyChange(String s) {

        }
    }
}
