package com.myProject.fileManagerTool.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenameApi
{
	
	
	
	
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("/rename/{previousPath}/{newFolderName}")
	public String getRenameFolder(@PathVariable String previousPath,@PathVariable String newFolderName) 
	{
		System.out.println("Rename Called");
		//System.out.println(newFolderName);

		String firstCheck=newFolderName;
		
		System.out.println(newFolderName);
		
		
		if(firstCheck.equals("null"))
		{
			return "Please Enter New Name To Perform Rename";
		}
		
		
		
		String realPath=previousPath.replace(',',  '\\');
		System.out.println(realPath);
		
		String myOldFolderName="";
		
		myOldFolderName=realPath.substring(realPath.lastIndexOf("\\"));
		
		
		String oldFolderName=myOldFolderName.replace("\\", "");
		System.out.println(oldFolderName);
		
		String newPath="";
		
		for(int i=0;i<realPath.length()-(oldFolderName.length());i++)
		{
			newPath=newPath+realPath.charAt(i);
		}
		System.out.println(newPath);
		
		

		
		
		
		
			boolean flag1=false;
			
			
			
			
			File rootFolder = new File(newPath);
			
			File[] fileList=rootFolder.listFiles();
			
			for(File file:fileList)
			{
				if(file.getName().equals(newFolderName))
				{
					return "There is already a File with same name !   Please Enter New Name and Try Again";
				}
				
					if(file.getName().equals(oldFolderName))
					{
						
						
						File myNewFile=new File(newPath+"\\"+newFolderName);
						
					 flag1=file.renameTo(myNewFile);
					}

			}
				
			
			
			if(flag1)
			{
				return "successfully Renamed";
			}
			else
			{
				return "Error Occured! Try Again";
								
			}
			
			
			

	}

}
