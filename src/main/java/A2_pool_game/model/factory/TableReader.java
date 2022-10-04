package A2_pool_game.model.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class TableReader implements IReader {
    
    public List<HashMap<String, Object>> parse(JSONObject entireJsonObject) {
        List<HashMap<String, Object>> tables = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> table = new HashMap<String, Object>();

        // reading the Table section:
        JSONObject jsonTable = (JSONObject) entireJsonObject.get("Table");
        String tableColour = (String) jsonTable.get("colour");

        Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
        Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");
        Double tableFriction = (Double) jsonTable.get("friction");

        table.put("colour", tableColour);
        table.put("tableX", tableX);
        table.put("tableY", tableY);
        table.put("friction", tableFriction);
        tables.add(table);

        return tables;        
    } 
}


