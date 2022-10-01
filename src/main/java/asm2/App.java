/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package asm2;

import javafx.application.Application;
import javafx.stage.Stage;

import asm2.model.factory.*;
import asm2.model.reader.Reader;
import asm2.view.GameWindow;
import asm2.model.GameEngine;
import asm2.model.GameEngineImpl;
import asm2.model.builder.*;
import asm2.model.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Map<String, String> params = getParameters().getNamed();

        final double frameDurationMilli = 17;
        ConfigReader configReader = new ConfigReader();
        JSONObject entireJsonObject = configReader.parse("config.json"); 

        
        // factory design pattern 
        ConfigReaderFactory configReaderFactory = new TableReaderFactory(); // concrete factory
        Reader reader = configReaderFactory.makeReader(); // concrete product
        
        List<HashMap<String, Object>> tables = reader.parse(entireJsonObject);

        configReaderFactory = new BallReaderFactory();
        reader = configReaderFactory.makeReader();
        List<HashMap<String, Object>> balls = reader.parse(entireJsonObject);

        // builder design pattern
        BallDirector ballDirector = new BallDirectorImpl();
        List<Ball> ballObjects = new ArrayList<Ball>();
        CueBall cueBall = new CueBall();

        boolean cueBallExist = false;
        for (int i = 0; i < balls.size(); i ++) {
            // save all the properties of the ball to a variable
            HashMap<String, Object> ball = balls.get(i);
            String colour = (String) ball.get("colour");

            Double positionX = (Double) ball.get("positionX");
            Double positionY = (Double) ball.get("positionY");
            Double velocityX = (Double) ball.get("velocityX");
            Double velocityY = (Double) ball.get("velocityY");
            Double mass = (Double) ball.get("mass");

            BallBuilder ballBuilder;
            if (colour.equals("white")) {
                ballBuilder = new CueBallBuilderImpl(colour, positionX, positionY, velocityX, velocityY, mass);
                cueBallExist = true;

                ballDirector.setBuilder(ballBuilder);
                ballDirector.construct();
                Ball ballObject = (CueBall) ballBuilder.getProduct();
                cueBall = (CueBall) ballObject;
                ballObjects.add(ballObject);

            } else {
                ballBuilder = new BallBuilderImpl(colour, positionX, positionY, velocityX, velocityY, mass);

                ballDirector.setBuilder(ballBuilder);
                ballDirector.construct();
                Ball ballObject = (Ball) ballBuilder.getProduct();
                ballObjects.add(ballObject);
            }
            

            // if (colour.equals("white")) {
            //     cueBall = (CueBall) ballBuilder.getProduct();
            //     ballObjects.add(cueBall); 
            // } else {
            // }
        }

        if (!cueBallExist) {
            System.out.println("Cue ball does not exist");
            // exist with exception
            System.exit(1);
        }

        TableDirector tableDirector = new TableDirectorImpl();
        HashMap<String, Object> table = tables.get(0);
        // get colour, tableX, tableY, friction
        String colour = (String) table.get("colour");
        Long tableX = (Long) table.get("tableX");
        Long tableY = (Long) table.get("tableY");
        Double friction = (Double) table.get("friction");

        TableBuilder tableBuilder = new TableBuilderImpl(colour, tableX, tableY, friction);
        tableDirector.setBuilder(tableBuilder);
        tableDirector.construct();
        Table tableObject = (Table)tableBuilder.getProduct();


        GameEngine gameEngine = new GameEngineImpl(tableObject, ballObjects, cueBall);
        System.out.println(tableObject.getTableX());
        GameWindow window = new GameWindow(gameEngine, tableObject.getTableX(), tableObject.getTableY(), frameDurationMilli);
        window.run();

        // show stage
        primaryStage.setTitle("Pool Game");
        primaryStage.setScene(window.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
        
}