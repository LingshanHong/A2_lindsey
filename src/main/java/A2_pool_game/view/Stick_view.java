package A2_pool_game.view;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import java.util.List;
import A2_pool_game.model.IGame_logic;

public class Stick_view {
    private final IGame_logic model;
    private Scene scene;
    private final Rectangle game_stick;
    private boolean is_dragged;
    private double forceX;
    private double forceY;
    private Rotate rotate;
    private Translate translate;
    private List<IBall_view> balls_view;
    private IBall_view cueBallView;

    public Stick_view(IGame_logic model, Rectangle game_stick, List<IBall_view> balls_view, IBall_view cueBallView, Scene scene) {
        this.model = model;
        this.scene = scene;
        this.game_stick = game_stick;
        this.is_dragged = false;

        this.balls_view = balls_view;
        this.cueBallView = cueBallView;

        this.translate = new Translate();
        this.rotate = new Rotate();
        rotate.pivotXProperty().bind(cueBallView.getBallShape().centerXProperty());
        rotate.pivotYProperty().bind(cueBallView.getBallShape().centerYProperty());

        game_stick.getTransforms().addAll(rotate, translate);

        this.cueBallView.getBallShape().setOnMouseDragged(this::dragStick);
        this.cueBallView.getBallShape().setOnMouseReleased(this::release_stick);

    }

    public double cal_stick_angle(double angle){
        double absAngle = Math.abs(angle);
        if (absAngle > 90) {
            absAngle = absAngle - 90;
        }else if (absAngle < 90) {
            absAngle = 90 - absAngle;
        }
        return absAngle;
    }

    // user drag the stick
    //and calculate the force
    public void dragStick(MouseEvent event) {
        this.is_dragged = true;
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();

        cueBallView.getBallShape().setCursor(Cursor.CLOSED_HAND);
        Double angle = Math.toDegrees(Math.atan2(mouseY - cueBallView.getBallShape().getCenterY(), mouseX - cueBallView.getBallShape().getCenterX()));
        double absAngle = cal_stick_angle(angle);
        this.rotate.setAngle(angle);

        // the power user hit the cue ball is based on the distance from cue ball
        double diffX = mouseX - cueBallView.getBallShape().getCenterX();
        double diffY = mouseY - cueBallView.getBallShape().getCenterY();
        double power = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
        power = Math.min(40, power);

        double x_force=power * Math.cos(Math.toRadians(absAngle));
        double y_force=power * Math.sin(Math.toRadians(absAngle));

        // set force based on angle quadrant
        if (angle > 0 && angle < 90) {
            this.forceX = x_force;
            this.forceY = y_force*-1;
        } else if (angle > 90 && angle < 180) {
            this.forceX = x_force;
            this.forceY = y_force;
        } else if (angle > -90 && angle < 0) {
            this.forceX = x_force*-1;
            this.forceY = y_force*-1;
        } else if (angle > -180 && angle < -90) {
            this.forceX = x_force*-1;
            this.forceY = y_force*-1;
            }
        }

    // set on mouse released
    public void release_stick(MouseEvent drag) {
        if (is_dragged) {
            this.is_dragged = false;
            this.translate.setZ(0);
            this.translate.setY(0);
            cueBallView.getBallShape().setCursor(Cursor.CLOSED_HAND);
            model.hit_cueBall(this.forceX, this.forceY);
        }
    }



    

    
}
