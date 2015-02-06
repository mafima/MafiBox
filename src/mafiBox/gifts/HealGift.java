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
public class HealGift extends Gift {

    private ItemStack healItem;

    public HealGift(Player player) {
        super(player);
        setupHealItem();
    }

    @Override
    public void executeGift() {
        getPlayer().getInventory().addItem(this.healItem);
        getPlayer().sendMessage(MafiBox.prefix);
    }

    private void setupHealItem() {
        this.healItem = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        ItemMeta healMeta = this.healItem.getItemMeta();
        healMeta.setDisplayName(MafiBox.prefix);
        this.healItem.setItemMeta(healMeta);

    }

}
