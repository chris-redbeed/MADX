/**
 * 
 */
package de.schule.madnx.server.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;
import de.schule.madnx.shared.coder.HighScoreMapCoder;

/**
 * @author xgadscj
 *
 */
public class HighScoreHandler {
	private Logger logger = Logger.getLogger(HighScoreHandler.class.getName());
	
	private Properties prop = new Properties();;
	private InputStream input;
	private final static String PATH = "C:/Users/xgadscj/Desktop/Highscore.txt";

	public String handleMessage(String message, Session session) {
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		if (method.equals(Methods.HIGHSCORE)) {
			// Holt sich die HighScore-Daten aus einer Txt-Datei
			try {
				File file = new File(PATH);
				input = new FileInputStream(file);
				prop.load(input);
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Map<String, Integer> result = new HashMap<String, Integer>();
			Set<String> keySet = prop.stringPropertyNames();
			for (String s: keySet) {
				result.put(s, Integer.valueOf(prop.get(s).toString()));
			}
			
			Map<String, Integer> sortedResult = sortByComparator(result);
			String resultString = HighScoreMapCoder.encode(sortedResult);
			
			JsonObject json = new JsonObject();
			json.addProperty(Methods.METHOD, Methods.HIGHSCORE);
			json.addProperty("result", resultString);

			return json.toString();
		}
		return "error";
	}
	
	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap) {

		// Konvertiert die Map zu einer Liste
		List<Map.Entry<String, Integer>> list = 
			new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		// Sortiert die Liste mit einem Komperator und dreht sie anschließend um
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
                                           Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		Collections.reverse(list);

		// Konvertiert die sortierte Liste wieder in eine Map
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
