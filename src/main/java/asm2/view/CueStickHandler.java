package asm2.view;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.List;

import asm2.model.GameEngine;


public class CueStickHandler {
    private final GameEngine model;
    private final Rectangle cueStick;
    private List<BallView> ballViews;
    private BallView cueBallView;
    private Scene scene;

    private Rotate rotate;
    private boolean isDragged;
    private Translate translate;

    private double powerX;
    private double powerY;

    public CueStickHandler(GameEngine model, Rectangle cueStick, List<BallView> ballViews, BallView cueBallView, Scene scene) {
        this.model = model;
        this.cueStick = cueStick;
        this.ballViews = ballViews;
        this.cueBallView = cueBallView;
        this.scene = scene;
        this.isDragged = false;

        this.rotate = new Rotate();
        this.translate = new Translate();

        rotate.pivotXProperty().bind(cueBallView.getBallView().centerXProperty());
        rotate.pivotYProperty().bind(cueBallView.getBallView().centerYProperty());
        cueStick.getTransforms().addAll(rotate, translate);

        this.cueBallView.getBallView().setOnMouseDragged(this::handleMouseDragged);
        this.cueBallView.getBallView().setOnMouseReleased(this::handleMouseReleased);


    }

    // set on mouse drag
    public void handleMouseDragged(MouseEvent event) {
        this.isDragged = true;
        // System.out.println("Dragged on cuestick!");
        
        double newX = event.getSceneX();
        double newY = event.getSceneY();
        // System.out.println("New X: " + newX);
        // System.out.println("New Y: " + newY);

        cueBallView.getBallView().setCursor(Cursor.CLOSED_HAND);
        Double angle = Math.toDegrees(Math.atan2(newY - cueBallView.getBallView().getCenterY(), newX - cueBallView.getBallView().getCenterX()));
        // Double angle = Math.toDegrees(Math.atan2(newY - cueBallView.getBallView().getCenterX(), newX - cueBallView.getBallView().getCenterY()));

        // System.out.println("Angle: " + angle);
        this.rotate.setAngle(angle);

        // get the new cuestick position
        Point2D topLeftInParent = this.cueStick.localToParent(this.cueStick.getX(), this.cueStick.getY());
        double newXPos = topLeftInParent.getX();
        double newYPos = topLeftInParent.getY();
        // System.out.println("New cueStick position after rotation: " + newXPos + ", " + newYPos);


        // power
        // check the difference between the mouse position and cue ball position
        // if the difference is small, the power is small
        // if the difference is large, the power is large
        double diffX = newX - cueBallView.getBallView().getCenterX();
        double diffY = newY - cueBallView.getBallView().getCenterY();
        // this.translate.setX(diffX);
        // this.translate.setY(diffY);
        double power = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        // System.out.println("Diff X: " + diffX + ", Diff Y: " + diffY);
        // System.out.println("Power: " + power);
        power = Math.min(40, power);


        // translate cue stick
        if (angle == 90) {
            this.translate.setY(power);
            this.powerX = power;
            this.powerY = 0;
        } else if (angle == -90) {
            this.translate.setY(power);
            this.powerX = -power;
            this.powerY = 0;
        } else if (angle == 180) {
            this.translate.setY(power);
            this.powerX = 0;
            this.powerY = power;
        } else if (angle == 0) {
            this.translate.setY(power);
            this.powerX = 0;
            this.powerY = -power;
        } else {
            double absAngle = Math.abs(angle);
            if (absAngle > 90) absAngle = absAngle - 90;
            else if (absAngle < 90) absAngle = 90 - absAngle;

            double y;
            if (absAngle < 45) {
                y = power * Math.sin(Math.toRadians(90 - absAngle));
            } else {
                y = power * Math.sin(Math.toRadians(absAngle));
            }

            // print angle
            // System.out.println("Abs Angle: " + absAngle);
            // System.out.println("Y: " + y);
            // double z = power * Math.cos(Math.toRadians(angle));

            // check stick in which quadrant
            if (angle > 0 && angle < 90) {
                this.translate.setY(y);
                this.powerX = power * Math.cos(Math.toRadians(absAngle));
                this.powerY = -1 * (power * Math.sin(Math.toRadians(absAngle)));
                // this.translate.setZ(z);
            } else if (angle > 90 && angle < 180) {
                this.translate.setY(y);
                this.powerX = power * Math.cos(Math.toRadians(absAngle));
                this.powerY = power * Math.sin(Math.toRadians(absAngle));

            } else if (angle > -90 && angle < 0) {
                this.translate.setY(y);
                this.powerX = -1 * (power * Math.cos(Math.toRadians(absAngle)));
                this.powerY = -1 * (power * Math.sin(Math.toRadians(absAngle)));
            } else if (angle > -180 && angle < -90) {
                this.translate.setY(y);
                this.powerX = -1 * (power * Math.cos(Math.toRadians(absAngle)));
                this.powerY = power * Math.sin(Math.toRadians(absAngle));
            }
        } 
    }

    // set on mouse released
    public void handleMouseReleased(MouseEvent event) {
        // cueStick.setRotate(Math.atan2(event.getY() - cueStick.getTranslateY(), event.getX() - cueStick.getTranslateX()) * 180 / Math.PI);
        // cueStick.setTranslateX(event.getX() - cueStick.getWidth() / 2);
        // cueStick.setTranslateY(event.getY() - cueStick.getHeight() / 2);
        // model.shootBall(cueStick.getRotate());
        if (isDragged) {

            this.isDragged = false;
            this.translate.setZ(0);
            this.translate.setY(0);
            System.out.println("Released after dragged!");
            cueBallView.getBallView().setCursor(Cursor.HAND);

            // print powerX and powerY
            System.out.println("Power X: " + this.powerX);
            System.out.println("Power Y: " + this.powerY);
            model.shootBall(this.powerX, this.powerY);
        }
    }



    

    
}
