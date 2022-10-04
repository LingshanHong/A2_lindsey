package A2_pool_game.model.factory;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public interface IReader {
    List<HashMap<String, Object>> parse(JSONObject entireJsonObject);
}
