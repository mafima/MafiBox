package me.Cobra_8.gifts.types;

import me.Cobra_8.MafiBox;
import me.Cobra_8.gifts.Gift;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Cobra_8
 */
public class SuperJumpGift extends Gift {

    public SuperJumpGift(Player player) {
        super(player);
    }

    @Override
    public void executeGift() {
        ItemStack stack = new ItemStack(Material.FEATHER);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.setItemMeta(meta);
        getPlayer().getInventory().addItem(stack);
        getPlayer().updateInventory();

        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast drei Speeditems gewonnen.");
    }
}
