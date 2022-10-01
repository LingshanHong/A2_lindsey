package asm2.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import asm2.model.GameEngine;
import asm2.model.entity.Ball;
import asm2.model.entity.Table;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameWindow {

    private final Long width;
    private final Long height;
    private final double frameDurationMilli;
    private final Scene scene;
    private final Pane pane;
    private final GameEngine model;

    private final int OFFSET_X = 100;
    private final int OFFSET_Y = 100;

    private Double tableViewPosX;
    private Double tableViewPosY;

    private TableView tableView;
    private List<BallView> ballViews;
    private BallView cueBallView;
    private Rectangle cueStick;

    CueStickHandler cueStickHandler;
    

    public GameWindow(
            GameEngine model,
            Long width,
            Long height,
            double frameDurationMilli) {

        this.model = model;
        this.width = width;
        this.height = height;
        this.frameDurationMilli = frameDurationMilli;

        pane = new Pane();
        scene = new Scene(pane, width + OFFSET_X, height + OFFSET_Y);
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
        this.tableView = new TableView(model.getTable());
        this.tableView.draw(pane, xTableOffset, yTableOffset);

        this.ballViews = new ArrayList<>();
        initBalls();
        initCueStick();

        // handle cuestick events
        this.cueStickHandler = new CueStickHandler(model, cueStick, ballViews, cueBallView, scene);
                
    }

    private void initBalls() {
        List<Ball> balls = model.getBalls();
        // get table position
        Long tableX = model.getTable().getTableX();
        Long tableY = model.getTable().getTableY();
        
        for (Ball ball : balls) {
            if (ball.matchBall(model.getCueBall())) {
                cueBallView = new BallViewImpl(ball);
                cueBallView.draw(pane, tableX, tableY);
                ballViews.add(cueBallView);
            } else {
                BallView ballView = new BallViewImpl(ball);
                ballView.draw(pane, tableX, tableY);
                ballViews.add(ballView);
            }
        }
    }
    
    private void initCueStick() {
        int cueStickWidth = 10;
        this.cueStick = new Rectangle(0, 0, cueStickWidth, height);
        cueStick.setFill(Color.BROWN);
        cueStick.setArcHeight(20.0d);
        cueStick.setArcWidth(20.0d);
        
        // set position
        // cue stick position should be relative to the cue ball position, with some offset
        int stickOffsetX = cueStickWidth / 2;
        int stickOffsetY = model.getCueBall().getRadius();
        cueStick.setX(model.getCueBall().getPositionX() + tableViewPosX - stickOffsetX);
        cueStick.setY(model.getCueBall().getPositionY() + tableViewPosY + stickOffsetY + 5);

        // set view order
        cueStick.setViewOrder(100);
        pane.getChildren().add(cueStick);
        System.out.println("INIT CUE STICK");
        // print position
        System.out.println("Cue stick position: " + cueStick.getX() + ", " + cueStick.getY());
        System.out.println(model.getCueBall().getPositionX());
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
        model.update();

        // if game is resetted
        // update the view again
        if (model.isResetted()) {
            // // remove all balls
            // for (BallView ballView : ballViews) {
            //     pane.getChildren().remove(ballView.getBallView());
            // }
            // ballViews.clear();
            // // reinit balls
            // initBalls();

            // // reinit cue stick
            // pane.getChildren().remove(cueStick);
            // initCueStick();
            
            // reset the all ballView position
            List<Ball> balls = model.getBalls();
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                BallView ballView = ballViews.get(i);
                ballView.resetBall(ball, tableViewPosX, tableViewPosY);
                
                // if ballView not in pane, then add
                if (!pane.getChildren().contains(ballView.getBallView())) {
                    pane.getChildren().add(ballView.getBallView());
                }
            }

            // reset cue ball
            cueBallView.resetBall(model.getCueBall(), tableViewPosX, tableViewPosY);

            // reset cue stick
            int cueStickWidth = 10;
            int stickOffsetX = cueStickWidth / 2;
            int stickOffsetY = model.getCueBall().getRadius();
            cueStick.setX(model.getCueBall().getPositionX() + tableViewPosX - stickOffsetX);
            cueStick.setY(model.getCueBall().getPositionY() + tableViewPosY + stickOffsetY + 5);

            model.setReset(false);
        } else {
            // remove ballviews if it doesn't exist in the model balls list
            List<Ball> modelBalls = model.getBalls();
            List<BallView> ballViewsToRemove = new ArrayList<>();
            for (BallView ballView : ballViews) {
                if (!modelBalls.contains(ballView.getBall())) {
                    ballViewsToRemove.add(ballView);
                }
            }
            if (!ballViewsToRemove.isEmpty()) {
                // ballViews.removeAll(ballViewsToRemove);
                // removing from pane, but not list
                for (BallView ballView : ballViewsToRemove) {
                    pane.getChildren().remove(ballView.getBallView());
                }
            }
        }
        // System.out.println(model.isShot());
        if (model.isShot()) {
            System.out.println("SHOTTTT");
            cueStick.setVisible(false);
            // update all balls position
            System.out.println(ballViews.size());
            for (BallView ballView : ballViews) {
                System.out.println("UPDATINGGGGGGGGG");
                ballView.update(tableViewPosX, tableViewPosY);
            }
            cueStick.setX(model.getCueBall().getPositionX() + tableViewPosX - cueStick.getWidth() / 2);
            cueStick.setY(model.getCueBall().getPositionY() + tableViewPosY + model.getCueBall().getRadius() + 5);
        } else {
            cueStick.setVisible(true);
        }
    }
}
