package com.example.WynncraftMod;

import net.minecraft.init.Blocks;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.json.JSONArray;

import java.util.regex.Pattern;

class EventHandlerClass {

    private static Logger logger;
    public Client client;
    public static String specialchar = "§";
    EventHandlerClass(Logger logger, Client client){
        this.logger = logger;
        this.client = client;
        //specialchar=specialchar.replaceAll("[^\\x00-\\x7F]","");
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(event.getType()== ChatType.CHAT) {
            if (isGuildChat(event.getMessage().getUnformattedText())) {
                logger.info("YEA SOMEONE SAID SOMETHING1 " + event.getMessage().getUnformattedText());
                logger.info("GUILDCHAT");
                try {
                    client.sendmsg((TextFormatting.getTextWithoutFormattingCodes(event.getMessage().getUnformattedText())));
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("GOD DAMN ERROR IS " + e.getStackTrace());
                    client.connect();
                    client.sendmsg((TextFormatting.getTextWithoutFormattingCodes(event.getMessage().getUnformattedText())));
                } finally {
                    logger.info("CALLED FINALLY");
                }
            }
        }
    }
    public String intoSentMessage(String msg){

        String[] msgParts = msg.split(specialchar+"3]",2);
        msgParts[0] = TextFormatting.getTextWithoutFormattingCodes(msgParts[0]);
        msgParts[1] = TextFormatting.getTextWithoutFormattingCodes(msgParts[1]);
        msgParts[0] = msgParts[0].replaceAll("[","").replaceAll("]","");
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