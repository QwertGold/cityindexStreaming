package com.tradable.examples;

import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;
import com.tradable.examples.lightstreamer.DataListener;
import com.tradable.examples.lightstreamer.ILightStreamer;
import com.tradable.examples.lightstreamer.LightStreamerV5;
import com.tradable.examples.lightstreamer.Mode;

/**
 * @author Klaus Groenbaek
 *         Created 09/02/16.
 */
public class OrdersStreamExample extends AbstractCityIndexExample {


    public static void main(String[] args) throws Exception {

        new OrdersStreamExample().runExample();
    }

    @Override
    public ILightStreamer createClient() {
        return new LightStreamerV5();
    }

    @Override
    public void run(ILightStreamer lightStreamClient) throws Exception {
        String[] fields = {"OrderId", "MarketId", "Status",
                "Type", "OriginalQuantity", "Quantity", "LastChangedTime",
                "ExecutionPrice"    // values for this field is always  "CityIndex.Atlas.Business.OrderExecutionPrice"
        };

        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
        final Semaphore snapshotSemaphore = new Semaphore(0);
        lightStreamClient.subscribe("ORDERS", "ORDERS", Mode.DISTINCT, fields, false, new DataListener() {
            @Override
            public void onUpdate(Map<String, String> values) {
                try {
                    String s = writer.writeValueAsString(values);
                    System.out.println("Order update " + s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSnapshotEnd(int itemPos, String itemName) {
                System.out.println("onEndOfSnapshot called.");
                snapshotSemaphore.release();
            }
        });

        boolean gotPermit = snapshotSemaphore.tryAcquire(10, TimeUnit.SECONDS);
        if (!gotPermit) {
            System.err.println("onEndOfSnapshot not called within 10 seconds after connecting. Streaming will not work!!!");
        }
    }
}
