package com.myProject.fileManagerTool.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompressApi 
{
	
	
	List<String> list=new ArrayList<>();
	
	@CrossOrigin("http://localhost:4200/")
	@GetMapping("/compress/{previousPath}/{newFolderName}")
	public String getZipFolder(@PathVariable String previousPath,@PathVariable String newFolderName)
	{

		String realPath=previousPath.replace(',',  '\\');
		
		String myPath=realPath;
		
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
		
		
		String rootPath="";
		
		for(int i=0;i<newPath.length()-1;i++)
		{
			rootPath=rootPath+newPath.charAt(i);
		}
		
		System.out.println("Root path "+rootPath);
	
		File rootFolder = new File(rootPath);
		
		File[] fileList=rootFolder.listFiles();
		
		for(File file:fileList)
		{
			String txtExtFile= newFolderName.replace(".txt","");
			if(file.getName().equals(newFolderName+".zip") || file.getName().equals(txtExtFile+".zip"))
			{
				return " There is already a File with same name !   Please Enter New Name and Try Again";
				
			}
		}
		
		
		System.out.println(newFolderName);

		String firstCheck=newFolderName;
		
		if(firstCheck.equals("null") || firstCheck.equals("undefined"))
		{
			return "Please Enter New Name To Perform Compress";
		}
		
		
		
		
		
		
		boolean extension=myPath.contains(".");
		
		if(extension)
		{
			String myRootPath=myPath.substring(myPath.lastIndexOf("."));
			
			System.out.println("myRootPath"+myRootPath);
			
			String myNewRootPath="";
			
			for(int i=0;i<myPath.length()-(myRootPath.length());i++)
			{
				myNewRootPath=myNewRootPath+myPath.charAt(i);
			}
			
			System.out.println("myNewRootPath"+myNewRootPath);
			
			System.out.println("rootPath"+rootPath);

			System.out.println("oldFolderName"+oldFolderName);

			
			
			
			byte[] buffer=new byte[1024];
			
			try
			{
				
				
				FileOutputStream fos=new FileOutputStream(rootPath+"\\"+newFolderName+".zip");
				ZipOutputStream zos=new ZipOutputStream(fos);
				ZipEntry zipEntry=new ZipEntry(oldFolderName);
				zos.putNextEntry(zipEntry);
				FileInputStream fis=new FileInputStream(rootPath+"\\"+oldFolderName);
				int len;
				while((len=fis.read(buffer))>0)
				{
					zos.write(buffer, 0, len);
					
				}
				zos.closeEntry();
				zos.close();
				fis.close();
				
			}
			
			
			catch (Exception e) {
				return  "Error Occured! Try Again";
			}
			
			return "successfully compressed";
		}
		
		else
		{
			if(firstCheck.equals(oldFolderName))
			{
				return " Please Enter New Name and Try Again";
			}
			
			byte[] buffer=new byte[1024];
			
			try
			{
				
	
				FileOutputStream fos=new FileOutputStream(rootPath+"\\"+newFolderName+".zip");
				ZipOutputStream zos=new ZipOutputStream(fos);
				
				File rootFolder1 = new File(rootPath+"\\"+oldFolderName);
				
				 File[] fileList1 = rootFolder1.listFiles();
				 
				    for(File file:fileList1)
				    {
				    	
				    	
				    	list.add(file.getName());

				    }
				    
				    for(String myFiles:list)
				    {
				    	ZipEntry zipEntry=new ZipEntry(myFiles);
				    	zos.putNextEntry(zipEntry);
				    	FileInputStream fis=new FileInputStream(rootPath+"\\"+oldFolderName+ File.separator + myFiles);
				    	int len;
						while((len=fis.read(buffer))>0)
						{
							zos.write(buffer, 0, len);
						}
						fis.close();
				    }
				

				zos.closeEntry();
				zos.close();
				
				
			}
			
			
			catch (Exception e) {
				return "Error Occured! Try Again ";
			}
			
			return "successfully compressed";
		}
		
		
		
	}
}
