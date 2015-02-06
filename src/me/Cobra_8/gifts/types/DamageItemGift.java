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
public class DamageItemGift extends Gift {
    
    public DamageItemGift(Player player) {
        super(player);
    }
    
    @Override
    public void executeGift() {
        
        ItemStack stack = new ItemStack(Material.STONE_SPADE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.setItemMeta(meta);
        getPlayer().getInventory().addItem(stack);
        getPlayer().updateInventory();
        
        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast eine Schaufel gewonnen, welche 6 Herzen Schaden macht.");
        getPlayer().sendMessage(MafiBox.prefix + "§6Aber achtung, sie geht schnell kapuut !");
    }
}
