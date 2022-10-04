package A2_pool_game.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import A2_pool_game.model.IGame_logic;
import A2_pool_game.model.builder.Ball.Ball;
import A2_pool_game.model.builder.Table.Table;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Game_view {
    private final Scene scene;
    private final Pane pane;
    private final Long window_width;
    private final Long window_height;
    private final double frameDurationMilli;
    private final IGame_logic model;

    private final int OFFSET_X = 100;
    private final int OFFSET_Y = 100;

    private Double tableViewPosX;
    private Double tableViewPosY;

    private Table_view tableView;
    private List<IBall_view> ballViews;
    private IBall_view cueBallView;
    private Rectangle cueStick;
    Stick_view stickView;
    

    public Game_view(
            IGame_logic model,
            Long window_width,
            Long window_height,
            double frameDurationMilli) {

        this.model = model;
        this.window_width = window_width;
        this.window_height = window_height;
        this.frameDurationMilli = frameDurationMilli;

        pane = new Pane();
        scene = new Scene(pane, window_width + OFFSET_X, window_height + OFFSET_Y);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Table table = model.getTable();
        // center table
        double xPaneMidpoint = pane.getWidth() / 2;
        double yPaneMidpoint = pane.getHeight() / 2;

        double xTableMidpoint = table.getTableX() / 2;
        double yTableMidpoint = table.getTableY() / 2;

        double xTableOffset = xPaneMidpoint - xTableMidpoint;
        double yTableOffset = yPaneMidpoint - yTableMidpoint;

        this.tableViewPosX = xTableOffset;
        this.tableViewPosY = yTableOffset;
        this.tableView = new Table_view(model.getTable());
        this.tableView.drawTable(pane, xTableOffset, yTableOffset);

        this.ballViews = new ArrayList<>();
        initBalls();
        initCueStick();

        // handle cuestick events
        this.stickView = new Stick_view(model, cueStick, ballViews, cueBallView, scene);
                
    }

    private void initBalls() {
        List<Ball> balls = model.getBalls();
        // get table position
        Long tableX = model.getTable().getTableX();
        Long tableY = model.getTable().getTableY();
        
        for (Ball ball : balls) {
            if (ball.matchBall(model.getCueBall())) {
                cueBallView = new Ball_view(ball);
                cueBallView.drawBall(pane, tableX, tableY);
                ballViews.add(cueBallView);
            } else {
                IBall_view ballView = new Ball_view(ball);
                ballView.drawBall(pane, tableX, tableY);
                ballViews.add(ballView);
            }
        }
    }
    
    private void initCueStick() {
        /*the view of stick */
        int cueStickWidth = 10;
        this.cueStick = new Rectangle(0, 0, cueStickWidth, window_height);
        cueStick.setFill(Color.BROWN);
        cueStick.setArcHeight(15.0d);
        cueStick.setArcWidth(15.0d);
        
        // set position
        // cue stick position should be relative to the cue ball position, with some offset
        int stickOffsetX = cueStickWidth / 2;
        int stickOffsetY = model.getCueBall().getRadius();
        cueStick.setX(model.getCueBall().getxPos() + tableViewPosX - stickOffsetX);
        cueStick.setY(model.getCueBall().getyPos() + tableViewPosY + stickOffsetY + 5);

        // set view order
        cueStick.setViewOrder(1000);
        pane.getChildren().add(cueStick);
    }

    public Scene getScene() {
        return scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frameDurationMilli),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /** 
     * Draw the game state
     */ 
    private void draw() {
        model.run_game();
        // if game is resetted
        // update the view again
        if (model.check_reset()==true) {

            // reset the all ballView position
            List<Ball> balls = model.getBalls();
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                IBall_view ballView = ballViews.get(i);
                ballView.resetBall(ball, tableViewPosX, tableViewPosY);
                
                // if ballView not in pane, then add
                if (!pane.getChildren().contains(ballView.getBallShape())) {
                    pane.getChildren().add(ballView.getBallShape());
                }
            }

            // reset cue ball
            cueBallView.resetBall(model.getCueBall(), tableViewPosX, tableViewPosY);

            // reset cue stick
            int cueStickWidth = 10;
            int stickOffsetX = cueStickWidth / 2;
            int stickOffsetY = model.getCueBall().getRadius();
            cueStick.setX(model.getCueBall().getxPos() + tableViewPosX - stickOffsetX);
            cueStick.setY(model.getCueBall().getyPos() + tableViewPosY + stickOffsetY + 5);
            model.set_reset(false);
        } else {
            // remove ballviews if it doesn't exist in the model balls list
            List<Ball> modelBalls = model.getBalls();
            List<IBall_view> ballViewsToRemove = new ArrayList<>();
            for (IBall_view ballView : ballViews) {
                if (!modelBalls.contains(ballView.getBall())) {
                    ballViewsToRemove.add(ballView);
                }
            }
            if (!ballViewsToRemove.isEmpty()) {
                // ballViews.removeAll(ballViewsToRemove);
                // removing from pane, but not list
                for (IBall_view ballView : ballViewsToRemove) {
                    pane.getChildren().remove(ballView.getBallShape());
                }
            }
        }

        if (model.isIs_hit()) {
            cueStick.setVisible(false);
            for (IBall_view ballView : ballViews) {
                ballView.updateBall(tableViewPosX, tableViewPosY);
            }
            cueStick.setX(model.getCueBall().getxPos() + tableViewPosX - cueStick.getWidth() / 2);
            cueStick.setY(model.getCueBall().getyPos() + tableViewPosY + model.getCueBall().getRadius() + 5);
        } else {
            cueStick.setVisible(true);
        }
    }
}
