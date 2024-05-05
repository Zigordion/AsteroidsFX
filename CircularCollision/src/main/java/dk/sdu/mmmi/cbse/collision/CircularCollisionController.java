package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.Interactable;

public class CircularCollisionController implements IPostEntityProcessingService {

    private final EventBroker eventBroker = EventBroker.getInstance();
    @Override
    public void postProcess(GameData gameData, World world) {
        outerLoop:
        for (Entity entity : world.getEntities() ) {
            double[] coords = entity.getPolygonCoordinates();
            double entityRadius = 0;
            for (double coord : coords) {
                double tmpRadius = Math.abs(coord);
                entityRadius = Math.max(tmpRadius,entityRadius);
            }
            for (Entity other : world.getEntities() ) {
                if(other instanceof Interactable){
                    continue;
                }
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
                        eventBroker.triggerEvent(EventType.COLLISION, entity, other);
                        continue outerLoop;
                    }
                }
            }
        }
    }
}
