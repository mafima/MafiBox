package mafiBox.gifts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mafiBox.MafiBox;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Cobra_8
 */
public class RandomGift extends Gift {
    
    public RandomGift(Player player) {
        super(player);
    }
    
    @Override
    public void executeGift() {
        List<Material> mats = new ArrayList<>();
        mats.addAll(Arrays.asList(Material.values()));
        getPlayer().getInventory().addItem(new ItemStack(mats.get(MafiBox.random.nextInt(mats.size()))));
        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast ein ganz normales Minecraft Item erhalten !");
    }
    
}
