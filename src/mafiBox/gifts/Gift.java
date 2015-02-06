package mafiBox.gifts;

import org.bukkit.entity.Player;

/**
 *
 * @author Cobra_8
 */
public abstract class Gift {

    private Player player;

    public Gift(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract void executeGift();

}
