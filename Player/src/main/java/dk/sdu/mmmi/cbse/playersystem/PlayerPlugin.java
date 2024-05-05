package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;

public class PlayerPlugin implements IGamePluginService, IEventListener {

    private Entity player;
    private final EventBroker eventBroker =  EventBroker.getInstance();
    public PlayerPlugin(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
    }
    @Override
    public void onTrigger(EventType eventType, Entity... entities) {
        if(player == entities[0]){
            eventBroker.triggerEvent(EventType.PLAYER_HIT,player);
        }
    }


    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();
        playerShip.setRGB(255,255,255);
        playerShip.setActive(true);
        playerShip.setPolygonCoordinates(-5,-5, 10,0, -5,5);
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
