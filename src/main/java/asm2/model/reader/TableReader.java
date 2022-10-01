package asm2.model.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TableReader implements Reader {
    
    public List<HashMap<String, Object>> parse(JSONObject entireJsonObject) {
        List<HashMap<String, Object>> tables = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> table = new HashMap<String, Object>();

        // reading the Table section:
        JSONObject jsonTable = (JSONObject) entireJsonObject.get("Table");

        // reading a value from the table section
        String tableColour = (String) jsonTable.get("colour");

        // reading a coordinate from the nested section within the table
        // note that the table x and y are of type Long (i.e. they are integers)
        Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
        Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

        // getting the friction level.
        // This is a double which should affect the rate at which the balls slow down
        Double tableFriction = (Double) jsonTable.get("friction");

        table.put("colour", tableColour);
        table.put("tableX", tableX);
        table.put("tableY", tableY);
        table.put("friction", tableFriction);
        tables.add(table);

        // System.out.println("Table colour: " + tableColour + ", x: " + tableX + ", y: " + tableY + ", friction: " + tableFriction);
        return tables;        
    } 
}


