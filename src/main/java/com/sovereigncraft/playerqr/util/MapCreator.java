package com.sovereigncraft.playerqr.util;

import com.sovereigncraft.playerqr.Main;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapCreator {

    @SneakyThrows
    public static ItemStack generateMap(String string, Player p, String data,Boolean perPlayer) {

        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();
        MapView mapView = Bukkit.createMap(p.getWorld());
        mapView.setScale(MapView.Scale.CLOSEST);
        mapView.setUnlimitedTracking(true);
        mapView.getRenderers().clear();
        TemplateCreator templateCreator = new TemplateCreator();
        templateCreator.createTemplate(string,mapView);


        mapMeta.setMapView(mapView);
        itemStack.setItemMeta(mapMeta);

        if (Main.getInstance().getConfig().getBoolean("settings.changeMapName")) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(data);
            itemStack.setItemMeta(meta);
        }


        return itemStack;
    }
    public static ItemStack clonePlayerMap() {
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();
        MapView mapView = Bukkit.getMap(Main.getInstance().getConfig().getInt("interfaceID"));
        mapMeta.setMapView(mapView);
        itemStack.setItemMeta(mapMeta);

        if (Main.getInstance().getConfig().getBoolean("settings.changeMapName")) {
            ItemMeta meta = itemStack.getItemMeta();
            itemStack.setItemMeta(meta);
        }


        return itemStack;
    }
    public static ItemStack generatePlayerMap(Player p) {

        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();
        MapView mapView = Bukkit.createMap(p.getWorld());
        mapView.setScale(MapView.Scale.CLOSEST);
        mapView.setUnlimitedTracking(true);
        mapView.getRenderers().clear();
        TemplateCreator templateCreator = new TemplateCreator();
        templateCreator.createPlayerTemplate(mapView);

        mapMeta.setMapView(mapView);
        itemStack.setItemMeta(mapMeta);

        if (Main.getInstance().getConfig().getBoolean("settings.changeMapName")) {
            ItemMeta meta = itemStack.getItemMeta();
            itemStack.setItemMeta(meta);
        }


        return itemStack;
    }
}
