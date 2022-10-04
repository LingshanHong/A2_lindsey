package A2_pool_game.model.strategy;

import A2_pool_game.model.IGame_logic;
import A2_pool_game.model.builder.Ball.Ball;

public interface IBall_strategy {
    void after_fall(Ball ball, IGame_logic pool_game);
}
