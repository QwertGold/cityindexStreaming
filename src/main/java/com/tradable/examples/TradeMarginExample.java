package com.tradable.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tradable.examples.dto.JSONOrderSide;
import com.tradable.examples.lightstreamer.*;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * This example illustrates an error on the Trade Margin Stream
 * It looks like you only get updates form the most resent trade when subscribing to the Trade Margin Stream
 * This example places a trade and wait for the event on the Trade Margin Stream, and then places another trade and waits
 * for the event. After both trades are placed, it will print the time since the last update to each trade.
 * The output will look like this
 * Status is CONNECTING
 Status is CONNECTED:STREAM-SENSING
 Status is CONNECTED:HTTP-STREAMING
 Trade Placed ApiTradeOrderResponseDTO(Status=PENDING, StatusReason=1, OrderId=554286093, Orders=[ApiOrderResponseDTO(OrderId=554286093, StatusReason=1, Status=OPEN, OrderTypeId=MARKET, Price=1.12519, Quantity=1000.0, TriggerPrice=0.0, CommissionCharge=0.0, IfDone=[], GuaranteedPremium=0.0, OCO=null)], Quote=null, Actions=[ApiOrderActionResponseDTO(ActionedOrderId=554286093, ActioningOrderId=554286093, Quantity=1000.0, ProfitAndLoss=0.0, ProfitAndLossCurrency=null, OrderActionTypeId=Opening_Order)], ErrorMessage=null)
 Trade Placed ApiTradeOrderResponseDTO(Status=PENDING, StatusReason=1, OrderId=554286094, Orders=[ApiOrderResponseDTO(OrderId=554286094, StatusReason=1, Status=OPEN, OrderTypeId=MARKET, Price=1.55699, Quantity=1000.0, TriggerPrice=0.0, CommissionCharge=0.0, IfDone=[], GuaranteedPremium=0.0, OCO=null)], Quote=null, Actions=[ApiOrderActionResponseDTO(ActionedOrderId=554286094, ActioningOrderId=554286094, Quantity=1000.0, ProfitAndLoss=0.0, ProfitAndLossCurrency=null, OrderActionTypeId=Opening_Order)], ErrorMessage=null)
 Starting new thread to periodic pull trade margin data every 30 seconds.
 Click q to quit
 Last update to order 554286093 was 0 seconds ago.
 Last update to order 554286094 was 0 seconds ago.
 Last update to order 554286093 was 30 seconds ago.
 Last update to order 554286094 was 30 seconds ago.
 Last update to order 554286093 was 60 seconds ago.
 Last update to order 554286094 was 29 seconds ago.
 Last update to order 554286093 was 90 seconds ago.
 Last update to order 554286094 was 29 seconds ago.
 Last update to order 554286093 was 120 seconds ago.
 Last update to order 554286094 was 29 seconds ago.
 Last update to order 554286093 was 150 seconds ago.
 Last update to order 554286094 was 29 seconds ago.
 *
 * Notice that after trade 554286094 is placed, we get no more stream events for 554286093.
 *
 * @author Klaus Groenbaek
 *         Created 10/02/16.
 */
@Slf4j
public class TradeMarginExample extends AbstractCityIndexExample {

    public static void main(String[] args) throws Exception {
        new TradeMarginExample().runExample();
    }

    @Override
    public ILightStreamer createClient() {
        return new LightStreamerV5();
    }

    @Override
    protected void run(ILightStreamer client) throws Exception {

        // map from orderId to stream data (+time) so we can check when the last stream update was
        ConcurrentHashMap<Integer, Entry> streamUpdates = new ConcurrentHashMap<>();

        ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
        client.subscribe("TRADEMARGIN", "TRADEMARGIN", Mode.MERGE, new String[]{"OrderId", "OTEConverted", "PriceCalculatedAt"}, false, new DataListener() {
            @Override
            public void onUpdate(Map<String, String> values) {
                int orderId = Integer.parseInt(values.get("OrderId"));
                streamUpdates.put(orderId, new Entry(values));
                if (log.isDebugEnabled()) {
                    try {
                        String s = writer.writeValueAsString(values);
                        log.debug(s);

                    } catch (Exception e) {
                        log.debug("Unable to convert message to JSON", e);
                    }
                }
            }

            @Override
            public void onSnapshotEnd(int itemPos, String itemName) {
                System.out.println("on snapshot end " + itemName + " " + itemPos);
            }
        });

        // wait for connection
        sleep(1000);

        List<Integer> orderIds = new ArrayList<>();
        // place trade, marketId 401091573 is EUR/USD FOREX
        int orderId = placeTrade(JSONOrderSide.BUY, 1000, 401091573);
        orderIds.add(orderId);

        // wait until we get an update on the stream for the given order
        Entry entry;
        do {
            sleep(100);
            entry = streamUpdates.get(orderId);
        } while (entry == null);

        // new trade, marketID 401091557 is EUR/CAD FOREX
        orderId = placeTrade(JSONOrderSide.BUY, 1000, 401091557);
        orderIds.add(orderId);
        // wait for the stream to see the second trade
        do {
            sleep(100);
            entry = streamUpdates.get(orderId);
        } while (entry == null);


        // now we have two positions and should get Trade Margin events for both trades
        System.out.println("Starting new thread to periodic pull trade margin data every 30 seconds.");
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    for (Integer id : orderIds) {
                        long delta = System.nanoTime() - streamUpdates.get(id).time;
                        System.out.println("Last update to order " + id + " was " + TimeUnit.NANOSECONDS.toSeconds(delta) + " seconds ago.");
                    }
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public static class Entry {
        public final Map<String, String> data;
        public final long time = System.nanoTime();

        public Entry(Map<String, String> data) {
            this.data = data;
        }
    }

}
