package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="datadictionary")
public class DataDictionary implements Serializable{

	@Id
	private String dataId;
	private String datatype;
	private String datadictionary;
	private String parentId;
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDatadictionary() {
		return datadictionary;
	}
	public void setDatadictionary(String datadictionary) {
		this.datadictionary = datadictionary;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
