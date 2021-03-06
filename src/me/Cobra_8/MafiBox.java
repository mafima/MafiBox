package me.Cobra_8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import me.Cobra_8.listener.HandleBox;
import me.Cobra_8.listener.OnEntityDamageByEntity;
import me.Cobra_8.listener.OnInteract;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Manuel, Cobra_8
 */
public class MafiBox extends JavaPlugin {

    public static final int price = 10000;
    public static final String prefix = "§8[§6Mafi§cBox§8]§r ";
    public static final String boxprefix = "§a§l- §oMafiBox §a§l-";

    public static final String itemprefix = "§7§l- §6§oReward §7§l-";
    public static final List<String> itemlore = Arrays.asList("§7Dieses §aItem wurde", "§7aus einer §a§lMafiBox", "§7gewonnen !");

    public static final Random random = new Random();

    public static RegisteredServiceProvider<Economy> provider;
    public static Economy economy;

    public static ItemStack item;

    @Override
    public void onEnable() {
        setupItemStack();

        provider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        economy = provider.getProvider();

        Bukkit.getServer().getPluginManager().registerEvents(new HandleBox(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnEntityDamageByEntity(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OnInteract(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mafibox")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (p.getInventory().contains(item)) {
                    p.getInventory().remove(item.getType());
                    p.sendMessage(prefix + "§6Du hast deine MafiBox weg gepackt.");
                    return true;
                }

                p.getInventory().addItem(item);
                p.updateInventory();
                p.playEffect(p.getLocation(), Effect.SMOKE, 2);
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
