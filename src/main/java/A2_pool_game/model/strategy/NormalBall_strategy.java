package A2_pool_game.model.strategy;

import A2_pool_game.model.IGame_logic;
import A2_pool_game.model.builder.Ball.Ball;

public class NormalBall_strategy implements IBall_strategy {
    public void after_fall(Ball ball, IGame_logic gameEngineImpl) {
        ball.setInHole(true);
        gameEngineImpl.removeBalls(ball);
    }
}
