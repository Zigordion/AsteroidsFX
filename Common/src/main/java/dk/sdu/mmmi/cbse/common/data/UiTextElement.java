package dk.sdu.mmmi.cbse.common.data;

import java.util.UUID;

public class UiTextElement {
    public UiTextElement(String text, double x, double y, int redValue, int greenValue, int blueValue) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }
    private final UUID ID = UUID.randomUUID();

    private String text;
    private double x;
    private double y;
    private int redValue;
    private int greenValue;
    private int blueValue;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getID() {
        return ID.toString();
    }
}
