package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setRGB(255,255,255);
        playerShip.setActive(true);
        playerShip.setPolygonCoordinates(-5,-5, 10,0, -5,5);
        //playerShip.setPolygonCoordinates(-1,1 ,-1.3,2 ,-1.3,3 ,-0.5,3.5 ,1.7,3.5, 1,3, 0.7,2.3, 1.2,1.7  , 0.6,1.4, 0.6,0.6, -0.5,0.6);

        playerShip.setX(gameData.getDisplayHeight()/2.0);
        playerShip.setY(gameData.getDisplayWidth()/2.0);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
