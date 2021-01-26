package dev.bitsnthings.mc.eitems.bow;

import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

public class EbowEvents implements Listener {
	@EventHandler(priority=EventPriority.HIGH)
	public void onEntityShootBow(EntityShootBowEvent event){
    if (event.isCancelled()) return;
    if (event.getEntity() instanceof Player) {
      Player player = (Player) event.getEntity();
      if (EnderBow.isEbow(event.getBow())) {
        event.setConsumeItem(false);
        Entity arrow = event.getProjectile();
        Entity pearl = player.launchProjectile(EnderPearl.class, arrow.getVelocity());
        event.setProjectile(pearl);
        player.updateInventory();
      }
    }
  }
}

