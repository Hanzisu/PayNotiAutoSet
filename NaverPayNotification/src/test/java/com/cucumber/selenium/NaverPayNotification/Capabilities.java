package com.cucumber.selenium.NaverPayNotification;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {
	
	public static DesiredCapabilities gridSetUp (String browser) throws MalformedURLException {		
		DesiredCapabilities capability = null;	
		
		//****************** firefox Capabilities
		 if(browser.equalsIgnoreCase("firefox")) {			
			 FirefoxProfile profile = new FirefoxProfile();
			 profile.setEnableNativeEvents(false);
			 capability = DesiredCapabilities.firefox();			 
			 capability.setCapability(FirefoxDriver.PROFILE, profile);
			 capability.setBrowserName("firefox");
			 capability.setPlatform(Platform.ANY);
			 capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			 }
		
		 //****************** IE6 Capabilities
		 if(browser.equalsIgnoreCase("internet explorer 6")) {
			 capability = DesiredCapabilities.internetExplorer();
			 capability.setBrowserName("internet explorer");
			 capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			 capability.setPlatform(Platform.ANY);
			 capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			 capability.setVersion("6");
			 }

		 //****************** IE7 Capabilities
		 if(browser.equalsIgnoreCase("internet explorer 7")) {
			 capability = DesiredCapabilities.internetExplorer();
			 capability.setBrowserName("internet explorer");
			 capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			 capability.setPlatform(Platform.ANY);			 
			 capability.setVersion("7");			
			 capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			 }
		
		 //****************** IE8 Capabilities
		if(browser.equalsIgnoreCase("internet explorer 8")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
			capability.setPlatform(Platform.ANY);			
			capability.setVersion("8");
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			}
		
		//****************** IE9 Capabilities
		if(browser.equalsIgnoreCase("internet explorer 9")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setPlatform(Platform.ANY);			
			capability.setVersion("9");
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			}
		
		if(browser.equalsIgnoreCase("internet explorer 10")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setPlatform(Platform.ANY);			
			capability.setVersion("10");
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			}
				
		//****************** Chrome Capabilities
		if(browser.equalsIgnoreCase("chrome")) {
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.ANY);
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			}
		
		//****************** iPhone Capabilities
		if(browser.equalsIgnoreCase("iPhone")) {
			capability = DesiredCapabilities.iphone();
			capability.setBrowserName("iPhone");
			capability.setPlatform(Platform.ANY);
			//capability.setVersion("5.1");
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			}
		
		//****************** Android Capabilities
		if(browser.equalsIgnoreCase("android")) {
			capability = DesiredCapabilities.android();
			capability.setBrowserName("android");
			capability.setPlatform(Platform.ANY);
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capability.setCapability(CapabilityType.ROTATABLE, true);
			}
		
		
		//****************** Android Capabilities
		if(browser.equalsIgnoreCase("Appium_android")) {
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("android");
			capability.setPlatform(Platform.ANY);
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			capability.setCapability(CapabilityType.ROTATABLE, true);
		    //capability.setCapability(CapabilityType.BROWSER_NAME, "browser");
			//capability.setCapability(CapabilityType.VERSION, "4.3");
			//capability.setCapability(CapabilityType.PLATFORM, "Android");
			//capability.setCapability("appPackage", "com.android.browser"); 
			///capability.setCapability("appActivity", "com.android.browser.BrowserActivity");
			}
		
		return capability;		
	}	
}
