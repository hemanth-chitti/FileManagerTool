package com.myProject.fileManagerTool.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class OpenApi 
{
	
	
	
	List<Format> list=new ArrayList<>();
	
	
	
	
	
	@GetMapping("/open/{previousPath}")
	@CrossOrigin("http://localhost:4200/")
	public List<Format> getMyDire(@PathVariable String previousPath)
	{
		System.out.println("OpenApi Called");
		
        String realPath=previousPath.replace(',', '/');
        

       
		
		File rootFolder = new File(realPath);
		
		
	    File[] fileList = rootFolder.listFiles();
	    
	   
	    list.clear();

	    for(File file:fileList)
	    {
	    	
	    	
	    	FileSystemView fsv = FileSystemView.getFileSystemView();
	    	String driveType = fsv.getSystemTypeDescription(file);
	    	
	    	if(file.isDirectory())
	    	{
	    		Format root=new Format(file.getName(), driveType, file.getPath());
	    		list.add(root);
	    	}
	    	
		
	    }
	    
	    
	    for(File file:fileList)
	    {
	    	
	    	
	    	FileSystemView fsv = FileSystemView.getFileSystemView();
	    	String driveType = fsv.getSystemTypeDescription(file);
	    	
	    	if(file.isFile())
	    	{
	    		Format root=new Format(file.getName(), driveType, file.getPath());
	    		list.add(root);
	    	}
    		
	    }
	    
		
		return list;
	}

}
