package asm2.model.builder;

import asm2.model.entity.Ball;
import asm2.model.entity.CueBall;
import asm2.model.entity.Entity;
import asm2.model.strategy.BallStrategy;
import asm2.model.strategy.BlueBallStrategy;
import asm2.model.strategy.RedBallStrategy;
import asm2.model.strategy.WhiteBallStrategy;

public class BallBuilderImpl implements BallBuilder {
    private String colour;
    private Double positionX;
    private Double positionY;
    private Double velocityX;
    private Double velocityY;
    private Double mass;

    private Ball ball;
    private BallStrategy strategy;


    public BallBuilderImpl(String colour, Double positionX, Double positionY, Double velocityX, Double velocityY, Double mass) {
        this.colour = colour;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;

        this.ball = new Ball();

        // determine strategy
        chooseStrategy();
    }

    private void chooseStrategy() {
        if (this.colour.equals("blue")) {
            this.strategy = new BlueBallStrategy();
        } else if (this.colour.equals("red")) {
            this.strategy = new RedBallStrategy();
        }
    }

    // setting methods

    public BallBuilderImpl setStrategy() {
        this.ball.setStrategy(strategy);
        return this;
    }

    public BallBuilderImpl setColour() {
        this.ball.setColour(colour);
        return this;
    }

    public BallBuilderImpl setPositionX() {
        this.ball.setPositionX(positionX);
        return this;
    }

    public BallBuilderImpl setPositionY() {
        this.ball.setPositionY(positionY);
        return this;
    }

    public BallBuilderImpl setVelocityX() {
        this.ball.setVelocityX(velocityX);
        return this;
    }

    public BallBuilderImpl setVelocityY() {
        this.ball.setVelocityY(velocityY);
        return this;
    }

    public BallBuilderImpl setMass() {
        this.ball.setMass(mass);
        return this;
    }

    public Entity getProduct() {
        return this.ball;
    }
    
}
