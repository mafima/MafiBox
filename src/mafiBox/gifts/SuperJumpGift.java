package mafiBox.gifts;

import mafiBox.MafiBox;
import org.bukkit.entity.Player;

/**
 *
 * @author Cobra_8
 */
public class SuperJumpGift extends Gift {

    private int jumps;

    public SuperJumpGift(Player player) {
        super(player);
        this.jumps = 0;
    }

    public int getJumps() {
        return this.jumps;
    }

    @Override
    public void executeGift() {
        this.jumps = MafiBox.random.nextInt(4) + 3;
    }

}
