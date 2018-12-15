package model;

import java.io.Serializable;

public class Timeslot implements Serializable{
	
	private int slotid;
	private String startat;
	private String endat;
	
	public Timeslot() {
		
	}

	public Timeslot(int slotid, String startat, String endat) {
		super();
		this.slotid = slotid;
		this.startat = startat;
		this.endat = endat;
	}

	/**
	 * @return the slotid
	 */
	public int getSlotid() {
		return slotid;
	}

	/**
	 * @param slotid the slotid to set
	 */
	public void setSlotid(int slotid) {
		this.slotid = slotid;
	}

	/**
	 * @return the startat
	 */
	public String getStartat() {
		return startat;
	}

	/**
	 * @param startat the startat to set
	 */
	public void setStartat(String startat) {
		this.startat = startat;
	}

	/**
	 * @return the endat
	 */
	public String getEndat() {
		return endat;
	}

	/**
	 * @param endat the endat to set
	 */
	public void setEndat(String endat) {
		this.endat = endat;
	}
	
	

}
