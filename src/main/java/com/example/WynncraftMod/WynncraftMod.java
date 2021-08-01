package com.example.WynncraftMod;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = WynncraftMod.MODID, name = WynncraftMod.NAME, version = WynncraftMod.VERSION)
public class WynncraftMod
{
    public static final String MODID = "wynncraftmod";
    public static final String NAME = "Wynncraft Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    class HandleEventsClass {
        @SubscribeEvent
        public void pickupItem(EntityItemPickupEvent event) {
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
            System.out.println("Item picked up!");
        }
    }
}