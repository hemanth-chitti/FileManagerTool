package com.myProject.fileManagerTool.controller;



public class Format 
{
	private String name;
	private String type;
	private String path;
	
	public Format() {
		// TODO Auto-generated constructor stub
	}

	public Format(String name, String type, String path) {
		super();
		this.name = name;
		this.type = type;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "RootDirectory [name=" + name + ", type=" + type + ", path=" + path + "]";
	}
	
	
	
}
