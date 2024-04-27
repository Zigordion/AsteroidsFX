package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EnemyTest {
    Enemy enemy;
    Enemy enemy2;
    World world;
    GameData gameData;
    @BeforeEach
    void setUp() {
        enemy = new Enemy();
        enemy2 = new Enemy();
        world = new World();
        gameData = new GameData();
    }

    @AfterEach
    void tearDown() {
        enemy = null;
        enemy2 = null;
        world = null;
        gameData = null;
    }

    @Test
    void whenEnemyCollidesWithEnemyThenNoCollision() {
        world.addEntity(enemy);
        world.addEntity(enemy2);
        enemy.onTrigger(EventType.COLLISION, enemy2);

    }
}