package com.gmail.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.testng.Assert;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src=new File("./configuration/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			pro =new Properties();
			pro.load(fis);
		}
		catch (Exception e) {
				
				// TODO Auto-generated catch block
				Assert.fail("Login Page encountered a following issue :"+e.getMessage());
		}
	}
	
	public String getURL()
	{
		String url=pro.getProperty("URL");
		return url;
	}
	
	public String getEmailId()
	{
		String url=pro.getProperty("EmailId");
		return url;
	}
	
	public String getPassword()
	{
		String url=pro.getProperty("password");
		return url;
	}
	
	public String getChromepath()
	{
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	
	public String getFirefoxpath()
	{
		String firefoxpath=pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	public String getIEpath()
	{
		String iepath=pro.getProperty("iepath");
		return iepath;
	}
	
}

