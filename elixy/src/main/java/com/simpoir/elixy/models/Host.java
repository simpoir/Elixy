package com.simpoir.elixy.models;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Host {

	@Basic(optional = false)
	private String name;

	public void setName(String name) {
		this.name = name;
	}

}
