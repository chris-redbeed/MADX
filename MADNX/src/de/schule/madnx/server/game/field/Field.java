package de.schule.madnx.server.game.field;

import de.schule.madnx.server.game.Figure;

public class Field {
	
	public enum Type {
		SPAWN, START, FINISH, PATH;
	}
	public enum PlayerColor {
		RED, BLUE, GREEN, YELLOW;
	}
	
	private boolean set;
	private int x;
	private int y;
	public Type type;
	private Figure figure;
	private Field next;
	
	public Field(Field next, int x, int y){
		type = Type.PATH;
		this.x = x;
		this.y = y;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public void setNext(Field next) {
		this.next = next;
	}

	public void setSet(boolean set) {
		this.set = set;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Field getNext() {
		return next;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getSet() {
		return set;
	}
	
	public Figure getFigure() {
		return figure;
	}
	
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
}
