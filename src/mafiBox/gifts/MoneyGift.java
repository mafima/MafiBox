package mafiBox.gifts;

import mafiBox.MafiBox;
import org.bukkit.entity.Player;

/**
 *
 * @author Cobra_8
 */
public class MoneyGift extends Gift {

    public MoneyGift(Player player) {
        super(player);
    }

    @Override
    public void executeGift() {
        double money = 0;
        int percent = (int) Math.ceil(Math.random() * 100);
        if (percent > 99) {
            money = 200000D;
        } else if (percent > 79) {
            money = 40000D;
        } else if (percent > 50) {
            money = 20000D;
        } else {
            money = 10000D;
        }
        MafiBox.economy.withdrawPlayer(getPlayer(), money);
        getPlayer().sendMessage(MafiBox.prefix + "§6Du hast §c" + money + " §6Mafis gewonnen !");
    }

}
