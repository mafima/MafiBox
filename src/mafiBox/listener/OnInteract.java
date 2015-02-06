package mafiBox.listener;

import java.util.ArrayList;
import java.util.List;
import mafiBox.MafiBox;
import mafiBox.gifts.DamageItemGift;
import mafiBox.gifts.DiamondDamageGift;
import mafiBox.gifts.Gift;
import mafiBox.gifts.HealGift;
import mafiBox.gifts.MoneyGift;
import mafiBox.gifts.RandomGift;
import mafiBox.gifts.SpeedGift;
import mafiBox.gifts.SuperJumpGift;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Cobra_8
 */
public class OnInteract implements Listener {

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

        if (MafiBox.economy.has(e.getPlayer(), MafiBox.price)) {
            int percent = (int) Math.ceil(Math.random() * 100);
            List<Gift> gifts = new ArrayList<>();
            if (percent > 90) {// 10%
                gifts.add(new HealGift(e.getPlayer()));
                gifts.add(new RandomGift(e.getPlayer()));
                gifts.add(new SpeedGift(e.getPlayer()));
            } else if (percent > 85) {// 5%
                gifts.add(new SuperJumpGift(e.getPlayer()));
                gifts.add(new DamageItemGift(e.getPlayer()));
                gifts.add(new DiamondDamageGift(e.getPlayer()));
                gifts.add(new DiamondDamageGift(e.getPlayer()));
                gifts.add(new MoneyGift(e.getPlayer()));
            } else if (percent > 84) {// 1%
                //give VIP
            } else {
                MafiBox.economy.withdrawPlayer(e.getPlayer(), 2500);
                e.getPlayer().sendMessage(MafiBox.prefix + "§6Du hast §c2500 §6Mafis gewonnen !");
                return;
            }
            gifts.get(MafiBox.random.nextInt(gifts.size())).executeGift();
            e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 2);
            e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_SCREAM, 2, 2);
        } else {
            e.getPlayer().sendMessage(MafiBox.prefix + "§4Du hast leider nicht genug Mafis :(");
        }

    }
}
