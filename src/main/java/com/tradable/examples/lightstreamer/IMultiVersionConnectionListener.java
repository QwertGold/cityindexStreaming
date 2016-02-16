package com.tradable.examples.lightstreamer;

import com.lightstreamer.client.ClientListener;
import com.lightstreamer.ls_client.ConnectionListener;

/**
 *
 * The connection semantics for V5 and V6 of LightStreamer is rather different, V5 uses URLConnection and V6 uses
 * Netty, so the connection listener classes are in no way similar. This classes implement both interfaces, so we don't
 * need to change the code when switching between API implementations
 *
 *         Created 16/02/16.
 */
public interface IMultiVersionConnectionListener extends ConnectionListener, ClientListener {
}
