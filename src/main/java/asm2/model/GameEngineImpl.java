package asm2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import asm2.model.entity.*;
import javafx.geometry.Point2D;
import javafx.util.Pair;


public class GameEngineImpl implements GameEngine {

    private Table table;
    private List<Ball> balls;
    private Ball cueBall;

    private Table originTable;
    private List<Ball> originBalls;
    private Ball originCueBall;

    private CollisionEngine collisionEngine;

    private boolean isShot;
    private boolean resetted;

    public GameEngineImpl(Table table, List<Ball> balls, Ball cueBall) {
        this.table = table;
        this.balls = balls;

        // deep copy table to originTable
        // deep copy balls to originBalls
        this.originTable = new Table(table);
        this.originBalls = new ArrayList<>();
        for (Ball ball : balls) {
            // match cue ball
            if (ball.matchBall(cueBall)) {
                this.originCueBall = new Ball(cueBall);
                this.originBalls.add(this.originCueBall);
            } else {
                this.originBalls.add(new Ball(ball));
            }
        }
        


        this.cueBall = cueBall;
        this.collisionEngine = new CollisionEngine();
        this.isShot = false;

        for (int i = 0; i < balls.size(); i ++) {
            Ball ball = balls.get(i);
            // print all elements in one line
            System.out.println(ball.getColour() + " " + ball.getPositionX() + " " + ball.getPositionY() + " " + ball.getVelocityX() + " " + ball.getVelocityY() + " " + ball.getMass());
        }
    }

    public void shootBall(double powerX, double powerY) {
        // System.out.println("Shot ball");
        // change cueball velocity
        cueBall.setVelocityX(powerX);
        cueBall.setVelocityY(powerY);
        
        isShot = true;
        System.out.println(isShot);
    }

    public void update() {

        // if cueball is shot
        if (isShot) {
            System.out.println("GAMEENGINE UPDATE");
            /* Friction */
            // get table friction
            double friction = table.getFriction();

            // update all balls position
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                // get ball velocity
                double velocityX = ball.getVelocityX().intValue();
                double velocityY = ball.getVelocityY().intValue();
                System.out.println(ball);

                if (velocityX == 0 && velocityY == 0) {
                    // ball not moving
                    continue;
                }
                // update ball velocity
                // set cueBall new velocity after friction
                if (ball.getVelocityX() > 0) {
                    ball.setVelocityX(ball.getVelocityX() - friction);
                } else if (ball.getVelocityX() < 0) {
                    ball.setVelocityX(ball.getVelocityX() + friction);
                }

                if (ball.getVelocityY() > 0) {
                    ball.setVelocityY(ball.getVelocityY() - friction);
                } else if (ball.getVelocityY() < 0) {
                    ball.setVelocityY(ball.getVelocityY() + friction);
                }
                // update ball position
                ball.setPositionX(ball.getPositionX() + velocityX);
                ball.setPositionY(ball.getPositionY() + velocityY);
            }
            // cueBall.setPositionX(cueBall.getPositionX() + cueBall.getVelocityX());
            // cueBall.setPositionY(cueBall.getPositionY() + cueBall.getVelocityY());

            // print velocity information
            System.out.println("Cueball velocity: " + cueBall.getVelocityX().intValue() + " " + cueBall.getVelocityY().intValue());

            /* Table Collision */
            // check for all balls bounce on table
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                collisionEngine.resolveTableCollision(ball, table);
            }
            

            /* Ball Collision */
            // check for all balls bounce on other balls
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                for (int j = i + 1; j < balls.size(); j ++) {
                    // check if they collide
                    if (collisionEngine.isCollide(ball, balls.get(j))) {
                        System.out.println("Collided!");
                        /* */
                        // resolve collision
                        Ball otherBall = balls.get(j);
                        double x1 = ball.getPositionX();   
                        double y1 = ball.getPositionY();
                        double x2 = otherBall.getPositionX();
                        double y2 = otherBall.getPositionY();
                        double v1x = ball.getVelocityX();
                        double v1y = ball.getVelocityY();
                        double v2x = otherBall.getVelocityX();
                        double v2y = otherBall.getVelocityY();
                        double m1 = ball.getMass();
                        double m2 = otherBall.getMass();
                        Pair<Point2D, Point2D> collision = collisionEngine.calculateCollision(new Point2D(x1, y1), new Point2D(v1x, v1y), m1, new Point2D(x2, y2), new Point2D(v2x, v2y), m2);
                        // update ball velocity
                        // print ball collision value and otherBall collision value
                        System.out.println("Ball collision: " + collision.getKey().getX() + " " + collision.getKey().getY());
                        System.out.println("OtherBall collision: " + collision.getValue().getX() + " " + collision.getValue().getY());

                        ball.setVelocityX(collision.getKey().getX());
                        ball.setVelocityY(collision.getKey().getY());
                        otherBall.setVelocityX(collision.getValue().getX());
                        otherBall.setVelocityY(collision.getValue().getY());
                    }
                    
                }
            }



            /*  check if all balls are not moving */
            boolean isAllBallsNotMoving = true;
            // compare the balls previous and current position
            // if the position is the same, then the ball is not moving
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                // get ball velocity
                double velocityX = ball.getVelocityX().intValue();
                double velocityY = ball.getVelocityY().intValue();

                if (velocityX != 0 || velocityY != 0) {
                    // ball is moving
                    isAllBallsNotMoving = false;
                    break;
                }
            }

            // if all balls are not moving, set isShot to false
            if (isAllBallsNotMoving) {
                System.out.println("All balls are not moving");
                isShot = false;
            }

            // // set prevPosition to the current position
            // for (int i = 0; i < balls.size(); i ++) {
            //     Ball ball = balls.get(i);
            //     ball.setPrevPositionX(ball.getPositionX());
            //     ball.setPrevPositionY(ball.getPositionY());
            // }

            // check if ball overlap with one of the table pocket
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                // check if ball is in pocket
                if (collisionEngine.isInPocket(ball, table)) {
                    ball.behave(this);
                    // print ball information
                    System.out.println("Ball in pocket: " + ball.getColour() + " " + ball.getPositionX() + " " + ball.getPositionY() + " " + ball.getVelocityX() + " " + ball.getVelocityY() + " " + ball.getMass());
                }
            }
        }
    }

    @Override
    public void removeBalls(Ball ball) {
        System.out.println("Num balls: " + balls.size());
        System.out.println("Remove ball: " + ball.getColour());
        balls.remove(ball);
        System.out.println(balls.size() + " balls left");
    }

    @Override
    public void resetGame() {
        // reset table and balls to originTable and originBalls
        table = new Table(originTable);
        balls = new ArrayList<>();
        System.out.println(originBalls.size());
        for (int i = 0; i < originBalls.size(); i ++) {
            if (originBalls.get(i).matchBall(originCueBall)) {
                // cue ball
                System.out.print("FOUND ORIGINAL CUE BALL");
                cueBall = new Ball(originBalls.get(i));
                balls.add(cueBall);
            } else {
                balls.add(new Ball(originBalls.get(i)));
            }
        }
        // reset isShot to false
        isShot = false;

        // reset cueBall position
        System.out.println("Game Resetted");
        this.resetted = true;
    }

    @Override
    public boolean isResetted() {
        return this.resetted;
    }

    @Override
    public void setReset(boolean reset) {
        this.resetted = reset;
    }

    @Override
    public boolean isShot() {
        return isShot;
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
    }

    @Override
    public Ball getCueBall() {
        return cueBall;
    }
}
