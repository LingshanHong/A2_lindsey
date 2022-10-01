package asm2.model;

import java.util.List;

import asm2.model.entity.Ball;
import asm2.model.entity.Table;

public interface GameEngine {

    Table getTable();
    List<Ball> getBalls();
    Ball getCueBall();
    void shootBall(double powerX, double powerY);
    void update();
    boolean isShot();
    void removeBalls(Ball ball);
    void resetGame();
    boolean isResetted();
    void setReset(boolean b);
}
