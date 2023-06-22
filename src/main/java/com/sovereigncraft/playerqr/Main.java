package com.sovereigncraft.playerqr;

import com.google.common.cache.AbstractCache;
import com.sovereigncraft.playerqr.command.PlayerQRCode;
import com.sovereigncraft.playerqr.command.getMapInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import com.sovereigncraft.playerqr.command.QRCode;
import com.sovereigncraft.playerqr.event.MapInitialize;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.io.File;
import java.util.HashMap;

public class Main extends JavaPlugin {

    @Getter @Setter
    private static Main instance;
    public static HashMap playerQRInterface;
    @SneakyThrows
    @Override
    public void onEnable() {
        setInstance(this);
        playerQRInterface = new HashMap<>();
        getCommand("screen").setExecutor(new getMapInterface());
        getCommand("qrcode").setExecutor(new QRCode());
        getCommand("playerqr").setExecutor(new PlayerQRCode());
        Bukkit.getPluginManager().registerEvents(new MapInitialize(), this);

        File configFile = new File(getDataFolder()+File.separator+"config.yml");
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        File mapsData = new File(getDataFolder()+File.separator+"data.yml");
        if (!mapsData.exists()) {
            mapsData.createNewFile();
        }

        getLogger().info("QRCodeMap is enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("QRCodeMap is disabled!");
    }

    public static String getMessage(String messageCode) {
        return getInstance().getConfig().getString(messageCode).replace("&", "§");
    }

}