/*
	CVS ID $Id: VitalWareSerializeUpdateObject.java,v 1.2 2007/05/16 05:45:35 hcvs Exp $
*/
package com.televital.vitalware.domain;

import java.io.Serializable;
import java.util.Map;

public class VitalWareSerializeUpdateObject implements Serializable
{
	public static final long serialVersionUID = 123456;
	private Map<String,Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}