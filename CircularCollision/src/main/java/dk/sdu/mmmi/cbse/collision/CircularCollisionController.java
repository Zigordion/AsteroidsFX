package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CircularCollisionController implements IPostEntityProcessingService {

    private final EventBroker eventBroker = EventBroker.getInstance();
    @Override
    public void postProcess(GameData gameData, World world) {
        //Get all entities.
        //Loop through entities of different classes.
        //Trigger a death function on the entity if it collides with a
        //different class.
        //Bullets may kill the shooter in that instance as they are spawned
        //inside the shooter, 2 solutions:
            //1. spawn bullet away from shooter (enables friendly fire)
            //2. detect who shot the bullet and disregard them when
            //   checking for collisions.


        /*
        create a common collisions detector, which will contain an interface which subclasses of entity can implement
        it will contain the onHit method.
        Collision controller could then check if entity is instance of collisionDetector, this would prevent violation
        of the Liskov's Substitution principle. It would however increase the amount of dependencies
         */

        for (Entity entity : world.getEntities() ) {
            double[] coords = entity.getPolygonCoordinates();
            double entityRadius = 0;
            for (double coord : coords) {
                double tmpRadius = Math.abs(coord);
                entityRadius = Math.max(tmpRadius,entityRadius);
            }
            for (Entity other : world.getEntities() ) {
                if(entity.getClass() != other.getClass()){
                    //Check collision
                    double[] otherCoords = other.getPolygonCoordinates();
                    double otherRadius = 0;
                    for (double coord : otherCoords) {
                        double tmpRadius = Math.abs(coord);
                        otherRadius = Math.max(tmpRadius,otherRadius);
                    }
                    double deltaX = entity.getX()-other.getX();
                    double deltaY = entity.getY()-other.getY();
                    double distance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
                    if(distance < entityRadius+otherRadius){
                        //collided
                        eventBroker.triggerEvent(EventType.COLLISION, entity,other);
                    }
                }
            }
        }
    }
}
