package org.beifeng.oa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

@Entity
@Table(name="t_leave")
public class Leave implements Serializable{

	@Id
	private String leaveId;
	private String leavetype;
	private String leavestarttime;
	private String leaveendtime;
	private String leaveday;
	private String leaveuserId;
	private String leaveusername;
	private String processinsId;
	private String reason;
	@Transient
	private ProcessInstance processins;
	@Transient
	private Task task;
	@Transient
	private ProcessDefinition processdefine;
	public String getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getLeavestarttime() {
		return leavestarttime;
	}
	public void setLeavestarttime(String leavestarttime) {
		this.leavestarttime = leavestarttime;
	}
	public String getLeaveendtime() {
		return leaveendtime;
	}
	public void setLeaveendtime(String leaveendtime) {
		this.leaveendtime = leaveendtime;
	}
	public String getLeaveday() {
		return leaveday;
	}
	public void setLeaveday(String leaveday) {
		this.leaveday = leaveday;
	}
	public String getLeaveuserId() {
		return leaveuserId;
	}
	public void setLeaveuserId(String leaveuserId) {
		this.leaveuserId = leaveuserId;
	}
	public String getLeaveusername() {
		return leaveusername;
	}
	public void setLeaveusername(String leaveusername) {
		this.leaveusername = leaveusername;
	}
	public String getProcessinsId() {
		return processinsId;
	}
	public void setProcessinsId(String processinsId) {
		this.processinsId = processinsId;
	}
	public ProcessInstance getProcessins() {
		return processins;
	}
	public void setProcessins(ProcessInstance processins) {
		this.processins = processins;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public ProcessDefinition getProcessdefine() {
		return processdefine;
	}
	public void setProcessdefine(ProcessDefinition processdefine) {
		this.processdefine = processdefine;
	}
}
