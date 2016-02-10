package com.tradable.examples;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;

/**
 *
 * On City Index you need the price to place a market order, we therefore need a class to keep track of the latest prices (and auditId)
 * @author Klaus Groenbaek
 *         Created 10/02/16.
 */
public class PriceSubscriptionManager {

    private final ConcurrentHashMap<Integer, LastPrice> prices = new ConcurrentHashMap<>();
    private final Set<Integer> subscriptions = new HashSet<>();
    private LightstreamerClient lightStreamClient;

    public void setClient(LightstreamerClient lightStreamClient) {
        this.lightStreamClient = lightStreamClient;
    }


    public synchronized LastPrice getLastPrice(int marketId) {
        if (!subscriptions.contains(marketId)) {
            Subscription subscription = new Subscription("MERGE", "PRICE." + marketId, new String[]{"MarketId", "Bid", "Offer", "AuditId"});
            subscription.setDataAdapter("PRICES");
            subscription.setRequestedSnapshot("yes");
            subscription.addListener(new AbstractSubscriptionListener() {
                @Override
                public void onItemUpdate(ItemUpdate itemUpdate) {
                    String bidStr = itemUpdate.getValue("Bid");
                    String offerStr = itemUpdate.getValue("Offer");
                    String auditId = itemUpdate.getValue("AuditId");
                    LastPrice lastPrice = prices.get(marketId);
                    double bid = 0;
                    double offer = 0;
                    if (lastPrice != null) {
                        bid = lastPrice.bid;
                        offer = lastPrice.offer;
                    }
                    if (bidStr != null) {
                        bid = Double.parseDouble(bidStr);
                    }
                    if (offerStr != null) {
                        offer = Double.parseDouble(offerStr);
                    }
                    prices.put(marketId, new LastPrice(auditId, bid, offer));
                }
            });
            lightStreamClient.subscribe(subscription);
            subscriptions.add(marketId);
        }

        return prices.get(marketId);
    }


    public static class LastPrice {
        public final double bid;
        public final double offer;
        public final String auditId;

        public LastPrice(String auditId, double bid, double offer) {
            this.auditId = auditId;
            this.bid = bid;
            this.offer = offer;
        }
    }
}
