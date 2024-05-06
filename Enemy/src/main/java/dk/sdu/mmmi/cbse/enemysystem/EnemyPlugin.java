package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Event;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEventListener;
import dk.sdu.mmmi.cbse.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPlugin implements IEventListener {
    private final EventBroker eventBroker =  EventBroker.getInstance();
    private final Random random = new Random();
    private final List<Entity> enemies = new ArrayList<>();
    private static double timer;
    private final double maxTimer = 400;
    public EnemyPlugin(){
        EventBroker.getInstance().addListener(this,EventType.COLLISION);
        EventBroker.getInstance().addListener(this,EventType.PLAYER_HIT);
    }
    public void process(double deltaTime, GameData gameData, World world){
        timer -= deltaTime;
        if (timer <= 0) {
            Entity enemy = createEnemy(gameData);
            world.addEntity(enemy);
            timer = maxTimer;
        }
    }
    public Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();
        enemy.setRGB(128,0,0);
        enemy.setPolygonCoordinates(-5, -5, 10, 5, -5, 5, 10, -5);
        enemy.setX(random.nextDouble(5, gameData.getDisplayWidth() - 5));
        enemy.setY(random.nextDouble(5, gameData.getDisplayHeight() - 5));
        enemy.setActive(true);
        enemies.add(enemy);
        return enemy;
    }

    @Override
    public void onTrigger(Event event) {
        for (Entity enemy : enemies) {
            if(event.getEntities()[0] == enemy){
                if(event.getEntities()[1] instanceof Enemy){
                    return;
                }
                Event newEvent = new Event(EventType.GENERATE_PICKUP, event.getWorld(), event.getGameData(), enemy);
                eventBroker.triggerEvent(newEvent);
                enemies.remove(enemy);
                enemy.setActive(false);
                break;
            }
        }
    }
}
