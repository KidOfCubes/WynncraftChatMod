package com.example.WynncraftMod;

import net.minecraft.init.Blocks;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

import java.util.regex.Pattern;

class EventHandlerClass {

    private static Logger logger;
    public Client client;
    public static String specialchar = "§";
    EventHandlerClass(Logger logger, Client client){
        this.logger = logger;
        this.client = client;
        specialchar=specialchar.replace("Â","");
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(event.getType()== ChatType.CHAT) {
            logger.info("CHAT MSG IS " + event.getMessage().getUnformattedText());
            logger.info("IT DOESNT CONTAIN "+specialchar +event.getMessage().getUnformattedText().contains(specialchar));
            logger.info("IT DOESNT CONTAIN "+specialchar+"3["+event.getMessage().getUnformattedText().contains(specialchar+"3["));
            logger.info("IT DOESNT CONTAIN "+specialchar+"3]"+event.getMessage().getUnformattedText().contains(specialchar+"3]"));
            if (isGuildChat(event.getMessage())) {
                logger.info("YEA SOMEONE SAID SOMETHING1 " + event.getMessage().getUnformattedText());
                logger.info("GUILDCHAT");
                try {
                    logger.info("CHAT SEND MSG1");
                    client.sendmsg(event.getMessage().getUnformattedText());
                    logger.info("CHAT SEND MSG2"); 
                } catch (Exception e) {
                    logger.info("IT WASNT CONNECTED NOW IT IS1");
                    e.printStackTrace();
                    logger.info("GOD DAMN ERROR IS " + e.getStackTrace());
                    client.connect();
                    client.sendmsg(event.getMessage().getUnformattedText());
                    logger.info("IT WASNT CONNECTED NOW IT IS2");
                } finally {
                    logger.info("CALLED FINALLY");
                }
                logger.info("YEA SOMEONE SAID SOMETHING2 " + event.getMessage().getUnformattedText());
            }else{
                logger.info("NOT A GUILD CHAT");
            }
        }
    }
    public boolean isGuildChat(ITextComponent thing){
/*        if(thing.contains("§3[")){
            return true;
        }else{
            logger.info("doesnt contain "+"§3[");
        }
        if(thing.contains("§3]")){
            return true;
        }else{
            logger.info("doesnt contain "+"§3]");
        }*/
        boolean isGuild = Pattern.compile("(^&3\\[(&b★{0,5})?(&3)?(&o)?[\\w ]*?(&3)?\\])(?<!&3\\[Parkour\\])|(^&3You were not in the territory)"
                .replace("&", specialchar)).matcher(thing.getUnformattedText()).find();
        return isGuild;
    }
}