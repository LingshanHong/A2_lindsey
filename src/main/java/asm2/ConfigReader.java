package asm2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;



public class ConfigReader {

	public JSONObject parse(String name) {

		try {
			Optional<URI> configUrl = Optional.ofNullable(getClass().getResource("/" + name).toURI());
			if (configUrl.isEmpty()) {              
				throw new FileNotFoundException();
			}

			String path = configUrl.map(url -> url.getPath()).get();

			JSONParser parser = new JSONParser();
			Object object = parser.parse(new FileReader(path));
			JSONObject entireJsonObject = (JSONObject) object;

			return entireJsonObject;
			
        } catch (FileNotFoundException | URISyntaxException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
