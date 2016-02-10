package com.tradable.examples;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;

/**
 * @author Klaus Groenbaek
 *         Created 09/02/16.
 */
public class OrdersStreamExample extends AbstractCityIndexExample {



    public static void main(String[] args) throws Exception {

        new OrdersStreamExample().runExample();
    }


    @Override
    public void run(LightstreamerClient lightStreamClient) throws Exception {

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
                System.out.println("onEndOfSnapshot called.");
                snapshotSemaphore.release();
            }
        };
        orderSubscription.addListener(orderListener);

        lightStreamClient.subscribe(orderSubscription);
        lightStreamClient.connect();

        boolean gotPermit = snapshotSemaphore.tryAcquire(10, TimeUnit.SECONDS);
        if (!gotPermit) {
            System.err.println("onEndOfSnapshot not called within 10 seconds after connecting. Streaming will not work!!!");
        }
    }
}
