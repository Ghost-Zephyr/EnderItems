package dev.bitsnthings.mc.eitems;

import dev.bitsnthings.mc.eitems.bow.EbowEvents;
import dev.bitsnthings.mc.eitems.bow.EnderBow;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.permissions.Permission;
import org.bukkit.Bukkit;

import java.util.logging.Logger;
import java.util.ArrayList;

public class EnderItems extends JavaPlugin {
  private final Logger logger = Logger.getLogger("EnderItems");
  private static ArrayList<ArrayList<Permission>> allperms = new ArrayList<>();
  private static EnderItems plugin;

  @Override
  public void onEnable() {
    plugin = this;

    Bukkit.getPluginManager().registerEvents(new EbowEvents(), this);
    this.getCommand("eitem").setExecutor(new EitemCommand());
    //this.getCommand("eitems").setExecutor(new EitemsCommand());

    if (!EnderBow.registerEbowRecipes()) logger.warning("Failed to register Ender Bow recipes!");
    else logger.info("Registered Ender Bow recipes.");
    EnderBow.registerPermissions();

    allperms.add(EnderBow.perms);
    for (ArrayList<Permission> perms: allperms) {
      for (Permission perm: perms) {
        Bukkit.getPluginManager().addPermission(perm);
        logger.fine("Registered Permission: " + perm.getName());
      }
    }
    logger.info("Registered all permissions.");
  }

  @Override
  public void onDisable() {
    for (ArrayList<Permission> perms: allperms) {
      for (Permission perm: perms) {
        Bukkit.getPluginManager().removePermission(perm);
        logger.fine("Unregistered Permission: " + perm.getName());
      }
    }
    logger.info("Unregistered all permissions.");

    if (!EnderBow.unregisterEbowRecipes()) logger.warning("Failed to unregister Ender Bow recipes!");
    else logger.info("Unregistered Ender Bow recipes.");
    EnderBow.perms.clear();
    allperms.clear();
  }

  public Logger getLogger() {
		return logger;
	}
	public static EnderItems getInstance() {
		return plugin;
	}
}

