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
	
	public Player(String user) {
		figures = new ArrayList<>();
		this.user = user;
		wins = false;
	}
	
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
	
	public boolean hasFiguresOnField() {
		for (Figure f: this.figures) {
			if (f.getSet()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasFigureInHouse(int id) {
		for (Figure f: this.figures) {
			if (f.getId() != id && !f.getSet()) {
				return true;
			}
		}
		return false;
	}
	
}
