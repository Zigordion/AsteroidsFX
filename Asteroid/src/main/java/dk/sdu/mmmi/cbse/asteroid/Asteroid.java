package dk.sdu.mmmi.cbse.asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EventType;
import dk.sdu.mmmi.cbse.common.services.AEventListener;

import java.util.List;

public class Asteroid extends AEventListener {
    private double xDirection;
    private final double size;
    private final IAsteroidCreator asteroidCreator;
    public Asteroid(double size, IAsteroidCreator asteroidCreator){

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
    public void onTrigger(EventType eventType) {

    }

    @Override
    public void onTrigger(EventType eventType, Entity entity, Entity other) {
        if(eventType == EventType.COLLISION && entity == this && other instanceof Bullet){
            if(size/2.0>1.5){
                asteroidCreator.createSmallerAsteroid(this);
            }
            setActive(false);
        }
    }

}
