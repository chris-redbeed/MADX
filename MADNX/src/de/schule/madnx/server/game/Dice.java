/**
 * 
 */
package de.schule.madnx.server.game;

/**
 * @author xgadscj
 *
 */
public class Dice {
	
	public static int dice() {
		int random = (int) ((Math.random()*6)+1);
		return random;
	}
}
