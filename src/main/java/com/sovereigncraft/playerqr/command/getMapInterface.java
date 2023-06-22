package com.sovereigncraft.playerqr.command;

import com.sovereigncraft.playerqr.Main;
import com.sovereigncraft.playerqr.util.MapCreator;
import com.sovereigncraft.playerqr.util.QRCreator;
import lombok.SneakyThrows;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

import java.io.File;

public class getMapInterface implements CommandExecutor {

    @Override
    @SneakyThrows
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = Main.getMessage("messages.prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix+Main.getMessage("messages.onlyForPlayers"));
            return true;
        }

        if (args.length > 0) {
            return false;
        }

        Player player = (Player) sender;
            if (player.getItemInHand().getType() != Material.AIR) {
                sender.sendMessage(prefix + Main.getMessage("messages.handNotEmpty"));
                return true;
            }
        if (Main.getInstance().getConfig().get("interfaceID") == null) {
            ItemStack map = MapCreator.generatePlayerMap(player);
            Integer id = ((MapMeta) map.getItemMeta()).getMapId();
            player.setItemInHand(map);
            File configFile = new File(Main.getInstance().getDataFolder()+File.separator+"config.yml");
            FileConfiguration config = Main.getInstance().getConfig();
            config.set("interfaceID",id);
            config.save(configFile);

        } else {
            ItemStack map = MapCreator.clonePlayerMap();
            player.setItemInHand(map);
        }
        sender.sendMessage(prefix + Main.getMessage("messages.success"));
        return true;
    }

}
