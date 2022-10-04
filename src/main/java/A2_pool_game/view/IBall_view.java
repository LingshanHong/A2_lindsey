package A2_pool_game.view;

import A2_pool_game.model.builder.Ball.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public interface IBall_view {

    void drawBall(Pane pane, Long tableX, Long tableY);

    void updateBall(Double tableX, Double tableY);

    void resetBall(Ball ball, Double tableX, Double tableY);

    Ball getBall();
    Circle getBallShape();
}
