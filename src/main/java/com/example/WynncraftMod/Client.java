package com.example.WynncraftMod;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.MinecraftForgeClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Client extends WebSocketClient {

    private Logger log;
    public String lastdiscordmsg = "";
    public Client(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public Client(URI serverURI,Logger log) {
        super(serverURI, (Map<String, String>)(new HashMap<String, String>() {{
            put("User-Agent", "me");
        }}));
        this.log = log;
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
        this.log.info("STARTED A CLIENT");
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        //send("Client Joined");
        log.info("new connection opened");
        log.info("new connection opened");
        log.info("new connection opened");
        log.info("new connection opened");
        log.info("new connection opened");
        log.info("new connection opened");
        //AttemptToTellPlayer("CHAT BRIDGE CONNECTED");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        log.info("closed with exit code " + code + " additional info: " + reason + "and it was "+remote+"ly because of the server");
        if(!reason.equals("Disconnected from world")) {
            //AttemptToTellPlayer("CHAT BRIDGE DISCONNECTED");
            //this.reconnect();
        }else{
            log.info("DISCONNECTED FROM WORLD");
        }
    }
    public void AttemptToTellPlayer(String msg){
        try{
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            log.info("TRIED TO TELL PLAYER "+msg);
        }
    }

    public void sendmsg(String msg){
        log.info("SENT A MSG "+msg);
        send(msg);
    }

    @Override
    public void onMessage(String message) {
        log.info("received message: " + message);
        JSONObject parsedMessage = new JSONObject(message);
        if(parsedMessage.has("message")){
            String ip = Minecraft.getMinecraft().getCurrentServerData().serverIP;
            log.info("got the msg but idk if im in right server");
            if(ip.substring(ip.length() - 13).equals("wynncraft.com")) {
                log.info("GONNA SAY: " + "/g " + parsedMessage.getString("message"));
                lastdiscordmsg = parsedMessage.getString("message");
                Minecraft.getMinecraft().player.sendChatMessage("/g " + parsedMessage.getString("message"));
                log.info("SAID:  " + "/g " + parsedMessage.getString("message"));
            }
        }
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
        log.info(ex.getStackTrace());
        ex.printStackTrace();
    }
}