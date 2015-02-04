/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mafiBox;

import java.util.Arrays;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Manuel
 */
public class MafiBox extends JavaPlugin {

    public static final int price = 10000;
    public static final String prefix = "§8[§6Mafi§cBox§8]§r ";
    public static final String boxprefix = "§a§l- §oMafiBox §a§l-";

    public static ItemStack item;

    @Override
    public void onEnable() {

        setupItemStack();
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mafibox")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (p.getInventory().contains(item)) {
                    p.getInventory().remove(item);
                    p.sendMessage(prefix + "§6Du hast deine MafiBox weg gepackt.");
                }

                p.getInventory().addItem(item);
                p.updateInventory();
                p.playEffect(p.getLocation(), Effect.SMOKE, p);
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 5, 3);
                p.sendMessage(prefix + "§6Du findest nun eine MafiBox in deinem Inventar.");
            }
        }
        return true;
    }

    private void setupItemStack() {
        item = new ItemStack(Material.ENDER_CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(boxprefix);
        meta.setLore(Arrays.asList("§8Zahle §7§o" + price + "§7 Mafis §8für ein §a§lGeschenk!"));
        item.setItemMeta(meta);
    }

}
