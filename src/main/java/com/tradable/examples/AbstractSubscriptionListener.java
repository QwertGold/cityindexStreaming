package com.tradable.examples;

import com.lightstreamer.client.Subscription;
import com.lightstreamer.client.SubscriptionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * abstract Subscription listener that implements everything except the onItemUpdate method
 *
 * @author Klaus Groenbaek
 *         Created 01/12/15.
 */
@Slf4j
public abstract class AbstractSubscriptionListener implements SubscriptionListener {

    @Override
    public void onClearSnapshot(String itemName, int itemPos) {
        log.debug("onClearSnapshot itemName " + itemName + ", position " + itemPos);
    }

    @Override
    public void onCommandSecondLevelItemLostUpdates(int lostUpdates, String key) {
        log.warn("onCommandSecondLevelItemLostUpdates key " + key + ", lostUpdates " + lostUpdates);
    }

    @Override
    public void onCommandSecondLevelSubscriptionError(int code, String message, String key) {
        log.warn("onClearSnapshot code " + code + ", key " + key + ", message " + message);
    }

    @Override
    public void onEndOfSnapshot(String itemName, int itemPos) {
        log.debug("onEndOfSnapshot itemName " + itemName + ", position " + itemPos);
    }

    @Override
    public void onItemLostUpdates(String itemName, int itemPos, int lostUpdates) {
        log.warn("onItemLostUpdates itemName " + itemName + ", itemPos " + itemPos + ", lostUpdates " + lostUpdates);
    }

    @Override
    public void onListenEnd(Subscription subscription) {
        log.debug("onListenEnd " + subscription.getItemGroup());
    }

    @Override
    public void onListenStart(Subscription subscription) {
        log.debug("onListenStart " + subscription.getItemGroup());
    }

    @Override
    public void onSubscription() {
        log.debug("onSubscription");
    }

    @Override
    public void onSubscriptionError(int code, String message) {
        log.warn("onSubscriptionError code " + code + ", message " + message);
    }

    @Override
    public void onUnsubscription() {
        log.debug("onUnsubscription");
    }
}
