package A2_pool_game.model.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// Director and Product
public class BallReader implements IReader {

    public List<HashMap<String, Object>> parse(JSONObject entireJsonObject) {
        List<HashMap<String, Object>> balls = new ArrayList<HashMap<String, Object>>();

        // reading the "Balls" section:
        JSONObject jsonBalls = (JSONObject) entireJsonObject.get("Balls");

        // reading the "Balls: ball" array:
        JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");


        for (Object obj : jsonBallsBall) {
            HashMap<String, Object> ball = new HashMap<String, Object>();
            JSONObject jsonBall = (JSONObject) obj;

            String colour = (String) jsonBall.get("colour");
            Double positionX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
            Double positionY = (Double) ((JSONObject) jsonBall.get("position")).get("y");

            Double velocityX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
            Double velocityY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");

            Double mass = (Double) jsonBall.get("mass");

            ball.put("colour", colour);
            ball.put("positionX", positionX);
            ball.put("positionY", positionY);
            ball.put("velocityX", velocityX);
            ball.put("velocityY", velocityY);
            ball.put("mass", mass);
            balls.add(ball);

        }
        return balls;
        
    } 
}


