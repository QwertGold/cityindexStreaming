package com.tradable.examples.lightstreamer;

import com.lightstreamer.client.ClientListener;
import com.lightstreamer.client.LightstreamerClient;
import com.lightstreamer.ls_client.ConnectionListener;
import com.lightstreamer.ls_client.PushConnException;
import com.lightstreamer.ls_client.PushServerException;

/**
 *
 * The connection semantics for V5 and V6 of LightStreamer is rather different, V5 uses URLConnection and V6 uses
 * Netty, so the connection listener classes are in no way similar. This classes implement both interfaces, so we don't
 * need to change the code when switching between API implementations
 *
 *         Created 16/02/16.
 */
public class MultiVersionConnectionListener implements IMultiVersionConnectionListener {

    // ----- V5 methods
    @Override
    public void onActivityWarning(boolean b) {

    }

    @Override
    public void onConnectionEstablished() {
        System.out.println("Established");
    }

    @Override
    public void onSessionStarted(boolean b) {
        System.out.println("Session started " + b);
    }

    @Override
    public void onNewBytes(long l) {

    }

    @Override
    public void onDataError(PushServerException e) {
        e.printStackTrace();
    }

    @Override
    public void onClose() {
        System.out.println("Closed");
    }

    @Override
    public void onEnd(int i) {

    }

    @Override
    public void onFailure(PushServerException e) {
        e.printStackTrace();
    }

    @Override
    public void onFailure(PushConnException e) {
        e.printStackTrace();
    }

    // ---- V6 methods


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
