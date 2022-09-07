package com.mdhscompsci.mdhsmod;

import com.mdhscompsci.mdhsmod.core.init.EntityInit;
import com.mdhscompsci.mdhsmod.common.item.emptyWaterGun;
import com.mdhscompsci.mdhsmod.core.init.ItemInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MdhsMod.MOD_ID)
public class MdhsMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mdhsmod";

    public MdhsMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ItemInit.ITEMS.register(eventBus);
        EntityInit.ENTITIES.register(eventBus);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        emptyWaterGun.register(bus);
        bus.addListener(this::setup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

}
