package biraw.online.b_s_SleepingBag;


import biraw.online.b_s_SleepingBag.Listeners.BlockBreakListener;
import biraw.online.b_s_SleepingBag.Listeners.GetUpListener;
import biraw.online.b_s_SleepingBag.Listeners.SleepListener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;


public final class B_s_SleepingBag extends JavaPlugin{
    private static B_s_SleepingBag instance;
    public static B_s_SleepingBag getInstance(){return instance;}
    public static NamespacedKey bedKey;

    public static void Log(String message, Level level){
        instance.getServer().getLogger().log(level,"[SpeedBuilders] "+message);
    }

    @Override
    public void onEnable() {
        instance = this;
        bedKey = new NamespacedKey(this,"sleeping_bag");

        getServer().getPluginManager().registerEvents(new SleepListener(),this);
        getServer().getPluginManager().registerEvents(new GetUpListener(),this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(),this);

        getLogger().info(" ");
        getLogger().info("O=========================================================O");
        getLogger().info("    The B's SleepingBag plugin has loaded successfully!");
        getLogger().info("       This is B's SleepingBag for Minecraft JDK 21."    );
        getLogger().info("                       Author: BiRaw");
        getLogger().info("         Discord: https://discord.gg/XwFqu7uahX :>");
        getLogger().info("O=========================================================O");
        getLogger().info(" ");
    }

    @Override
    public void onDisable() {
        getLogger().info("B's SleepingBag has been disabled!");
    }
}
