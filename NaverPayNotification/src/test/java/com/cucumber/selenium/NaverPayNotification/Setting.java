package com.cucumber.selenium.NaverPayNotification;
import static org.junit.Assert.assertTrue;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Setting {
	private static WebDriver driver;    
	 private static StringBuffer verificationErrors = new StringBuffer();    
	 private static String IEDRIVER_FILE_PATH;
	 private static String CHROMEDRIVER_FILE_PATH;
	 private static JavascriptExecutor js;  // JavascriptExecutor 클래스 선언
	 WebElement order_num;
	 private static String number1;
	 private static String number2;
	 private static String number3;
	 private static APIClient client;
	 private static Map data;
	 private static JSONObject r;
	 private static String testId = "154939";
	 private static String currentWindowId;

	@Before("@tagToSetting")
	public static void setUp(){
		IEDRIVER_FILE_PATH = "D:/hanzisu/TestPayAutomaion/driver/IEDriverServer.exe";
		CHROMEDRIVER_FILE_PATH = "D:/hanzisu/TestPayAutomaion/driver/chromedriver.exe"; 
		 //System.setProperty("webdriver.ie.driver", IEDRIVER_FILE_PATH);
		 System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_FILE_PATH);
		 //driver = new InternetExplorerDriver();
		 driver = new ChromeDriver();
		  js = (JavascriptExecutor) driver;  // Web Driver를 JavascriptExecutor로 캐스팅
		  

		//DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		//caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		//driver = new InternetExplorerDriver(caps);
		//TestRail연동
			 client = new APIClient("http://test.navercorp.com/testrail/");
			 client.setUser("jisu.han@nhn.com");
			 client.setPassword("zjffjgks5");
			 
			 
		  
	}
	
	public void Login(){
		//네이버공통로그인
		driver.findElement(By.id("id")).sendKeys("nvqa_han");
		driver.findElement(By.id("pw")).sendKeys("qatest");
		driver.findElement(By.className("btn_login")).click();
		//스토어팜로그인
	}
	
	@Given("^베타 설정 페이지에 접근한다\\.$")
	public void 알파_설정_페이지에_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			driver.get("https://beta-pay.naver.com/mypage/update");
			Login();
				
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 베타 설정 페이지에 접근한다");
		r = (JSONObject) client.sendPost("add_result/" + testId, data);
		}
		
	}

	@When("^비밀번호 변경 버튼 클릭한다\\.$")
	public void 비밀번호_변경_버튼_클릭한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			currentWindowId= driver.getWindowHandle();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='content']/table[2]/tbody/tr[1]/td/a")).click();
				
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 비밀번호 변경 버튼 클릭한다");
		r = (JSONObject) client.sendPost("add_result/" + testId, data);
		}
		
	}

	@When("^네이버페이 비밀번호 입력창인지 확인한 후 비밀번호를 입력한다\\.$")
	public void 네이버페이_비밀번호_입력창인지_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//String currentWindowId= driver.getWindowHandle();
		data = new HashMap();
		try{
			int i=1;
			 Thread.sleep(2000);
				Set<String> allWindows = driver.getWindowHandles();
				if(!allWindows.isEmpty()){
					for (String windowId : allWindows){
						driver.switchTo().window(windowId);
						System.out.println(i+driver.getTitle());
						i++;
						if(driver.getTitle().equals("네이버페이 비밀번호")){
								System.out.println("비밀번호 입력창 찾음");
								try{
									Screen screen = new Screen();
									Pattern pw1 = new Pattern("D:/hanzisu/NaverPayNotification/image/pw_1.jpg");
									Pattern pw2 = new Pattern("D:/hanzisu/NaverPayNotification/image/pw_2.jpg");
									Pattern pw3 = new Pattern("D:/hanzisu/NaverPayNotification/image/pw_3.jpg");
									//기존비밀번호 입력/////////////////////
									//screen.wait(image, 10);
									screen.click(pw1);
									screen.click(pw2);
									screen.click(pw3);
									screen.click(pw1);
									screen.click(pw2);
									screen.click(pw3);
									/////////////////////////////////
									Thread.sleep(2000);
									try{
										Alert alert= driver.switchTo().alert();
									if(alert != null){
										alert.accept();
									        screen.click(pw1);
									        screen.click(pw1);
									        screen.click(pw1);
									        screen.click(pw2);
									        screen.click(pw2);
									        screen.click(pw2);
									        Thread.sleep(2000);
									        screen.click(pw1);
											screen.click(pw2);
											screen.click(pw3);
											screen.click(pw1);
											screen.click(pw2);
											screen.click(pw3);
											Thread.sleep(2000);
									        screen.click(pw1);
											screen.click(pw2);
											screen.click(pw3);
											screen.click(pw1);
											screen.click(pw2);
											screen.click(pw3);
									        
									    }
									}catch(NoAlertPresentException e){
										screen.click(pw1);
								        screen.click(pw1);
								        screen.click(pw1);
								        screen.click(pw2);
								        screen.click(pw2);
								        screen.click(pw2);
								        Thread.sleep(2000);
								        screen.click(pw1);
								        screen.click(pw1);
								        screen.click(pw1);
								        screen.click(pw2);
								        screen.click(pw2);
								        screen.click(pw2);
									    	//e.printStackTrace();
								        }
									    
									   																
									Thread.sleep(2000);
									 try {
									        driver.switchTo().alert().accept();
									    } // try
									    catch (NoSuchWindowException e) {
									      	//e.printStackTrace();
									    } // catch
																
									 break;
								}catch(NoSuchWindowException e){
									
									e.printStackTrace();
									}
								}else
									System.out.println("비밀번호입력창 아님");
									//throw new PendingException();
							
						}
						}driver.switchTo().window(currentWindowId);
				
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 네이버페이 비밀번호 입력창인지 확인한 후 비밀번호를 입력한다");
		r = (JSONObject) client.sendPost("add_result/" + testId, data);
		}
		
			
		
	}

	@Then("^'네이버페이 비밀번호 변경' me 알림을 확인한다\\.$")
	public void 네이버페이_비밀번호_변경_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			driver.get("http://stg.me.naver.com/box/noti.nhn");
			//driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
		String text="안전하게";
		assertTrue(driver.findElement(By.partialLinkText(text)).isDisplayed());
		System.out.println(driver.findElement(By.partialLinkText(text)).getText());
		data.put("status_id", new Integer(1));
		data.put("comment",driver.findElement(By.partialLinkText(text)).getText());
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		//driver.close();
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 네이버페이 비밀번호 변경' me 알림을 확인한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		
	}
	@After("@tagToSetting")
	public static void teardown(){
		driver.quit();
		
			
			 
			 
	}
}
