package A2_pool_game.model.builder.Ball;

public interface IBall_builder {

    Ball getBall();

    IBall_builder setColour();

    IBall_builder setXPos();

    IBall_builder setyPos();

    IBall_builder setxVel();


    IBall_builder setyVel();

    IBall_builder setMass();

    IBall_builder setStrategy();
    
}
