package asm2.view;

import asm2.model.entity.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public interface BallView {
    void draw(Pane pane, Long tableX, Long tableY);
    Circle getBallView();
    void update(Double tableViewPosX, Double tableViewPosY);
    Ball getBall();
    void resetBall(Ball ball, Double tableViewPosX, Double tableViewPosY);
}
