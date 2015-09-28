/**
 * 
 */
package de.schule.madnx.server.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author xgadscj
 *
 */
public class MapGenerator {

	private Logger logger = Logger.getLogger(MapGenerator.class.getName());
	
	private FileReader fileReader;
    private BufferedReader bufferedReader;
    
    public MapGenerator() {
    	try {
			fileReader = new FileReader("");
			bufferedReader = new BufferedReader(fileReader);
			
			while (bufferedReader.read() != -1) {
				String readLine = bufferedReader.readLine();
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "FileLoad-Error", e);
			e.printStackTrace();
		}
    }
	
}
