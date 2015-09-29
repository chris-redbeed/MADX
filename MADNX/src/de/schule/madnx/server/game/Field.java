package de.schule.madnx.server.game;

public class Field {
	private boolean set;
	private int x;
	private int y;
	private int fieldID;
	private Figure figure;
	private Field next;

	public void setFieldID(int fieldID) {
		this.fieldID = fieldID;
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

	public int getFieldID() {
		return fieldID;
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
