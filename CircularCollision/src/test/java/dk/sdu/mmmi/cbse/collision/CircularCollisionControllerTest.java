package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EventBroker.class)
class CircularCollisionControllerTest {
    CircularCollisionController circularCollisionController;
    GameData gameData;
    World world;
    
    PowerMockito.mockStatic(EventBroker.class)
    EventBroker eventBroker;
    Entity entity1;
    Entity entity2;
    @BeforeEach
    void setUp() {
        circularCollisionController = new CircularCollisionController();
        gameData = new GameData();
        world = new World();
        eventBroker = mock(EventBroker.class);
        when(EventBroker.getInstance()).thenReturn(eventBroker);
        entity1 = mock(Entity.class);

        when(entity1.getID()).thenReturn(String.valueOf(UUID.randomUUID()));

        entity2 = mock(Entity.class);

        when(entity2.getID()).thenReturn(String.valueOf(UUID.randomUUID()));

        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});

    }
    @AfterEach
    void tearDown(){
        world = null;
        entity1 = null;
        entity2 = null;

    }

    @Test
    void noCollisionWhenFarApart() {
        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(100.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);

        world.addEntity(entity1);
        world.addEntity(entity2);

        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, never()).triggerEvent(
                eq(EventType.COLLISION),
                any(Entity.class),
                any(Entity.class));

    }
    @Test
    void CollisionWhenClose() {
        when(entity2.getX()).thenReturn(0.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);

        world.addEntity(entity1);
        world.addEntity(entity2);

        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker).triggerEvent(
                eq(EventType.COLLISION),
                any(Entity.class),
                any(Entity.class));

    }
}