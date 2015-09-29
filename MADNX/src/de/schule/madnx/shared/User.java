/**
 * 
 */
package de.schule.madnx.shared;

/**
 * @author xgadscj
 *
 */
public class User {
	private String name;
	private boolean host;
	private boolean status;
	
	public void setHost(boolean host) {
		this.host = host;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getHost() {
		return host;
	}
	
	public boolean getStatus() {
		return status;
	}
}
