package com.sovereigncraft.playerqr.command;

import com.sovereigncraft.playerqr.Main;
import com.sovereigncraft.playerqr.util.QRCreator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerQRCode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = Main.getMessage("messages.prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix+Main.getMessage("messages.onlyForPlayers"));
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        Player player = (Player) sender;

        String data = "";
        Integer num = 1;
        Integer max = args.length;
        for (String arg : args) {
            data += arg;
            if (num < max) {
                data += " ";
            }
            num++;
        }
        if (Main.playerQRInterface.get(player.getUniqueId()) == null) {
            Main.playerQRInterface.put(player.getUniqueId(), data);
        } else Main.playerQRInterface.replace(player.getUniqueId(), data);
        Main.playerQRInterface.get(player.getUniqueId());
        //QRCreator QRCreator = new QRCreator(data);
        //QRCreator.playerGenerate(player);

        sender.sendMessage(prefix+Main.getMessage("messages.success"));

        return true;
    }

}
