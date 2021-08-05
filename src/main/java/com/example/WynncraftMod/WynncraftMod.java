package com.example.WynncraftMod;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;


@Mod(modid = WynncraftMod.MODID, name = WynncraftMod.NAME, version = WynncraftMod.VERSION)
public class WynncraftMod
{
    public static final String MODID = "wynncraftmod";
    public static final String NAME = "Wynncraft Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;
    private static EventHandlerClass eventHandlerClass;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLLoadCompleteEvent event)
    {
        org.slf4j.Logger logger2 = LoggerFactory.getLogger(WynncraftMod.class);
        logger2.info("TES");
        // some example code
        //LoggerFactory.getLogger(WynncraftMod.class);
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        logger.info("TEST");
        System.out.println("THISISANOTHERTEST");
/*        if(!(client!=null)) {
            //ws://gem-cairnsmore.glitch.me/ws
            try {
                client = new Client(new URI("ws://eheh.glitch.me/ws"), logger);
                client.connect();
                logger.info("THISISANOTHERTEST2");
                MinecraftForge.EVENT_BUS.register(eventHandlerClass);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            logger.info("THIS MFER TRIED TO LOAD TWICE");
        }*/
        eventHandlerClass = new EventHandlerClass(logger);
        MinecraftForge.EVENT_BUS.register(eventHandlerClass);
/*        try {
            client = new Client(new URI("ws://eheh.glitch.me:80"), logger);
            client.connect();
            logger.info("THISISANOTHERTEST2");
            eventHandlerClass = new EventHandlerClass(logger,client);
            MinecraftForge.EVENT_BUS.register(eventHandlerClass);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/


    }



}