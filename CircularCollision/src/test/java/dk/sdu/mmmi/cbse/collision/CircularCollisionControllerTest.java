package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import static org.mockito.Mockito.*;

class CircularCollisionControllerTest {
    CircularCollisionController circularCollisionController;
    GameData gameData;
    World world;
    EventBroker eventBroker;
    Entity entity1;
    Entity entity2;
    @BeforeEach
    void setUp() {
        circularCollisionController = new CircularCollisionController();
        gameData = mock(GameData.class);
        world = mock(World.class);

        eventBroker = mock(EventBroker.class);
        when(gameData.getEventBroker()).thenReturn(eventBroker);
        entity1 = mock(EntityDummy.class);

        when(entity1.getID()).thenReturn(UUID.randomUUID().toString());
        when(entity1.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});
        entity2 = mock(Entity.class);

        when(entity2.getID()).thenReturn(UUID.randomUUID().toString());
        when(entity2.getPolygonCoordinates()).thenReturn(new double[]{-4, -4, -4, 4, 4, 4, 4, -4});

        Collection<Entity> mockEntities = Arrays.asList(entity1, entity2);
        when(world.getEntities()).thenReturn(mockEntities);
    }

    @Test
    void testNoCollisionWhenEntitiesFarApart() {
        when(entity2.getX()).thenReturn(100.0);
        when(entity2.getY()).thenReturn(100.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, never()).triggerEvent(
                eq(EventType.COLLISION),
                eq(entity1),
                eq(entity2));
    }
    @Test
    void testCollisionWhenEntitiesAtSameCoordinate() {
        when(entity2.getX()).thenReturn(0.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, atLeastOnce()).triggerEvent(
                eq(EventType.COLLISION),
                eq(entity1),
                eq(entity2));

    }
    @Test
    void testCollisionWhenEntitiesJustCloseEnough() {
        when(entity2.getX()).thenReturn(7.9);
        when(entity2.getY()).thenReturn(0.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, atLeastOnce()).triggerEvent(
                eq(EventType.COLLISION),
                eq(entity1),
                eq(entity2));

    }
    @Test
    void testNoCollisionWhenEntitiesExactlyFarEnough() {
        when(entity2.getX()).thenReturn(8.0);
        when(entity2.getY()).thenReturn(0.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, never()).triggerEvent(
                eq(EventType.COLLISION),
                eq(entity1),
                eq(entity2));

    }
    @Test
    void testCollisionWhenEntitiesClose() {
        when(entity2.getX()).thenReturn(2.0);
        when(entity2.getY()).thenReturn(2.0);
        when(entity1.getX()).thenReturn(0.0);
        when(entity1.getY()).thenReturn(0.0);
        circularCollisionController.postProcess(gameData, world);
        verify(eventBroker, atLeastOnce()).triggerEvent(
                eq(EventType.COLLISION),
                eq(entity1),
                eq(entity2));

    }
}