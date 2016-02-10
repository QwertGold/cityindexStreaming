package com.tradable.examples;

import com.lightstreamer.client.ClientListener;
import com.lightstreamer.client.LightstreamerClient;

/**
 * @author Klaus Groenbaek
 *         Created 10/02/16.
 */
class MyClientListener implements ClientListener {
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
