package A2_pool_game.model.builder.Table;



public class Pocket  {
    
    private Double positionX;
    private Double positionY;
    private int radius;

    public void setXPos(Double positionX) {
        this.positionX = positionX;
    }

    public void setyPos(Double positionY) {
        this.positionY = positionY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Double getxPos() {
        return positionX;
    }

    public Double getyPos() {
        return positionY;
    }

    public int getRadius() {
        return radius;
    }
    
}
