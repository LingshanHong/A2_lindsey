package asm2.model.reader;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public interface Reader {
    List<HashMap<String, Object>> parse(JSONObject entireJsonObject);
}
