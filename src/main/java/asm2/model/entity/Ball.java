package asm2.model.entity;

import java.util.List;

import asm2.model.GameEngine;
import asm2.model.GameEngineImpl;
import asm2.model.strategy.BallStrategy;

public class Ball implements Entity{

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

    private BallStrategy strategy;

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
        this.positionX = ball.getPositionX();
        this.positionY = ball.getPositionY();
        this.velocityX = ball.getVelocityX();
        this.velocityY = ball.getVelocityY();
        this.mass = ball.getMass();
        this.prevPositionX = ball.getPrevPositionX();
        this.prevPositionY = ball.getPrevPositionY();
        this.inHole = ball.isInHole();
        this.strategy = ball.getStrategy();
    }

    public void setStrategy(BallStrategy strategy) {
        this.strategy = strategy;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public void setVelocityX(Double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(Double velocityY) {
        this.velocityY = velocityY;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public String getColour() {
        return colour;
    }

    public Double getPositionX() {
        return positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public Double getVelocityX() {
        return velocityX;
    }

    public Double getVelocityY() {
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

    public void setPrevPositionX(Double positionX2) {
        this.prevPositionX = positionX2;
    }

    public void setPrevPositionY(Double positionY2) {
        this.prevPositionY = positionY2;
    }

    public boolean isInHole() {
        return inHole;
    }

    public void setInHole(boolean inHole) {
        this.inHole = inHole;
    }

    private BallStrategy getStrategy() {
        return this.strategy;
    }

    public void behave(GameEngine gameEngineImpl) {
        strategy.behave(this, gameEngineImpl);
    }

    @Override
    public String toString() {
        return "Ball [colour=" + colour + ", mass=" + mass + ", positionX=" + positionX + ", positionY=" + positionY
                + ", prevPositionX=" + prevPositionX + ", prevPositionY=" + prevPositionY + ", radius=" + radius
                + ", velocityX=" + velocityX + ", velocityY=" + velocityY + "]";
    }
    
}
