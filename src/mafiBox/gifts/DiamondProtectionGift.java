package mafiBox.gifts;

import java.util.ArrayList;
import java.util.List;
import mafiBox.MafiBox;
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

        ItemStack gift = new ItemStack(protections.get(MafiBox.random.nextInt(protections.size())));
        ItemMeta giftMeta = gift.getItemMeta();
        giftMeta.setDisplayName(MafiBox.boxprefix);
        gift.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        gift.setItemMeta(giftMeta);

        getPlayer().getInventory().addItem(gift);

        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast ein Diamantschutz erhalten !");

    }

}
