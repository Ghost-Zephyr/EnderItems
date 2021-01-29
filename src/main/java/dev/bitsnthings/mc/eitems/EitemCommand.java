package dev.bitsnthings.mc.eitems;

import dev.bitsnthings.mc.eitems.bow.EnderBow;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class EitemCommand implements CommandExecutor {
  Logger log = EnderItems.getInstance().getLogger();
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player receiver;
    if (args.length > 0 && args.length < 3) {
      String itemName = args[0].toLowerCase();
      if (EnderBow.ebowSynonyms.contains(itemName)) {
        switch (args.length) {
          case 1:
            if (!(sender instanceof Player)) {
              sender.sendMessage(ChatColor.RED + "Can't give an ender bow to the console!");
              return false;
            }
            if (!sender.hasPermission(EnderBow.GIVE_SELF_PERM)) return false;
            receiver = (Player) sender;
          break;
          case 2:
            if (!sender.hasPermission(EnderBow.GIVE_OTHERS_PERM)) return false;
            receiver = (Player) Bukkit.getPlayer(args[1]);
          break;
          default: return false;
        }
        receiver.getInventory().addItem(EnderBow.createEbow());
        sender.sendMessage(String.format("Gave %s an ender bow.", receiver.getDisplayName()));
        if (sender.getName() != "CONSOLE") {
          String receiverName = receiver.getDisplayName();
          if (sender.getName() == receiver.getDisplayName()) receiverName = "self";
          log.info(String.format("%s gave %s an ender bow.", sender.getName(), receiverName));
        }
        return true;
      } else {
        sender.sendMessage(String.format("%sGotta have a valid item name!", ChatColor.RED));
        return true;
      }
    } else return false;
  }
}
