package org.example;

import javafx.scene.control.Tab;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONConfigReader {
    JSONParser parser = new JSONParser();

    public Components tableParse(String path) {

        try {

            Object object = parser.parse(new FileReader(path));

            // convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;
            // reading the Table section:
            JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

            // reading a value from the table section
            String tableColour = (String) jsonTable.get("colour");
//            setTableColour(tableColour);

            // reading a coordinate from the nested section within the table
            // note that the table x and y are of type Long (i.e. they are integers)
            Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
            Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");
//            setHeight(tableY);
//            setWidth(tableX);
            // getting the friction level.
            // This is a double which should affect the rate at which the balls slow down
            Double tableFriction = (Double) jsonTable.get("friction");

            // TODO: delete me, this is just a demonstration:
            TableFactory tf = new TableFactory(tableColour, tableFriction, tableX,tableY);
            Components tb = tf.create();
            return tb;
            //System.out.println("Table colour: " + tableColour + ", x: " + tableX + ", friction: " + tableFriction);



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Components> ballParse(String path) {
        ArrayList<Components> ballArray= new ArrayList<>();
        try{
            Object object = parser.parse(new FileReader(path));

            // convert Object to JSONObject
            JSONObject jsonObject = (JSONObject) object;
            JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

            // reading the "Balls: ball" array:
            JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");

            // reading from the array:
            for (Object obj : jsonBallsBall) {
                JSONObject jsonBall = (JSONObject) obj;

                // the ball colour is a String
                String colour = (String) jsonBall.get("colour");

                // the ball position, velocity, mass are all doubles
                Double positionX = (Double) ((JSONObject) jsonBall.get("position")).get("x");
                Double positionY = (Double) ((JSONObject) jsonBall.get("position")).get("y");

                Double velocityX = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
                Double velocityY = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");

                Double mass = (Double) jsonBall.get("mass");

                BallFactory bf = new BallFactory(colour, positionX, positionY,velocityX,velocityY, mass);
                Components b = bf.create();
                ballArray.add(b);

            }
            return ballArray;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
