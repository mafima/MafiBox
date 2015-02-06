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
public class SpeedGift extends Gift {

    private ItemStack speedItem;

    public SpeedGift(Player player) {
        super(player);
        setupSpeedItem();
    }

    @Override
    public void executeGift() {
        getPlayer().getInventory().addItem(this.speedItem);
        getPlayer().sendMessage(MafiBox.prefix);
    }

    private void setupSpeedItem() {
        this.speedItem = new ItemStack(Material.SUGAR);
        ItemMeta speedMeta = this.speedItem.getItemMeta();
        speedMeta.setDisplayName(MafiBox.boxprefix);
        this.speedItem.setItemMeta(speedMeta);
    }
}
