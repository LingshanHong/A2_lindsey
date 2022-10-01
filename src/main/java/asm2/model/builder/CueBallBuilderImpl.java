package asm2.model.builder;

import asm2.model.entity.Ball;
import asm2.model.entity.CueBall;
import asm2.model.entity.Entity;
import asm2.model.strategy.BallStrategy;
import asm2.model.strategy.WhiteBallStrategy;

public class CueBallBuilderImpl implements BallBuilder {
    private String colour;
    private Double positionX;
    private Double positionY;
    private Double velocityX;
    private Double velocityY;
    private Double mass;

    private Ball ball;
    private BallStrategy strategy;


    public CueBallBuilderImpl(String colour, Double positionX, Double positionY, Double velocityX, Double velocityY, Double mass) {
        this.colour = colour;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.strategy = new WhiteBallStrategy();

        this.ball = new CueBall();

    }

    // setting methods

    public CueBallBuilderImpl setStrategy() {
        this.ball.setStrategy(strategy);
        return this;
    }

    public CueBallBuilderImpl setColour() {
        this.ball.setColour(colour);
        return this;
    }

    public CueBallBuilderImpl setPositionX() {
        this.ball.setPositionX(positionX);
        return this;
    }

    public CueBallBuilderImpl setPositionY() {
        this.ball.setPositionY(positionY);
        return this;
    }

    public CueBallBuilderImpl setVelocityX() {
        this.ball.setVelocityX(velocityX);
        return this;
    }

    public CueBallBuilderImpl setVelocityY() {
        this.ball.setVelocityY(velocityY);
        return this;
    }

    public CueBallBuilderImpl setMass() {
        this.ball.setMass(mass);
        return this;
    }

    public Entity getProduct() {
        return this.ball;
    }
    
}
