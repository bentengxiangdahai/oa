package org.beifeng.oa.vo;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class TodoVO {

	private String column1;
	private String column2;
	private String businessvalue;
	private Task task;
	private String processinsId;
	private ProcessDefinition processdefine;
	private ProcessInstance processins;
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getBusinessvalue() {
		return businessvalue;
	}
	public void setBusinessvalue(String businessvalue) {
		this.businessvalue = businessvalue;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getProcessinsId() {
		return processinsId;
	}
	public void setProcessinsId(String processinsId) {
		this.processinsId = processinsId;
	}
	public ProcessDefinition getProcessdefine() {
		return processdefine;
	}
	public void setProcessdefine(ProcessDefinition processdefine) {
		this.processdefine = processdefine;
	}
	public ProcessInstance getProcessins() {
		return processins;
	}
	public void setProcessins(ProcessInstance processins) {
		this.processins = processins;
	}
}
