package me.Cobra_8.listener;

import me.Cobra_8.MafiBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 *
 * @author Cobra_8
 */
public class OnEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        Player damager = (Player) e.getDamager();
        if (damager.getItemInHand().getType() == Material.DIAMOND_SPADE) {
            damager.getItemInHand().setDurability((short) (damager.getItemInHand().getType().getMaxDurability() / 3));
        }

        if (damager.getItemInHand().getItemMeta() == null || damager.getItemInHand().getItemMeta().getDisplayName() == null || damager.getItemInHand().getItemMeta().getDisplayName().isEmpty()) {
            return;
        }
        if (!(damager.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(MafiBox.itemprefix))) {
            return;
        }

        if (damager.getItemInHand().getType() == Material.STONE_SPADE) {
            e.setDamage(14D);
            short currDurability = damager.getItemInHand().getDurability();
            short maxDurability = damager.getItemInHand().getType().getMaxDurability();
            damager.getItemInHand().setDurability((short) (currDurability + (maxDurability / 3)));
            if (damager.getItemInHand().getDurability() > damager.getItemInHand().getType().getMaxDurability()) {
                damager.getItemInHand().setType(Material.AIR);
                damager.updateInventory();
            }
        }
    }
}
