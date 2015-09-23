/**
 * 
 */
package de.schule.madnx.server.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.shared.JSONHelper;

/**
 * @author xgadscj
 *
 */
public class LoginHandler {

	private Logger logger = Logger.getLogger(LoginHandler.class.getName());

	private Properties prop = new Properties();;
	private OutputStream output;
	private InputStream input;
	private final static String PATH = "C:/Users/xgadscj/Desktop/User_Password.txt";

	public String handleMessage(String message) {
		try {
			File file = new File(PATH);
			input = new FileInputStream(file);
			prop.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get("method").toString());
		logger.info("method: " + method);
		if (method.equals("login")) {
			String user = JSONHelper.valueToString(jsonObject.get("user").toString());
			String password = JSONHelper.valueToString(jsonObject.get("password").toString());
			return handleLogin(user, password);
		} else if (method.equals("register")) {
			String user = JSONHelper.valueToString(jsonObject.get("user").toString());
			String password = JSONHelper.valueToString(jsonObject.get("password").toString());
			return handleRegister(user, password);
		}
		return "error";
	}

	private String handleLogin(String user, String password) {
		String pw = "";
		if (prop.contains(user)) {
			pw = prop.getProperty(user);
		}
		JsonObject json = new JsonObject();
		if (pw.equals(password)) {
			json.addProperty("method", "login");
			json.addProperty("result", "okay");
			return json.toString();
		}
		json.addProperty("method", "login");
		json.addProperty("result", "error");

		return json.toString();
	}

	private String handleRegister(String user, String password) {
		JsonObject json = new JsonObject();
		File file = new File(PATH);
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (prop.containsKey(user)) {
			json.addProperty("method", "register");
			json.addProperty("result", "error");
		} else {
			prop.setProperty(user, password);
			json.addProperty("method", "register");
			json.addProperty("result", "okay");
		}

		try {
			prop.store(output, null);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json.toString();
	}
}
