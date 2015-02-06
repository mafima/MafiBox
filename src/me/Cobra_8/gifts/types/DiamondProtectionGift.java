package me.Cobra_8.gifts.types;

import java.util.ArrayList;
import java.util.List;
import me.Cobra_8.MafiBox;
import me.Cobra_8.gifts.Gift;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Cobra_8
 */
public class DiamondProtectionGift extends Gift {

    public DiamondProtectionGift(Player player) {
        super(player);
    }

    @Override
    public void executeGift() {
        List<Material> protections = new ArrayList<>();
        protections.add(Material.DIAMOND_HELMET);
        protections.add(Material.DIAMOND_CHESTPLATE);
        protections.add(Material.DIAMOND_LEGGINGS);
        protections.add(Material.DIAMOND_BOOTS);

        ItemStack stack = new ItemStack(protections.get(MafiBox.random.nextInt(protections.size())));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(MafiBox.itemprefix);
        meta.setLore(MafiBox.itemlore);
        stack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        stack.setItemMeta(meta);

        getPlayer().getInventory().addItem(stack);
        getPlayer().updateInventory();

        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast ein Diamantschutz gewonnen !");

    }

}
