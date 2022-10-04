package A2_pool_game.view;
import A2_pool_game.model.builder.Ball.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball_view implements IBall_view {
    private Ball ball;
    private Circle ballShape;

    public Ball_view(Ball ball) {
        this.ball = ball;
        this.ballShape = new Circle();
    }
    public void drawBall(Pane pane, Long tableX, Long tableY) {
        //adjust the space between table view and pane
        double x_space = (pane.getWidth()-tableX)/2;
        double y_space = (pane.getHeight()-tableY)/2;
        ballShape.setRadius(ball.getRadius());
        ballShape.setCenterX(x_space + ball.getxPos());
        ballShape.setCenterY(y_space + ball.getyPos());

       // draw the actual table
        ballShape.setFill(Color.valueOf(ball.getColour()));
        ballShape.setStroke(Color.BLACK);
        ballShape.setViewOrder(1000);
        pane.getChildren().add(ballShape);
    }

    public void updateBall(Double tableY, Double tableX) {
        ballShape.setCenterX(ball.getxPos() + tableX);
        ballShape.setCenterY(ball.getyPos() + tableY);
    }

    public void resetBall(Ball ball, Double tableX, Double tableY) {
        this.ball = ball;
        updateBall(tableX, tableY);
    }

    public Circle getBallShape() {
        return ballShape;
    }

    public Ball getBall() {
        return ball;
    }
}
