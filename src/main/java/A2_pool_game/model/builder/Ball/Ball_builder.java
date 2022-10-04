package A2_pool_game.model.builder.Ball;

import A2_pool_game.model.strategy.*;

public class Ball_builder implements IBall_builder {
    private String colour;
    private Double positionX;
    private Double positionY;
    private Double velocityX;
    private Double velocityY;
    private Double mass;

    private Ball ball;
    private IBall_strategy strategy;


    public Ball_builder(String colour, Double positionX, Double positionY, Double velocityX, Double velocityY, Double mass) {
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
        if (this.colour.equals("white")) {
            this.strategy = new WhiteBall_strategy();
        } else  {
            this.strategy = new NormalBall_strategy();
        }
    }

    // setting methods

    public Ball_builder setStrategy() {
        this.ball.setStrategy(strategy);
        return this;
    }

    public Ball_builder setColour() {
        this.ball.setColour(colour);
        return this;
    }

    public Ball_builder setXPos() {
        this.ball.setXPos(positionX);
        return this;
    }

    public Ball_builder setyPos() {
        this.ball.setyPos(positionY);
        return this;
    }

    public Ball_builder setxVel() {

        this.ball.setxVel(velocityX);
        return this;
    }

    public Ball_builder setyVel() {
        this.ball.setyVel(velocityY);
        return this;
    }

    public Ball_builder setMass() {
        this.ball.setMass(mass);
        return this;
    }

    public Ball getBall() {
        return this.ball;
    }
    
}
