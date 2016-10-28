package com.jgodara.stackoverflow.market.model;

/**
 * @author Jay
 * 
 *         The stock model class.
 */
public class Stock {

	private float value;
	private float integrity;
	private float publicPresence;
	private float publicOpinion;

	public Stock(float value, float integrity, float publicPresence,
			float publicOpinion) {
		super();
		this.value = value;
		this.integrity = integrity;
		this.publicPresence = publicPresence;
		this.publicOpinion = publicOpinion;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getIntegrity() {
		return integrity;
	}

	public void setIntegrity(float integrity) {
		this.integrity = integrity;
	}

	public float getPublicPresence() {
		return publicPresence;
	}

	public void setPublicPresence(float publicPresence) {
		this.publicPresence = publicPresence;
	}

	public float getPublicOpinion() {
		return publicOpinion;
	}

	public void setPublicOpinion(float publicOpinion) {
		this.publicOpinion = publicOpinion;
	}

}
