package dk.sdu.mmmi.cbse.asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventBroker;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.IEventListener;

public class Asteroid extends Entity implements IEventListener {
    private double xDirection;
    private final double size;
    private final IAsteroidCreator asteroidCreator;
    private final EventBroker eventBroker =  EventBroker.getInstance();
    public Asteroid(double size, IAsteroidCreator asteroidCreator){
        eventBroker.addListener(this,EventType.COLLISION);
        this.size = size;
        this.asteroidCreator = asteroidCreator;
    }

    public double getYDirection() {
        return yDirection;
    }

    public void setYDirection(double yDirection) {
        this.yDirection = yDirection;
    }

    private double yDirection;

    public double getXDirection() {
        return xDirection;
    }

    public void setXDirection(double xDirection) {
        this.xDirection = xDirection;
    }

    public double getSize() {
        return size;
    }


    @Override
    public void onTrigger(EventType eventType, Entity ... entities) {
        if(entities[0] == this && entities[1] instanceof Bullet){
            if(size/2.0>1.5){
                asteroidCreator.createSmallerAsteroid(this);
            }
            eventBroker.removeListener(this);
            eventBroker.triggerEvent(EventType.ASTEROID_DESTROYED,this);
            setActive(false);
        }
    }


}
