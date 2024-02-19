package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionController implements IPostEntityProcessingService {
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

        for (Entity entity : world.getEntities() ) {
            double[] coords = entity.getPolygonCoordinates();
            double entityRadius = 0;
            for (double coord : coords) {
                double tmpRadius = Math.abs(coord);
                if(entityRadius<tmpRadius){
                    entityRadius = tmpRadius;
                }
            }
            for (Entity other : world.getEntities() ) {
                if(entity.getClass() != other.getClass()){
                    //Check collision
                    double[] otherCoords = other.getPolygonCoordinates();
                    double otherRadius = 0;
                    for (double coord : otherCoords) {
                        double tmpRadius = Math.abs(coord);
                        if(otherRadius<tmpRadius){
                            otherRadius = tmpRadius;
                        }
                    }
                    double deltaX = entity.getX()-other.getX();
                    double deltaY = entity.getY()-other.getY();
                    double distance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);
                    if(distance < entityRadius+otherRadius){
                        //collided
                        entity.onHit(other);
                        other.onHit(entity);
                        //Some sort of check to see which entity is stronger
                        //eg. bullets stronger than asteroids, but asteroids
                        //stronger than ships
                    }
                }
            }
        }
    }
}
