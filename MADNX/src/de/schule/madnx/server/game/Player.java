/**
 * 
 */
package de.schule.madnx.server.game;

import java.util.ArrayList;

/**
 * @author xgadscj
 *
 */
public class Player {
	
	private boolean wins;
	private String user;
	
	private ArrayList<Figure> figures;
	
	public void setWins(boolean wins) {
		this.wins = wins;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setFigures(ArrayList<Figure> figures) {
		this.figures = figures;
	}
	
	public ArrayList<Figure> getFigures() {
		return figures;
	}
	
	public String getUser() {
		return user;
	}
	
	public boolean getWins() {
		return wins;
	}
	
}
