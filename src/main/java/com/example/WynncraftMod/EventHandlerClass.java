package com.example.WynncraftMod;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.*;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.logging.log4j.Logger;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.json.JSONArray;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

class EventHandlerClass {

    private static Logger logger;
    public Client client;
    public static String specialchar = "§";
    EventHandlerClass(Logger logger){
        this.logger = logger;
        //specialchar=specialchar.replaceAll("[^\\x00-\\x7F]","");
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(event.getType()== ChatType.CHAT) {
            if (isGuildChat(event.getMessage().getUnformattedText())) {
                if(!event.getMessage().getUnformattedText().split(specialchar+"3]",2)[1].substring(1,Math.min(
                                event.getMessage().getUnformattedText().split(specialchar+"3]",2)[1].length(), 10)).equals("[DISCORD]")) {
                    logger.info("YEA SOMEONE SAID SOMETHING1 " + event.getMessage().getUnformattedText());
                    logger.info("GUILDCHAT");
                    if(client.getReadyState()== ReadyState.OPEN){
                        client.sendmsg(intoSentMessage(event.getMessage().getUnformattedText()));
                    }else{
                        if(client.getReadyState()== ReadyState.CLOSED){
                            //AttemptToTellPlayer("CHAT BRIDGE CONNECTION IS DISCONNECTED");
                            //AttemptToTellPlayer("Reconnecting...");
                            reconnect();
                        }
                    }

                }
            }
        }
    }
    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        String ip = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        logger.info("CONNECTED TO"+ip);
        if(ip.substring(ip.length() - 13).equals("wynncraft.com")){
            logger.info("CONNECTED TO WYNNCRAFT");
            reconnect();
            //AttemptToTellPlayer("Reconnecting...");
        }
        //connectAgainIfNeeded();
    }
    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        logger.info("DISCONNECTED FROM SERVER");
        client.close(1000,"Disconnected from world");
    }
    public void reconnect(){
        try {
            client = new Client(new URI("ws://eheh.glitch.me/ws:3000"), logger);
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void AttemptToTellPlayer(String msg){
        try{
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            logger.info("TRIED TO TELL PLAYER "+msg);
        }
    }
    public String intoSentMessage(String msg){

        String[] msgParts = msg.split(specialchar+"3]",2);
        msgParts[0] = TextFormatting.getTextWithoutFormattingCodes(msgParts[0]);
        msgParts[1] = TextFormatting.getTextWithoutFormattingCodes(msgParts[1]);
        msgParts[0] = msgParts[0].replace("[","");
        msgParts[1] = msgParts[1].substring(1);
        JSONArray jArr = new JSONArray(msgParts);
        return jArr.toString();

    }
    public boolean isGuildChat(String thing){
        if(thing.contains(specialchar+"3[")){
            if(thing.contains(specialchar+"3]")){
                return true;
            }
        }
        /*else{
            logger.info("doesnt contain "+"?3[");
        }
        if(thing.contains("?3]")){
            return true;
        }else{
            logger.info("doesnt contain "+"?3]");
        }
        boolean isGuild = Pattern.compile("(^&3\\[(&b?{0,5})?(&3)?(&o)?[\\w ]*?(&3)?\\])(?<!&3\\[Parkour\\])|(^&3You were not in the territory)"
                .replace("&", specialchar)).matcher(thing.getUnformattedText()).find();
        return isGuild;*/
        return false;
    }
}