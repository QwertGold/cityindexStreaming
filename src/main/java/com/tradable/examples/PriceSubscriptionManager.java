package com.tradable.examples;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tradable.examples.lightstreamer.DataListener;
import com.tradable.examples.lightstreamer.ILightStreamer;
import com.tradable.examples.lightstreamer.Mode;

/**
 *
 * On City Index you need the price to place a market order, we therefore need a class to keep track of the latest prices (and auditId)
 * @author Klaus Groenbaek
 *         Created 10/02/16.
 */
public class PriceSubscriptionManager {

    private final ConcurrentHashMap<Integer, LastPrice> prices = new ConcurrentHashMap<>();
    private final Set<Integer> subscriptions = new HashSet<>();
    private ILightStreamer client;

    public void setClient(ILightStreamer lightStreamClient) {
        this.client = lightStreamClient;
    }


    public synchronized LastPrice getLastPrice(int marketId) {
        if (!subscriptions.contains(marketId)) {

            try {
                client.subscribe("PRICES", "PRICE." + marketId, Mode.MERGE, new String[]{"MarketId", "Bid", "Offer", "AuditId"}, true, new DataListener() {
                    @Override
                    public void onUpdate(Map<String, String> values) {
                        String bidStr = values.get("Bid");
                        String offerStr = values.get("Offer");
                        String auditId = values.get("AuditId");
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

                    @Override
                    public void onSnapshotEnd(int itemPos, String itemName) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
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
