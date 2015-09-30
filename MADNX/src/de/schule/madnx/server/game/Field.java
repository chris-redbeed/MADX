package de.schule.madnx.server.game;

public class Field {
	
	public enum Type {
		SPAWN, START, FINISH, PATH;
	}
	
	private boolean set;
	private int x;
	private int y;
	private int fieldId;
	private Type type;
	private Figure figure;
	private Field next;
	
	public Field(){
		
	}
	
	public Field(Type typ, int fieldId, int x, int y){
		this.type = typ;
		this.fieldId = fieldId;
		this.x = x;
		this.y = y;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public void setFieldID(int fieldID) {
		this.fieldId = fieldID;
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
		return fieldId;
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
