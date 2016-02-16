package com.tradable.examples.lightstreamer;

import java.util.Map;

/**
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public interface DataListener {

    void onUpdate(Map<String, String> values);

    void onSnapshotEnd(int itemPos, String itemName);

}
