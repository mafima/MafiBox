package mafiBox.gifts;

import mafiBox.MafiBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Cobra_8
 */
public class DamageItemGift extends Gift {

    private ItemStack damageItem;

    public DamageItemGift(Player player) {
        super(player);
        setupDamageItem();
    }

    @Override
    public void executeGift() {
        getPlayer().getInventory().addItem(this.damageItem);
        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast eine Schaufel bekommen, die 6 Herzen Schaden macht.");
        getPlayer().sendMessage(MafiBox.prefix + "§6Aber achtung, sie geht schnell kapuut !");
    }

    private void setupDamageItem() {
        this.damageItem = new ItemStack(Material.STICK);
        ItemMeta damageMeta = this.damageItem.getItemMeta();
        damageMeta.setDisplayName(MafiBox.boxprefix);
        this.damageItem.setItemMeta(damageMeta);

    }

}
