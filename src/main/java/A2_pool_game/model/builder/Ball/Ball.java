package A2_pool_game.model.builder.Ball;

import A2_pool_game.model.IGame_logic;
import A2_pool_game.model.strategy.IBall_strategy;

public class Ball  {
    private String colour;
    private Double positionX;
    private Double positionY;
    private Double velocityX;
    private Double velocityY;
    private Double mass;
    private Double prevPositionX;
    private Double prevPositionY;

    private final int radius = 10;

    private boolean inHole = false;

    private IBall_strategy fall_strategy;



    // public Ball() {
    //     this.positionX = -1.0d;
    //     this.positionY = -1.0d;
    //     this.prevPositionX = -1.0d;
    //     this.prevPositionY = -1.0d;
    // }
    public Ball() {

    }

    public Ball(Ball ball) {
        // deep copy
        this.colour = ball.getColour();
        this.positionX = ball.getxPos();
        this.positionY = ball.getyPos();
        this.velocityX = ball.getxVel();
        this.velocityY = ball.getyVel();
        this.mass = ball.getMass();
        this.prevPositionX = ball.getPrevPositionX();
        this.prevPositionY = ball.getPrevPositionY();
        this.inHole = ball.isInHole();
        this.fall_strategy = ball.getStrategy();
    }

    public void setStrategy(IBall_strategy strategy) {
        this.fall_strategy = strategy;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setXPos(Double positionX) {
        this.positionX = positionX;
    }

    public void setyPos(Double positionY) {
        this.positionY = positionY;
    }

    public void setxVel(Double velocityX) {
        this.velocityX = velocityX;
    }

    public void setyVel(Double velocityY) {
        this.velocityY = velocityY;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public String getColour() {
        return colour;
    }

    public Double getxPos() {
        return positionX;
    }

    public Double getyPos() {
        return positionY;
    }

    public Double getxVel() {
        return velocityX;
    }

    public Double getyVel() {
        return velocityY;
    }

    public Double getMass() {
        return mass;
    }

    public int getRadius() {
        return radius;
    }

    public boolean matchBall(Ball cueBall) {
        return this == cueBall;
    }

    public Double getPrevPositionX() {
        return prevPositionX;
    }

    public Double getPrevPositionY() {
        return prevPositionY;
    }

    public boolean isInHole() {
        return inHole;
    }

    public void setInHole(boolean inHole) {
        this.inHole = inHole;
    }

    private IBall_strategy getStrategy() {
        return this.fall_strategy;
    }

    public void behave(IGame_logic gameEngineImpl) {
        fall_strategy.after_fall(this, gameEngineImpl);
    }



}
