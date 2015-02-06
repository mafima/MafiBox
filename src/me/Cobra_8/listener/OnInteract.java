package me.Cobra_8.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.Cobra_8.MafiBox;
import me.Cobra_8.gifts.Gift;
import me.Cobra_8.gifts.types.DamageItemGift;
import me.Cobra_8.gifts.types.DiamondDamageGift;
import me.Cobra_8.gifts.types.DiamondProtectionGift;
import me.Cobra_8.gifts.types.HealGift;
import me.Cobra_8.gifts.types.MoneyGift;
import me.Cobra_8.gifts.types.RandomGift;
import me.Cobra_8.gifts.types.SpeedGift;
import me.Cobra_8.gifts.types.SuperJumpGift;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Cobra_8
 */
public class OnInteract implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        if (!(e.hasItem())) {
            return;
        }

        if (e.getItem() == null || e.getItem().getType() == Material.AIR) {
            return;
        }

        ItemStack item = e.getItem();
        if (item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null || item.getItemMeta().getDisplayName().isEmpty()) {
            return;
        }
        if (!(item.getItemMeta().getDisplayName().equalsIgnoreCase(MafiBox.itemprefix)) && !(item.getItemMeta().getDisplayName().equalsIgnoreCase(MafiBox.boxprefix))) {
            return;
        }
        e.setCancelled(true);
        if (e.getItem().getType() == MafiBox.item.getType()) {

            if (MafiBox.economy.has(e.getPlayer(), MafiBox.price)) {
                MafiBox.economy.withdrawPlayer(e.getPlayer(), MafiBox.price);
                int percent = (int) Math.ceil(Math.random() * 100);
                List<Gift> gifts = new ArrayList<>();
                if (percent > 60) {// 40%
                    gifts.add(new SuperJumpGift(e.getPlayer()));
                    gifts.add(new DiamondProtectionGift(e.getPlayer()));
                    gifts.add(new DiamondDamageGift(e.getPlayer()));
                    gifts.add(new MoneyGift(e.getPlayer()));
                } else if (percent > 30) {// 30%
                    gifts.add(new HealGift(e.getPlayer()));
                    gifts.add(new RandomGift(e.getPlayer()));
                    gifts.add(new SpeedGift(e.getPlayer()));
                    gifts.add(new DamageItemGift(e.getPlayer()));
                } else if (percent > 29) {// 1%
                    Bukkit.broadcastMessage("VIP !");
                    //give VIP
                    return;
                } else {//29%
                    e.getPlayer().sendMessage(MafiBox.prefix + "§6Du hast leider nichts gewonnen !");
                    return;
                }
                gifts.get(MafiBox.random.nextInt(gifts.size())).executeGift();
                e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 4);
                e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_DEATH, 2, 2);
            } else {
                e.getPlayer().sendMessage(MafiBox.prefix + "§4Du hast leider nicht genug Mafis :(");
            }
        } else if (e.getItem().getType() == Material.INK_SACK) {
            e.getPlayer().setHealth(20.0);
            e.getPlayer().getInventory().removeItem(getHealItem());
            e.getPlayer().sendMessage(MafiBox.prefix + "§6Du wurdest geheilt.");
        } else if (e.getItem().getType() == Material.SUGAR) {
            e.getPlayer().setWalkSpeed(0.8F);
            e.getPlayer().getInventory().removeItem(getSpeedItem());
            e.getPlayer().sendMessage(MafiBox.prefix + "§6Du rennst für §c10 §6Sekunden mit Ueberschallgeschwindigkeit");
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OnInteract.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (e.getPlayer() != null && e.getPlayer().isOnline()) {
                        e.getPlayer().setWalkSpeed(0.2F);
                    }
                }
            }, "MB-removeSpeed");
            thread.start();
        } else if (e.getItem().getType() == Material.FEATHER) {
            e.getPlayer().setVelocity(e.getPlayer().getVelocity().multiply(3.0).setY(4.0));
            e.getPlayer().getInventory().removeItem(getJumpItem());
            e.getPlayer().sendMessage(MafiBox.prefix + "§6Wooosh");
        }
    }

    private ItemStack getHealItem() {
        ItemStack stack = new ItemStack(Material.INK_SACK, 1, (byte) 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack getSpeedItem() {
        ItemStack stack = new ItemStack(Material.SUGAR, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.setItemMeta(meta);
        return stack;
    }

    private ItemStack getJumpItem() {
        ItemStack stack = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.setItemMeta(meta);
        return stack;
    }
}
