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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.schule.madnx.shared.JSONHelper;
import de.schule.madnx.shared.Methods;

/**
 * @author xgadscj
 *
 */
public class LoginHandler {

	private Logger logger = Logger.getLogger(LoginHandler.class.getName());

	private Properties prop = new Properties();
	private OutputStream output;
	private InputStream input;
	private final static String PATH = "Macintosh HD/Benutzer/julianschultehullern/Dokument/MadX/User_Password.txt";

	public String handleMessage(String message, Session session) {
		
		// Läd die registrierten Nutzer aus einer TXT-Datei
		try {
			File file = new File(PATH);
			input = new FileInputStream(file);
			prop.load(input);
			input.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Property-Error", e);
			e.printStackTrace();
		}
		
		// Parst das json, um die Methode zu bekommen
		JsonParser parser = new JsonParser();
		JsonElement jelement = parser.parse(message);
		JsonObject jsonObject = jelement.getAsJsonObject();
		String method = JSONHelper.valueToString(jsonObject.get(Methods.METHOD).toString());
		logger.info("method: " + method);
		//Vergleich zwischen den Methoden
		if (method.equals(Methods.LOGIN)) {
			String user = JSONHelper.valueToString(jsonObject.get(Methods.USER).toString());
			String password = JSONHelper.valueToString(jsonObject.get(Methods.PASSWORD).toString());
			return handleLogin(user, password,session);
		} else if (method.equals(Methods.REGISTER)) {
			String user = JSONHelper.valueToString(jsonObject.get(Methods.USER).toString());
			String password = JSONHelper.valueToString(jsonObject.get(Methods.PASSWORD).toString());
			return handleRegister(user, password,session);
		}
		return "error";
	}

	// loggt den Spieler ein
	private String handleLogin(String user, String password, Session session) {
		String pw = "";
		if (prop.containsKey(user)) {
			pw = prop.getProperty(user);
		}
		JsonObject json = new JsonObject();
		if (pw.equals(password)) {
			// Setzt Sessionattribute
			session.getUserProperties().put(Methods.USER, user);
			session.getUserProperties().put(Methods.LOBBY, 0);
			
			json.addProperty(Methods.METHOD, Methods.LOGIN);
			json.addProperty("result", "okay");
			return json.toString();
		}
		json.addProperty(Methods.METHOD, Methods.LOGIN);
		json.addProperty("result", "error");

		return json.toString();
	}

	// erstellt einen neuen User und loggt dann den Spieler ein
	private String handleRegister(String user, String password, Session session) {
		JsonObject json = new JsonObject();
		File file = new File(PATH);
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			logger.log(Level.SEVERE, "Register-Error", e1);
			e1.printStackTrace();
		}
		if (prop.containsKey(user)) {
			json.addProperty(Methods.METHOD, Methods.REGISTER);
			json.addProperty("result", "error");
		} else {
			prop.setProperty(user, password);
			session.getUserProperties().put(Methods.USER, user);
			json.addProperty(Methods.METHOD, Methods.REGISTER);
			json.addProperty("result", "okay");
		}

		// Abspeichern der Properties in der TXT-Datei
		try {
			prop.store(output, null);
			output.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Property-Error", e);
			e.printStackTrace();
		}

		return json.toString();
	}
}
