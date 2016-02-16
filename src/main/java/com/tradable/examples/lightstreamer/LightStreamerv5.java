package com.tradable.examples.lightstreamer;

import java.util.HashMap;
import java.util.Map;

import com.lightstreamer.ls_client.*;

/**
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public class LightStreamerV5 implements ILightStreamer {

    private LSClient client;

    @Override
    public void connect(String user, String session, String pushURL, String adapterSet, IMultiVersionConnectionListener listener)  {
        client = new LSClient();
        ConnectionInfo info = new ConnectionInfo();
        info.password = session;
        info.user = user;
        info.pushServerUrl = pushURL;
        info.adapter = adapterSet;
        try {
            client.openConnection(info, listener);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect ", e);
        }
    }

    @Override
    public void subscribe(String dataAdapter, String item, Mode mode, String[] fields, boolean snapshot, DataListener listener)  {
        try {
            ExtendedTableInfo tableInfo = new ExtendedTableInfo(new String[]{item}, mode.asString(), fields, snapshot);
            tableInfo.setDataAdapter(dataAdapter);
            getClient().subscribeTable(tableInfo, new DataHandlerAdapter(listener, fields), false);
        } catch (Exception e) {
            throw new RuntimeException("Unable to add subscription", e);
        }
    }

    @Override
    public void disconnect() {
        client.closeConnection();
    }

    public LSClient getClient() {
        if (client == null) {
            throw new IllegalStateException("You must call connect first.");
        }
        return client;
    }

    private static class DataHandlerAdapter implements HandyTableListener {
        private final DataListener listener;
        private final String[] fields;

        public DataHandlerAdapter(DataListener listener, String[] fields) {
            this.listener = listener;
            this.fields = fields;
        }

        @Override
        public void onUpdate(int itemPos, String s, UpdateInfo updateInfo) {
            Map<String, String> data = new HashMap<>();
            for (int i=1; i<= updateInfo.getNumFields(); i++) {
                data.put(fields[i-1], updateInfo.getNewValue(i));
            }
            listener.onUpdate(data);
        }

        @Override
        public void onSnapshotEnd(int itemPos, String itemName) {
            listener.onSnapshotEnd(itemPos, itemName);
        }

        @Override
        public void onRawUpdatesLost(int i, String s, int i1) {

        }

        @Override
        public void onUnsubscr(int i, String s) {

        }

        @Override
        public void onUnsubscrAll() {

        }
    }
}
