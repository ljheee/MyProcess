package com.ljheee.os.model;
/**
 * 实体类    封装进程详细信息
 * @author ljheee
 *
 */
public class ProcessInfo {

	private String name;
	private String pid;
	private String sessionName;
	private String sessionNum;
	private String memory;
	
	public ProcessInfo() {
	}

	public ProcessInfo(String name, String pid, String sessionName, String sessionNum, String memory) {
		super();
		this.name = name;
		this.pid = pid;
		this.sessionName = sessionName;
		this.sessionNum = sessionNum;
		this.memory = memory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(String sessionNum) {
		this.sessionNum = sessionNum;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	@Override
	public String toString() {
		return "ProcessInfo [name=" + name + ", pid=" + pid + ", sessionName=" + sessionName + ", sessionNum="
				+ sessionNum + ", memory=" + memory + "]";
	}
	
	
	
	
	
	
	
}
