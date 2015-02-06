package mafiBox.listener;

import java.util.ArrayList;
import java.util.List;
import mafiBox.MafiBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author Cobra_8
 */
public class HandleBox implements Listener {

    private List<String> playersToAdd;

    public HandleBox() {
        this.playersToAdd = new ArrayList<>();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent evt) {
        if (evt.getDrops().contains(MafiBox.item)) {
            evt.getDrops().remove(MafiBox.item);
            playersToAdd.add(evt.getEntity().getName());
        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent evt) {
        if (evt.getItemDrop().getItemStack().isSimilar(MafiBox.item)) {
            evt.setCancelled(true);
            evt.getPlayer().updateInventory();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent evt) {
        if (playersToAdd.contains(evt.getPlayer().getName())) {

            evt.getPlayer().getInventory().addItem(MafiBox.item);
            evt.getPlayer().updateInventory();

            playersToAdd.remove(evt.getPlayer().getName());
        }
    }

}
