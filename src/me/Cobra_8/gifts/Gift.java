package me.Cobra_8.gifts;

import org.bukkit.entity.Player;

/**
 *
 * @author Cobra_8
 */
public abstract class Gift {

    private Player player;
    private GiftType type;

    public Gift(Player player) {
        this.player = player;
        this.type = type;
    }

    public Player getPlayer() {
        return this.player;
    }

    public GiftType getType() {
        return this.type;
    }

    public abstract void executeGift();

}
