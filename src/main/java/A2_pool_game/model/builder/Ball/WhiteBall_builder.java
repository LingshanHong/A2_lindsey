package A2_pool_game.model.builder.Ball;

import A2_pool_game.model.strategy.IBall_strategy;
import A2_pool_game.model.strategy.WhiteBall_strategy;

public class WhiteBall_builder implements IBall_builder {
    private String colour;
    private Double positionX;
    private Double positionY;
    private Double velocityX;
    private Double velocityY;
    private Double mass;

    private Ball ball;
    private IBall_strategy strategy;


    public WhiteBall_builder(String colour, Double positionX, Double positionY, Double velocityX, Double velocityY, Double mass) {
        this.colour = colour;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.strategy = new WhiteBall_strategy();

        this.ball = new CueBall();

    }

    // setting methods

    public WhiteBall_builder setStrategy() {
        this.ball.setStrategy(strategy);
        return this;
    }

    public WhiteBall_builder setColour() {
        this.ball.setColour(colour);
        return this;
    }

    public WhiteBall_builder setXPos() {
        this.ball.setXPos(positionX);
        return this;
    }

    public WhiteBall_builder setyPos() {
        this.ball.setyPos(positionY);
        return this;
    }

    public WhiteBall_builder setxVel() {
        this.ball.setxVel(velocityX);
        return this;
    }

    public WhiteBall_builder setyVel() {
        this.ball.setyVel(velocityY);
        return this;
    }

    public WhiteBall_builder setMass() {
        this.ball.setMass(mass);
        return this;
    }

    public Ball getBall() {
        return this.ball;
    }
    
}
