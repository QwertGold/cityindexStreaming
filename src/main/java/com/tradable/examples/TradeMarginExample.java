package com.tradable.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;
import com.tradable.examples.dto.JSONOrderSide;
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
    protected void run(LightstreamerClient lightStreamClient) throws Exception {

        // map from orderId to stream data (+time) so we can check when the last stream update was
        ConcurrentHashMap<Integer, Entry> streamUpdates = new ConcurrentHashMap<>();

        final ObjectMapper objectMapper = new ObjectMapper();
        Subscription tradeMarginSubscription = new Subscription("MERGE", "TRADEMARGIN", new String[] {"OrderId", "OTEConverted", "PriceCalculatedAt"});
        tradeMarginSubscription.setDataAdapter("TRADEMARGIN");
        tradeMarginSubscription.addListener(new AbstractSubscriptionListener() {
            @Override
            public void onItemUpdate(ItemUpdate itemUpdate) {
                int orderId = Integer.parseInt(itemUpdate.getValue("OrderId"));
                streamUpdates.put(orderId, new Entry(itemUpdate));
                if (log.isDebugEnabled()) {
                    try {
                        String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(itemUpdate.getFields());
                        log.debug(s);

                    } catch (Exception e) {
                        log.debug("Unable to convert message to JSON", e);
                    }
                }

            }
        });
        lightStreamClient.subscribe(tradeMarginSubscription);
        lightStreamClient.connect();

        // wait for light streamer to be connected
        while (!"CONNECTED:HTTP-STREAMING".equals(lightStreamClient.getStatus())) {
            sleep(100);
        }

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
        public final ItemUpdate item;
        public final long time = System.nanoTime();

        public Entry(ItemUpdate item) {
            this.item = item;
        }
    }

}
