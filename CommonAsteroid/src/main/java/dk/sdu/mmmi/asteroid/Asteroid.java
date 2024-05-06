package dk.sdu.mmmi.asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private double xDirection;
    private final double size;
    public Asteroid(double size){
        this.size = size;
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



}
