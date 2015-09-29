/**
 * 
 */
package de.schule.madnx.shared;

/**
 * @author xgadscj
 *
 */
public class NetworkGame {
	private String host;
	private String status;
	private int id;
	private int currentPlaces;
	
	public void setCurrentPlaces(int currentPlaces) {
		this.currentPlaces = currentPlaces;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCurrentPlaces() {
		return currentPlaces;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getId() {
		return id;
	}
	
	public String getStatus() {
		return status;
	}
}
