package asm2.model.entity;

public class Pocket implements Entity {
    
    private Double positionX;
    private Double positionY;
    private int radius;

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Double getPositionX() {
        return positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public int getRadius() {
        return radius;
    }
    
}
