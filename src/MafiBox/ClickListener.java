/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mafiBox;

import java.util.ArrayList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 *
 * @author Manuel
 */
public class ClickListener implements Listener {

    private final ArrayList<String> playersToAdd;
    private final RegisteredServiceProvider<Economy> provider;
    private final Economy economy;

    public ClickListener() {
        playersToAdd = new ArrayList<>();
        provider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        economy = provider.getProvider();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!(e.hasItem())) {
            return;
        }
        ItemStack item = e.getItem();
        if (!(item.getItemMeta().getDisplayName().equalsIgnoreCase(MafiBox.boxprefix))) {
            return;
        }
        e.setCancelled(true);

        if (economy.has(e.getPlayer(), MafiBox.price)) {
            int x = (int) Math.ceil(Math.random() * 100);
            if (x > 99) {//99 % chancen

            }

        } else {
            e.getPlayer().sendMessage(MafiBox.prefix + "§4Du hast leider nicht genug Mafis :(");
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent evt) {
        if (evt.getDrops().contains(MafiBox.item)) {
            evt.getDrops().remove(MafiBox.item);
            playersToAdd.add(evt.getEntity().getName());
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent evt) {
        if (evt.getItemDrop().getItemStack().isSimilar(MafiBox.item)) {
            evt.setCancelled(true);
            evt.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent evt) {
        if (playersToAdd.contains(evt.getPlayer().getName())) {

            evt.getPlayer().getInventory().addItem(MafiBox.item);
            evt.getPlayer().updateInventory();

            playersToAdd.remove(evt.getPlayer().getName());
        }
    }

}
