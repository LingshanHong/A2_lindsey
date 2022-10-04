package A2_pool_game.model;

import java.util.ArrayList;
import java.util.List;

import A2_pool_game.model.builder.Ball.Ball;
import A2_pool_game.model.builder.Table.Table;
import javafx.geometry.Point2D;
import javafx.util.Pair;


public class Game_logic implements IGame_logic {

    private Table table;
    private List<Ball> balls;
    private Ball cueBall;
    private Table origin_table;
    private List<Ball> origin_balls;
    private Ball origin_cueBall;

    private CollisionFunction collisionFunction;
    private boolean is_hit;
    private boolean need_reset;

    public Game_logic(Table table, List<Ball> balls, Ball cueBall) {
        this.table = table;
        this.balls = balls;
        this.cueBall = cueBall;

        this.origin_table = table ;
        this.origin_balls = balls;
        this.origin_cueBall =cueBall;

        this.collisionFunction = new CollisionFunction();
        this.is_hit = false;
    }

    public void hit_cueBall(double forceX, double forceY) {
        cueBall.setxVel(forceX);
        cueBall.setyVel(forceY);
        is_hit = true;

    }

    public void run_game() {

        if (is_hit) {
            double friction = table.getFriction();

            // update all balls position
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                double vx = ball.getxVel().intValue();
                double vy = ball.getyVel().intValue();

                //when ball stay still
                if (vx == 0 && vy == 0) {
                    continue;
                }

                // update ball velocity
                // set cueBall new velocity after friction
                if (ball.getxVel() > 0) {
                    ball.setxVel(ball.getxVel() - friction);
                } else if (ball.getxVel() < 0) {
                    ball.setxVel(ball.getxVel() + friction);
                }

                if (ball.getyVel() > 0) {
                    ball.setyVel(ball.getyVel() - friction);
                } else if (ball.getyVel() < 0) {
                    ball.setyVel(ball.getyVel() + friction);
                }
                // update ball position
                ball.setXPos(ball.getxPos() + vx);
                ball.setyPos(ball.getyPos() + vy);
            }

            /* Table Collision */
            // check for all balls bounce on table
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                collisionFunction.resolveTableCollision(ball, table);
            }
            

            /* Ball Collision */
            // check for all balls bounce on other balls
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                for (int j = i + 1; j < balls.size(); j ++) {
                    // check if they collide
                    if (collisionFunction.isCollide(ball, balls.get(j))) {
                        System.out.println("Collided!");
                        /* */
                        // resolve collision
                        Ball otherBall = balls.get(j);
                        double x1 = ball.getxPos();
                        double y1 = ball.getyPos();
                        double x2 = otherBall.getxPos();
                        double y2 = otherBall.getyPos();
                        double v1x = ball.getxVel();
                        double v1y = ball.getyVel();
                        double v2x = otherBall.getxVel();
                        double v2y = otherBall.getyVel();
                        double m1 = ball.getMass();
                        double m2 = otherBall.getMass();
                        Pair<Point2D, Point2D> collision = collisionFunction.calculateCollision(new Point2D(x1, y1), new Point2D(v1x, v1y), m1, new Point2D(x2, y2), new Point2D(v2x, v2y), m2);
                        // update ball velocity
                        // print ball collision value and otherBall collision value
                        System.out.println("Ball collision: " + collision.getKey().getX() + " " + collision.getKey().getY());
                        System.out.println("OtherBall collision: " + collision.getValue().getX() + " " + collision.getValue().getY());

                        ball.setxVel(collision.getKey().getX());
                        ball.setyVel(collision.getKey().getY());
                        otherBall.setxVel(collision.getValue().getX());
                        otherBall.setyVel(collision.getValue().getY());
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
                double velocityX = ball.getxVel().intValue();
                double velocityY = ball.getyVel().intValue();

                if (velocityX != 0 || velocityY != 0) {
                    // ball is moving
                    isAllBallsNotMoving = false;
                    break;
                }
            }

            // if all balls are not moving, set isShot to false
            if (isAllBallsNotMoving) {
                System.out.println("All balls are not moving");
                is_hit = false;
            }

            // check if ball overlap with one of the table pocket
            for (int i = 0; i < balls.size(); i ++) {
                Ball ball = balls.get(i);
                // check if ball is in pocket
                if (collisionFunction.isInPocket(ball, table)) {
                    ball.behave(this);
                    // print ball information
                    System.out.println("Ball in pocket: " + ball.getColour() + " " + ball.getxPos() + " " + ball.getyPos() + " " + ball.getxVel() + " " + ball.getyVel() + " " + ball.getMass());
                }
            }
        }
    }


    @Override
    public void resetGame() {
        // reset game tabke and balls
        table = new Table(origin_table);
        balls = new ArrayList<>();
        for (int i = 0; i < origin_balls.size(); i ++) {
            if (origin_balls == origin_cueBall) {
                cueBall = new Ball(origin_balls.get(i));
                balls.add(cueBall);
            } else {
                balls.add(new Ball(origin_balls.get(i)));
            }
        }
        // need to hit the cue ball again
        is_hit = false;
        this.need_reset = true;
    }

    @Override
    public boolean check_reset() {return this.need_reset;}
    @Override
    public void set_reset(boolean reset) {this.need_reset = reset;}
    @Override
    public boolean isIs_hit() {return is_hit;}
    public void removeBalls(Ball ball) {balls.remove(ball);}
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
