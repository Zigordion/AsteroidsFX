package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Player extends Entity {
    private ArrayList<IPlayerShootListener> playerShootListeners = new ArrayList<>();

    public void addPlayerShootListeners(IPlayerShootListener playerShootListener) {
        playerShootListeners.add(playerShootListener);
    }
    public void notifyListeners(GameData gameData, World world, Entity player){
        for (IPlayerShootListener playerShootListener : playerShootListeners) {
            playerShootListener.notifyShot(gameData, world, player);
        }
    }
}
