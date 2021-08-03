package com.example.WynncraftMod;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.apache.logging.log4j.Logger;

public class Client extends WebSocketClient {

    private Logger log;
    public Client(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public Client(URI serverURI,Logger log) {
        super(serverURI, (Map<String, String>)(new HashMap<String, String>() {{
            put("User-Agent", "me");
        }}));
        this.log = log;
        this.log.info("STARTED A CLIENT");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Client Joined");
        log.info("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("closed with exit code " + code + " additional info: " + reason);
    }

    public void sendmsg(String msg){
        log.info("SENT A MSG "+msg);
        send(msg);
    }

    @Override
    public void onMessage(String message) {
        log.info("received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        log.info("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
        log.info("an error occurredan error occurredan error occurredan error occurredan error occurredan error occurred:" + ex);
    }
}