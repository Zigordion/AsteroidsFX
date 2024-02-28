package dk.sdu.mmmi.cbse.common.data;

public class UiPolygonElement {
    //can be either text or polygons
    //Should this be 2 separate classes?
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private int redValue;
    private int greenValue;
    private int blueValue;
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

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
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
