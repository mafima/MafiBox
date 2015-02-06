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
public class DiamondDamageGift extends Gift {

    public DiamondDamageGift(Player player) {
        super(player);
    }

    @Override
    public void executeGift() {
        List<Material> protections = new ArrayList<>();
        protections.add(Material.DIAMOND_SWORD);
        protections.add(Material.DIAMOND_HOE);
        protections.add(Material.DIAMOND_SPADE);
        protections.add(Material.DIAMOND_SWORD);

        ItemStack gift = new ItemStack(protections.get(MafiBox.random.nextInt(protections.size())));
        ItemMeta giftMeta = gift.getItemMeta();
        giftMeta.setDisplayName(MafiBox.boxprefix);
        gift.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        gift.setItemMeta(giftMeta);

        getPlayer().getInventory().addItem(gift);

        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast eine Diamantwaffe erhalten !");

    }

}
