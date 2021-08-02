package com.example.WynncraftMod;

import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

class EventHandlerClass {

    private static Logger logger;
    EventHandlerClass(Logger logger){
        this.logger = logger;
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(isGuildChat(event.getMessage().getUnformattedText())){
            logger.info(event.getMessage().getUnformattedText());
        }
    }
    public boolean isGuildChat(String thing){
        if(thing.contains("§3[")){
            if(thing.contains("§3]§r§b")){
                return true;
            }
        }
        return false;
    }
}