/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mafi.MafiBox;

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

    private final ArrayList<String> playersToAdd = new ArrayList<>();
    private final RegisteredServiceProvider<Economy> eProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
    private final Economy e = eProvider.getProvider();

    @EventHandler
    public void onInteract(PlayerInteractEvent evt) {

        if (evt.hasItem()) {
            ItemStack item = evt.getItem();
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(Main.boxprefix)) {

                evt.setCancelled(true);

                if (e.has(evt.getPlayer(), Main.price)) {
                	
                	
                	// beispiel:
        			double diamant = 0.4;
        			double gold = 0.5;
        			double stein = (1 - gold + diamant);
        			double E1 = (double) (Math.random() * stein);
        			double E2 = (double) (Math.random() * gold);
        			double E3 = (double) (Math.random() * diamant);

        			if (E1 > E2 && E2 > E3) {
        				System.out.println("Du bekommst ein Steinschwert");
        			} if (E2 > E1 && E2 > E3) {
        				System.out.println("Du bekommst ein Goldschwert");
        			} if (E3 > E1 && E3 > E2) {
        				System.out.println("Du bekommst ein Diamantschwert");
        			}

                } else {
                    evt.getPlayer().sendMessage(Main.prefix + "§4Du hast leider nicht genug Mafis :(");
                }

            }

        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent evt) {
        if (evt.getDrops().contains(Main.item)) {
            evt.getDrops().remove(Main.item);

            playersToAdd.add(evt.getEntity().getName());

        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent evt) {
        if (evt.getItemDrop().getItemStack() == Main.item) {
            evt.setCancelled(true);
            evt.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent evt) {
        if (playersToAdd.contains(evt.getPlayer().getName())) {

            evt.getPlayer().getInventory().addItem(Main.item);
            evt.getPlayer().updateInventory();

            playersToAdd.remove(evt.getPlayer().getName());
        }
    }

}
