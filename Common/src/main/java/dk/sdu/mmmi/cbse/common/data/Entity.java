package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private int redValue;
    private int greenValue;
    private int blueValue;
    private double rotation;
    private boolean isActive;

    protected boolean canCollide = true;

    private ArrayList<OnHitListener> onHitListeners = new ArrayList<>();
    public void onHit(Entity other){
        for (OnHitListener onHitListener : onHitListeners) {
            onHitListener.notifyHit(this,other);
        }
    }

    public void addOnHitListener(OnHitListener onHitListener){
        if(onHitListeners.contains(onHitListener)){
            return;
        }
        onHitListeners.add(onHitListener);
    }
    public String getID() {
        return ID.toString();
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setRGB(int redValue, int greenValue, int blueValue){
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    public void setX(double x) {
        this.x =x;
    }

    public boolean getCanCollide() {
        return canCollide;
    }
    public void setCanCollide(boolean value) {
        canCollide = value;
    }
    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRedValue() {
        return redValue;
    }

    public int getGreenValue() {
        return greenValue;
    }

    public int getBlueValue() {
        return blueValue;
    }
}
