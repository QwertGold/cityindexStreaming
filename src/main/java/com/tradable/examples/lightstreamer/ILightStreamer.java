package com.tradable.examples.lightstreamer;

/**
 *
 * Interface for abstracting differences between LightStreamer V5 and V6
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public interface ILightStreamer {

    /**
     * For interfaces we can't specify a constructor, so we have an init method that must be called before any others
     */
    void connect(String user, String session, String pushURL, String adapterSet, IMultiVersionConnectionListener listener);

    void subscribe(String dataAdapter, String item, Mode mode, String[] fields, boolean snapshot, DataListener listener);

    void disconnect();
}
