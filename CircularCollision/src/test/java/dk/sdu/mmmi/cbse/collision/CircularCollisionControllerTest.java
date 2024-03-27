package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CircularCollisionControllerTest {
    CircularCollisionController circularCollisionController;
    GameData gameData;
    World world;
    EventBroker eventBroker;
    @BeforeEach
    void setUp() {
        circularCollisionController = new CircularCollisionController();
        gameData = new GameData();
        world = new World();
        eventBroker = mock(EventBroker.class);
    }

    @Test
    void noCollisionWhenFarApart() {
        Entity entity1 = mock(Entity.class);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});

        Entity entity2 = mock(Entity.class);
        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(100.0);
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});

        world.addEntity(entity1);
        world.addEntity(entity2);

        circularCollisionController.postProcess(gameData, world);

        verify(eventBroker, never()).triggerEvent(
                eq(EventType.COLLISION),
                any(Entity.class),
                any(Entity.class));

    }
}