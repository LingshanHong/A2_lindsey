package A2_pool_game.model;

import java.util.List;

import A2_pool_game.model.builder.Ball.Ball;
import A2_pool_game.model.builder.Table.Table;

public interface IGame_logic {

    Table getTable();
    List<Ball> getBalls();
    Ball getCueBall();
    void hit_cueBall(double forceX, double forceY);
    void run_game();
    boolean isIs_hit();
    void removeBalls(Ball ball);
    void resetGame();
    boolean check_reset();
    void set_reset(boolean b);
}
