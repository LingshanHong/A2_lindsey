package asm2.view;

import asm2.model.entity.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallViewImpl implements BallView {
    private Ball ball;
    private Circle ballView;

    public BallViewImpl(Ball ball) {
        this.ball = ball;
        ballView = new Circle(); 
    }

    public void resetBall(Ball ball, Double tableViewPosX, Double tableViewPosY) {
        this.ball = ball;
        update(tableViewPosX, tableViewPosY);
    }

    public void draw(Pane pane, Long tableX, Long tableY) {
        // convert string colour to Color javafx
        ballView.setFill(Color.valueOf(ball.getColour()));
        ballView.setStroke(Color.BLACK);

        // center table
        double xPaneMidpoint = pane.getWidth() / 2;
        double yPaneMidpoint = pane.getHeight() / 2;

        double xTableMidpoint = tableX / 2;
        double yTableMidpoint = tableY / 2;

        double xTableOffset = xPaneMidpoint - xTableMidpoint;
        double yTableOffset = yPaneMidpoint - yTableMidpoint;

        // check if ball position, including ball radius in table boundary
        /* TODO FUNCTION */

        ballView.setRadius(ball.getRadius());
        ballView.setCenterX(xTableOffset + ball.getPositionX());
        ballView.setCenterY(yTableOffset + ball.getPositionY());

        ballView.setViewOrder(100);
        pane.getChildren().add(ballView);
    }

    public void update(Double tableViewPosY, Double tableViewPosX) {
        // print ball position
        System.out.println("BallViewImpl.update() ball position: " + ball.getPositionX() + ", " + ball.getPositionY());

        ballView.setCenterX(ball.getPositionX() + tableViewPosX);
        ballView.setCenterY(ball.getPositionY() + tableViewPosY);
    }

    public Circle getBallView() {
        return ballView;
    }

    public Ball getBall() {
        return ball;
    }
}
