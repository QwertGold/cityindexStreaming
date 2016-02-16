package com.tradable.examples.lightstreamer;

import com.lightstreamer.client.ItemUpdate;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.client.Subscription;
import com.tradable.examples.AbstractSubscriptionListener;

/**
 * LightStreamer V6 Api
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public class LightStreamerV6 implements ILightStreamer {


    private LightstreamerClient client;

    @Override
    public void connect(String user, String session, String pushURL, String adapterSet, IMultiVersionConnectionListener listener) {
        client = new LightstreamerClient(pushURL, adapterSet);
        client.connectionDetails.setUser(user);
        client.connectionDetails.setPassword(session);
        client.addListener(listener);
        client.connect();
    }

    @Override
    public void subscribe(String dataAdapter, String item, Mode mode, String[] fields, boolean snapshot, DataListener listener) {

        Subscription subscription = new Subscription(mode.asString(), item, fields);
        subscription.setRequestedSnapshot(snapshot ? "yes" : "no");
        subscription.setDataAdapter(dataAdapter);

        AbstractSubscriptionListener orderListener = new AbstractSubscriptionListener() {
            @Override
            public void onItemUpdate(ItemUpdate itemUpdate) {
                listener.onUpdate(itemUpdate.getFields());
            }

            @Override
            public void onEndOfSnapshot(String itemName, int itemPos) {
                listener.onSnapshotEnd(itemPos, itemName);
            }
        };
        subscription.addListener(orderListener);
        client.subscribe(subscription);
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }

    public LightstreamerClient getClient() {
        if (client == null) {
            throw new IllegalStateException("You must call connect first.");
        }
        return client;
    }
}
