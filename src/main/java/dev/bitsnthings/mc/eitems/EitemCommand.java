package dev.bitsnthings.mc.eitems;

import dev.bitsnthings.mc.eitems.bow.EnderBow;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

public class EitemCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Player receiver;
    if (args.length > 0 && args.length < 3) {
      String itemName = args[0].toLowerCase();
      if (EnderBow.ebowCmdSynonyms.contains(itemName)) {
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
          String recv = receiver.getDisplayName();
          if (sender.getName() == receiver.getDisplayName()) recv = "himself";
          EnderItems.getInstance().getLogger().info(String.format("%s gave %s an ender bow.", sender.getName(), recv));
        }
        return true;
      } else return false;
    } else return false;
  }
}
