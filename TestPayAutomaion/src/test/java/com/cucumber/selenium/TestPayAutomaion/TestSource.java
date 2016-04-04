package com.cucumber.selenium.TestPayAutomaion;




import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;






import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestSource {
	
	private static WebDriver driver;    
	 private static StringBuffer verificationErrors = new StringBuffer();    
	 private static String IEDRIVER_FILE_PATH;
	 private static String CHROMEDRIVER_FILE_PATH;
	 private static JavascriptExecutor js;  // JavascriptExecutor 클래스 선언
	 WebElement order_num;
	 private static String number1;
	 private static String number2;
	 private static String number3;
	 private static String temp_num;
	 private static APIClient client;
	 private static Map data;
	 private static JSONObject r;
	 private static String testId = "154881";
	 private static String currentWindowId;
	 private static int delivery_num=456789123;

	@Before
	public void setUp() throws Exception{
		IEDRIVER_FILE_PATH = "D:/hanzisu/TestPayAutomaion/driver/IEDriverServer.exe";
		CHROMEDRIVER_FILE_PATH = "D:/hanzisu/TestPayAutomaion/driver/chromedriver.exe"; 
		 //System.setProperty("webdriver.ie.driver", IEDRIVER_FILE_PATH);
		 System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_FILE_PATH);
		 //driver = new InternetExplorerDriver();
		 driver = new ChromeDriver();
		  js = (JavascriptExecutor) driver;  // Web Driver를 JavascriptExecutor로 캐스팅
		//TestRail연동
			 client = new APIClient("http://test.navercorp.com/testrail/");
			 client.setUser("jisu.han@nhn.com");
			 client.setPassword("zjffjgks5");
		  
		  //JSONObject c = (JSONObject) client.sendGet("get_case/1");
		  //System.out.println(c.get("title"));
		//DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		//caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		//driver = new InternetExplorerDriver(caps);
		
	}
	public void Login(){
		//네이버공통로그인
		driver.findElement(By.id("id")).sendKeys("nvqa_han");
		driver.findElement(By.id("pw")).sendKeys("qatest");
		driver.findElement(By.className("btn_login")).click();
		//스토어팜로그인1
		
	}

	@Given("^알파 스토어팜 판매자센터 접근한다\\.$")
	public void 알파_스토어팜_판매자센터_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		
		driver.get("http://alpha.sell.storefarm.naver.com/s/");
		//driver.findElement(By.className("gnb_btn_login")).click();

		driver.findElement(By.name("username")).sendKeys("qa1tc040");
		driver.findElement(By.name("password")).sendKeys("qa1tc040");
		driver.findElement(By.className("submit")).click();
		
	    
	}
/*
	@When("^상품주문번호 입력하여 검색 후 해당 내역 선택한다\\.$")
	public void 상품주문번호_입력하여_검색_후_해당_내역_선택한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		number2="2016030411509261";
		//판매관리 접근
		driver.findElement(By.className("lnb_m3")).click();
		//상품주문번호로 상세조회 검색
		driver.findElement(By.name("detailSearch.type")).click();
		driver.findElement(By.xpath("//*[@id='detailSearch.type']/option[5]")).click();
		driver.findElement(By.name("detailSearch.keyword")).sendKeys(number2);
		driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[2]/p/a")).click();
		//해당 내역 선택
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		//*[@id="gridbox"]/div[2]/div[2]/table/tbody/tr[2]/td[5]
	}

	@When("^발송지연 안내 버튼 클릭한다\\.$")
	public void 발송지연_안내_버튼_클릭한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//Set<String> allWindows = driver.getWindowHandles();
		driver.findElement(By.xpath("//*[@id='root']/div[9]/table/tbody/tr[1]/td/span/a[2]")).click();
		String currentWindowId= driver.getWindowHandle();
		Thread.sleep(3000);
		 try {
		     Alert alert =driver.switchTo().alert();
		     alert.accept();
		    } // try
		    catch (NoAlertPresentException e) {
		    	e.printStackTrace();
		    } // catch
		 Thread.sleep(1000);
		Set<String> allWindows = driver.getWindowHandles();
		if(!allWindows.isEmpty()){
			for (String windowId : allWindows){
				driver.switchTo().window(windowId);
				
					if(driver.getPageSource().contains("발송지연 안내하기")){
						System.out.println("발송지연 안내하기 팝업창 찾음");
						try{
							driver.findElement(By.name("delayDispatchDetailReason")).sendKeys("상세사유");
							driver.findElement(By.xpath("//*[@id='pop_footer']/div/a[1]")).click();
							Thread.sleep(3000);
							 try {
							        driver.switchTo().alert().accept();
							    } // try
							    catch (NoSuchWindowException e) {
							    	System.out.println("얼럿 못찾음");
							    	e.printStackTrace();
							    } // catch
							 Thread.sleep(2000);
							 try {
							        driver.switchTo().alert().accept();
							    } // try
							    catch (NoSuchWindowException e) {
							    	System.out.println("얼럿 못찾음");
							    	e.printStackTrace();
							    } // catch
							 break;
						}catch(NoSuchWindowException e){
							System.out.println("상세사유입력란 못찾음");
							e.printStackTrace();
							}
						}
					
				}
				}
		driver.switchTo().window(currentWindowId);
			
		}


	@Then("^'배송지연' me 알림을 확인한다\\.$")
	public void 배송지연_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}*/
	
	/*@Given("^주문내역에서 문의하기 버튼 클릭하여 판매자 문의하기 팝업 접근한다\\.$")
	public void 주문내역에서_문의하기_버튼_클릭하여_판매자_문의하기_팝업_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//주문상세로 이동
		driver.get("https://alpha-order.pay.naver.com/orderStatus/2016030411150241");
		//로그인
		driver.findElement(By.id("id")).sendKeys("nvqa_han");
		driver.findElement(By.id("pw")).sendKeys("qatest");
		driver.findElement(By.className("btn_login")).click();
		//판매자문의 버튼 클릭
		driver.findElement(By.xpath("//*[@id='content']/div/table/tbody/tr/td[4]/div/div[5]")).click();
		
		
		
	}

	@Given("^판매자 문의내용 입력 후 네이버me로 알림 체크하여 제출한다\\.$")
	public void 판매자_문의내용_입력_후_네이버me로_알림_체크하여_제출한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//팝업 제어
		String currentWindowId= driver.getWindowHandle();
		 Thread.sleep(1000);
			Set<String> allWindows = driver.getWindowHandles();
			if(!allWindows.isEmpty()){
				for (String windowId : allWindows){
					driver.switchTo().window(windowId);
					
						if(driver.getPageSource().contains("판매자 문의하기")){
							System.out.println("판매자 문의하기 팝업창 찾음");
							try{
								//문의유형선택
								driver.findElement(By.name("inquiryCategory")).click();
								driver.findElement(By.xpath("//*[@id='inquiryCategory']/option[3]")).click();
								//제목입력
								driver.findElement(By.id("title")).sendKeys("판매자문의 제목");
								//내용입력
								driver.findElement(By.id("inquiryContent")).sendKeys("판매자문의 내용");
								//네이버me로 알림받기 체크 설정
								driver.findElement(By.xpath("//input[@value='NAVERME']")).click();
								//확인
								driver.findElement(By.id("confirm_btn")).click();
								
								driver.findElement(By.id("closeBtn")).click();
								
																
								 break;
							}catch(NoSuchWindowException e){
								e.printStackTrace();
								}
							}
						
					}
					}
			driver.switchTo().window(currentWindowId);
	}

	@When("^스토어팜 판매자센터의 고객문의 탭에 접근한다\\.$")
	public void 스토어팜_판매자센터의_고객문의_탭에_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://alpha.sell.storefarm.naver.com/s/home");
		//로그인
		driver.findElement(By.name("username")).sendKeys("qa1tc040");
		driver.findElement(By.name("password")).sendKeys("qa1tc040");
		driver.findElement(By.xpath("//*[@id='content']/div/div/form/fieldset/a")).click();
		//판매관리 > 고객응대관리 > 고객문의
		driver.findElement(By.className("lnb_m3")).click();
		driver.findElement(By.xpath("//*[@id='lnb']/li[3]/ul/li[10]/a")).click();
		driver.findElement(By.xpath("//*[@id='content']/div[3]/ul/li[2]/a")).click();
		
	}

	@When("^해당 문의내역 선택한 후 답변한다\\.$")
	public void 해당_문의내역_선택한_후_답변한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//driver.findElement(By.name("searchType")).click();
		//driver.findElement(By.xpath("//*[@id='sel_choice3']/option[5]")).click();
		//문의 제목으로 내역 선택
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("판매자문의 제목")).click();
		System.out.println(driver.findElement(By.partialLinkText("판매자문의 제목")));
		//답변 내용 입력 후 제출
		driver.findElement(By.id("commentContent")).sendKeys("판매자문의 답변");
		driver.findElement(By.id("regComment")).click();
		//답변처리가 완료되었습니다. 얼럿 확인
		Thread.sleep(2000);
		 try {
		        driver.switchTo().alert().accept();
		    } // try
		    catch (NoSuchWindowException e) {
		      	e.printStackTrace();
		    } // catch
		 driver.close();
	}

	@Then("^'고객문의 답변' me 알림을 확인한다\\.$")
	public void 고객문의_답변_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   
	}*/

	/*@Given("^베타 스토어팜 상품 상세페이지 접근한다\\.$")
	public void 베타_스토어팜_상품_상세페이지_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://beta.storefarm.naver.com/ns2qa01/products/100207844");
		
		if (driver.findElement(By.className("gnb_btn_login")).isDisplayed()) {
			driver.findElement(By.className("gnb_btn_login")).click();

			driver.findElement(By.id("id")).sendKeys("nvqa_han");
			driver.findElement(By.id("pw")).sendKeys("qatest");
			driver.findElement(By.className("btn_login")).click();
		}
		//Q&A 클릭 > 문의하기 클릭
		driver.findElement(By.xpath("//*[@id='dts']/div/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id='content']/div/div[4]/div[4]/div/p[1]/a[2]")).click();
		
	}

	@Given("^상품 Q&A를 작성한다\\.$")
	public void 상품_Q_A를_작성한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//팝업 제어
				String currentWindowId= driver.getWindowHandle();
				 Thread.sleep(3000);
					Set<String> allWindows = driver.getWindowHandles();
					if(!allWindows.isEmpty()){
						for (String windowId : allWindows){
							driver.switchTo().window(windowId);
							System.out.println("1");
								if(driver.getPageSource().contains("답변수신메일")){
									System.out.println("상품 Q&A 작성하기 팝업창 찾음");
									try{
										//문의유형선택
										driver.findElement(By.name("type")).click();
										driver.findElement(By.xpath("//*[@id='qna_type']/option[2]")).click();
										//제목입력
										driver.findElement(By.id("qna_subject")).sendKeys("상품 qna 제목");
										//내용입력
										driver.findElement(By.id("qna_cont")).sendKeys("상품 qna 내용");
										//확인
										driver.findElement(By.xpath("//*[@id='pop_footer']/a[1]")).click();
										//상품Q&A가 등록되었습니다. 얼럿 확인
										Thread.sleep(2000);
										 try {
										        driver.switchTo().alert().accept();
										    } // try
										    catch (NoSuchWindowException e) {
										      	e.printStackTrace();
										    } // catch
																		
										 break;
									}catch(NoSuchWindowException e){
										e.printStackTrace();
										}
									}
								
							}
							}else
								System.out.println("상품qna 작성하기 팝업 못찾음");
					driver.switchTo().window(currentWindowId);
	}

	@When("^스토어팜 판매자센터의 상품Q&A 탭에 접근한다\\.$")
	public void 스토어팜_판매자센터의_상품Q_A_탭에_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://beta.sell.storefarm.naver.com/s/");
		
		//로그인
				driver.findElement(By.name("username")).sendKeys("ns2qa01");
				driver.findElement(By.name("password")).sendKeys("test00))");
				driver.findElement(By.className("submit")).click();
				//판매관리 > 고객응대관리
				driver.findElement(By.className("lnb_m3")).click();
				driver.findElement(By.xpath("//*[@id='lnb']/li[3]/ul/li[10]/a")).click();
					
	}
	@When("^해당 Q&A 문의내역 선택한 후 답변한다\\.$")
	public void 해당_Q_A_문의내역_선택한_후_답변한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//driver.findElement(By.name("searchType")).click();
				//driver.findElement(By.xpath("//*[@id='sel_choice3']/option[5]")).click();
				//문의 제목으로 내역 선택
				Thread.sleep(3000);
				driver.findElement(By.partialLinkText("상품 qna 제목")).click();
				System.out.println(driver.findElement(By.partialLinkText("상품 qna 제목")));
				//답변 내용 입력 후 제출
				driver.findElement(By.name("answer")).sendKeys("상품문의 답변");
				driver.findElement(By.className("_answer_button")).click();
				//답변처리가 완료되었습니다. 얼럿 확인
				Thread.sleep(2000);
				 try {
				        driver.switchTo().alert().accept();
				    } // try
				    catch (NoSuchWindowException e) {
				      	e.printStackTrace();
				    } // catch
				 driver.close();
	}

	@Then("^'상품Q&A 답변' me 알림을 확인한다\\.$")
	public void 상품q_A_답변_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}*/
	
	/*@Given("^베타 설정 페이지에 접근한다\\.$")
	public void 알파_설정_페이지에_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://beta-pay.naver.com/mypage/update");
		Login();
	}

	@When("^비밀번호 변경 버튼 클릭한다\\.$")
	public void 비밀번호_변경_버튼_클릭한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='set3']/td/a")).click();
	}

	@When("^네이버페이 비밀번호 입력창인지 확인한 후 비밀번호를 입력한다\\.$")
	public void 네이버페이_비밀번호_입력창인지_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//String currentWindowId= driver.getWindowHandle();
		Map data = new HashMap();

		try {
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
									    	e.printStackTrace();}
									    
									   																
									Thread.sleep(2000);
									 try {
									        driver.switchTo().alert().accept();
									    } // try
									    catch (NoSuchWindowException e) {
									      	e.printStackTrace();
									    } // catch
																
									 break;
								}catch(NoSuchWindowException e){
									
									e.printStackTrace();
									}
								}else
									System.out.println("비밀번호입력창 아님");
									//throw new PendingException();
							
						}
						}
				//TestRail연동
				 //client = new APIClient("http://test.navercorp.com/testrail/");
				 // client.setUser("jisu.han@nhn.com");
				 // client.setPassword("zjffjgks5");
				 // JSONObject c = (JSONObject) client.sendGet("T154413");
				 // System.out.println(c.get("title"));
				//data.put("status_id", new Integer(1));
				//data.put("comment", "This test worked fine!");
				//JSONObject r = (JSONObject) client.sendPost("T154413", data);
	       
	    } 
	    catch (Exception e) {
	      	e.printStackTrace();
	    }
		
			//driver.switchTo().window(currentWindowId);
		
	}

	@Then("^'네이버페이 비밀번호 변경' me 알림을 확인한다\\.$")
	public void 네이버페이_비밀번호_변경_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
*/
	/*@Given("^베타 설정 페이지에 접근한다\\.$")
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
			driver.findElement(By.xpath("//*[@id='set3']/td/a")).click();
				
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
			driver.get("http://dev.me.naver.com/box/noti.nhn");
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
		
	}*/
	
	/*@Given("^알파 주문서에 접근한다\\.$")
	public void 알파_주문서에_접근한다() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		driver.get("http://alpha.storefarm.naver.com/qa1tc040/products/365383");
		try{			
			if (driver.findElement(By.className("gnb_btn_login")).isDisplayed()) {
				driver.findElement(By.className("gnb_btn_login")).click();
				Login();
			}
			driver.findElement(By.className("buy")).click();
			
			//주문서 접근 후 결제정보 영역 까지 스크롤
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			//driver.findElement(By.id("cardCodeList")).click();
			if(driver.findElement(By.className("order_payment")).isDisplayed()){
					js.executeScript("scroll(0, 1000)");}
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 알파 주문서에 접근한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
	}
	
	@When("^결제정보에 일반결제 > 실시간계좌이체를 선택한 후 결제한다\\.$")
	public void 결제정보에_일반결제_실시간계좌이체를_선택한_후_결제한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			Screen screen = new Screen();
						
			WebElement payment_item=driver.findElement(By.xpath("//input[@value='BANK']"));
								
					if(!payment_item.isSelected()){
						driver.findElement(By.xpath("//*[@id='orderForm']/div/div[5]/div[1]/div[1]/div[4]/div/ul/li[2]/label")).click();}		   
					//결제하기 버튼 클릭
					driver.findElement(By.xpath("//*[@id='orderForm']/div/div[7]/button/span")).click();
					assertTrue(driver.findElement(By.className("orderend_info")).isDisplayed());
					order_num = driver.findElement(By.xpath("//*[@id='order']/div[2]/div[2]/div[1]/a"));
					temp_num=order_num.getText();
					System.out.println(temp_num);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제정보에 일반결제 > 실시간계좌이체를 선택한 후 결제한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
	}

	@When("^발주/발송관리 메뉴에서 '택배'로 '발송처리'한다\\.$")
	public void 발주_발송관리_메뉴에서_택배_로_발송처리_한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			String currentWindowId= driver.getWindowHandle();
			//판매관리 접근
			driver.findElement(By.className("lnb_m3")).click();
			//주문번호로 상세조회 검색
			driver.findElement(By.name("detailSearch.type")).click();
			driver.findElement(By.xpath("//*[@id='detailSearch.type']/option[4]")).click();
			driver.findElement(By.name("detailSearch.keyword")).sendKeys(temp_num);
			driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[2]/p/a")).click();
			//해당 내역 선택
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			//*[@id="gridbox"]/div[2]/div[2]/table/tbody/tr[2]/td[5]
			//택배로 발송처리
			driver.findElement(By.name("deliveryMethodType")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/select[1]/option[2]")).click();
			driver.findElement(By.name("serviceCompanyCode")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/select[2]/option[34]")).click();
			String invoicingNo=String.valueOf(delivery_num);
			driver.findElement(By.name("invoicingNo")).sendKeys(invoicingNo);
			delivery_num++;
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/a")).click();
			//발송처리 버튼 클릭
			driver.findElement(By.xpath("//*[@id='root']/div[9]/table/tbody/tr[2]/td/span/a[1]")).click();
			//얼럿 확인
			Set<String> allWindows1 = driver.getWindowHandles();
			System.out.println(allWindows1.size());
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			//팝업+얼럿 확인
			
			Thread.sleep(3000);
			Set<String> allWindows = driver.getWindowHandles();
			//String url = "https://alpha.sell.storefarm.naver.com/o/sale/delivery/dispatch2";
			//Thread.sleep(2000);
			//driver.switchTo().alert().accept();
			//Thread.sleep(2000);
			if(allWindows.size()==2){
				System.out.println("두번째 발송지연얼럿 닫기위해 드라이버 종료시킴");
				//driver.switchTo().window(currentWindowId);
				//driver.get("http://dev.me.naver.com/box/noti.nhn");
				//Thread.sleep(2000);
				//driver.switchTo().alert().accept();
				driver.quit();
			}
			data.put("status_id", new Integer(2));
			data.put("comment", "success => step : 택배 발송 처리 -> 배송 시작 안내 알림 확인필요");
			JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
			//System.out.println(allWindows.);
			if(!allWindows.isEmpty()){
				for (String windowId : allWindows){
					//Thread.sleep(2000);
					//driver.switchTo().window(windowId);
					System.out.println(driver.getCurrentUrl());
					System.out.println(driver.getTitle());
					
					//if(driver.switchTo().alert() != null){
					//	System.out.println("발송처리 팝업 찾음");}
					if(i==1){
						System.out.println("발송지연 안내하기 팝업창 찾음");
						try{
							
							Thread.sleep(2000);
							 try {
							        driver.switchTo().alert().accept();
							    } // try
							    catch (NoSuchWindowException e) {
							    	System.out.println("얼럿 못찾음");
							    	e.printStackTrace();
							    } // catch
							 break;
						}catch(NoSuchWindowException e){
							System.out.println("상세사유입력란 못찾음");
							e.printStackTrace();
							}
						}
					i++;	
					}
					}
			//driver.switchTo().window(currentWindowId);
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 상품주문번호 입력하여 검색 후 해당 내역 선택한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		System.out.println("catch로 넘어가나?");		
		}
	}

	@Then("^'배송안내' me 알림을 확인한다\\.$")
	public void 배송안내_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			driver = new ChromeDriver();
			driver.get("http://dev.me.naver.com/box/noti.nhn");
			driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div[1]/div/a/span")).click();
			Login();
			driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
			//driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
		Thread.sleep(1000);
			String text="실시간계좌이체로 결제하셨습니다.";
		assertTrue(driver.findElement(By.partialLinkText(text)).isDisplayed());
		System.out.println(driver.findElement(By.partialLinkText(text)).getText());
		
		data.put("status_id", new Integer(1));
		data.put("comment", driver.findElement(By.partialLinkText(text)).getText());
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제완료 - 실시간계좌이체 실패");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
		testId = "154887";
	}

	@When("^결제정보에 일반결제 > 휴대폰 일반결제를 선택한 후 결제한다\\.$")
	public void 결제정보에_일반결제_휴대폰_일반결제를_선택한_후_결제한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			Screen screen = new Screen();
						
			WebElement payment_item=driver.findElement(By.xpath("//input[@value='MOBILE']"));
								
					if(!payment_item.isSelected()){
						driver.findElement(By.xpath("//*[@id='orderForm']/div/div[5]/div[1]/div[1]/div[4]/div/ul/li[4]/label")).click();}		   
					//결제하기 버튼 클릭
					driver.findElement(By.xpath("//*[@id='orderForm']/div/div[7]/button/span")).click();
					//휴대폰결제 팝업
					//팝업 제어
					String currentWindowId= driver.getWindowHandle();
					 Thread.sleep(3000);
						Set<String> allWindows = driver.getWindowHandles();
						if(!allWindows.isEmpty()){
							for (String windowId : allWindows){
								driver.switchTo().window(windowId);
									if(driver.getPageSource().contains("안내사항")){
										System.out.println("휴대폰결제 팝업창 찾음");
										try{
											//휴대폰 결제(일반)
											driver.findElement(By.xpath("//*[@id='pop_content']/div[1]/ul/li[2]/a")).click();
											//휴대폰번호 입력
											driver.findElement(By.name("telNo_2")).sendKeys("0000");
											driver.findElement(By.name("telNo_3")).sendKeys("0000");	
											driver.findElement(By.xpath("//*[@id='pop_footer']/a[1]")).click();																			
											 break;
										}catch(NoSuchWindowException e){
											e.printStackTrace();
											}
										}
									
								}
								}else
									System.out.println("휴대폰 결제 팝업 못찾음");
						driver.switchTo().window(currentWindowId);
					assertTrue(driver.findElement(By.className("orderend_info")).isDisplayed());
					order_num = driver.findElement(By.xpath("//*[@id='order']/div[2]/div[2]/div[1]/a"));
					temp_num=order_num.getText();
					System.out.println(temp_num);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제정보에 일반결제 > 휴대폰 일반결제를 선택한 후 결제한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
	}

	@When("^발주/발송관리 메뉴에서 '방문수령'으로 '발송처리'한다\\.$")
	public void 발주_발송관리_메뉴에서_방문수령_으로_발송처리_한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			String currentWindowId= driver.getWindowHandle();
			//판매관리 접근
			driver.findElement(By.className("lnb_m3")).click();
			//주문번호로 상세조회 검색
			driver.findElement(By.name("detailSearch.type")).click();
			driver.findElement(By.xpath("//*[@id='detailSearch.type']/option[4]")).click();
			driver.findElement(By.name("detailSearch.keyword")).sendKeys(temp_num);
			driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[2]/p/a")).click();
			//해당 내역 선택
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			//*[@id="gridbox"]/div[2]/div[2]/table/tbody/tr[2]/td[5]
			//방문수령 선택건적용
			driver.findElement(By.name("deliveryMethodType")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/select[1]/option[4]")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/a")).click();
			//발송처리 버튼 클릭
			driver.findElement(By.xpath("//*[@id='root']/div[9]/table/tbody/tr[2]/td/span/a[1]")).click();
			//얼럿 확인
			Set<String> allWindows1 = driver.getWindowHandles();
			System.out.println(allWindows1.size());
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			//팝업+얼럿 확인
			
			Thread.sleep(3000);
			Set<String> allWindows = driver.getWindowHandles();
		//	String url = "https://alpha.sell.storefarm.naver.com/o/sale/delivery/dispatch2";
			//Thread.sleep(2000);
			//driver.switchTo().alert().accept();
			//Thread.sleep(2000);
			if(allWindows.size()==2){
				System.out.println("두번째 발송지연얼럿 닫기위해 드라이버 종료시킴");
				driver.quit();
			}
			data.put("status_id", new Integer(2));
			data.put("comment", "success => step : 방문수령 처리 -> 기타발송처리안내 알림 확인필요");
			JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 상품주문번호 입력하여 검색 후 해당 내역 선택한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		System.out.println("catch로 넘어가나?");		
		}
	}

	@Then("^'기타발송처리 안내 - 방문수령' me 알림을 확인한다\\.$")
	public void 기타발송처리_안내_방문수령_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
					
		
			driver = new ChromeDriver();
			driver.get("http://dev.me.naver.com/box/noti.nhn");
			driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div[1]/div/a/span")).click();
			Login();
			driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
			//driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
		String text="휴대폰으로 결제하셨습니다.";
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.partialLinkText(text)).isDisplayed());
		System.out.println(driver.findElement(By.partialLinkText(text)).getText());
		
		data.put("status_id", new Integer(1));
		data.put("comment", driver.findElement(By.partialLinkText(text)).getText());
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제완료 - 휴대폰 일반결제 실패");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
		testId = "154890";
	}

	@When("^결제정보에 일반결제 > 휴대폰 간편결제를 선택한 후 결제한다\\.$")
	public void 결제정보에_일반결제_휴대폰_간편결제를_선택한_후_결제한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			Screen screen = new Screen();
						
			WebElement payment_item=driver.findElement(By.xpath("//input[@value='MOBILE']"));
								
					if(!payment_item.isSelected()){
						driver.findElement(By.xpath("//*[@id='orderForm']/div/div[5]/div[1]/div[1]/div[4]/div/ul/li[4]/label")).click();}		   
					//결제하기 버튼 클릭
					driver.findElement(By.xpath("//*[@id='orderForm']/div/div[7]/button/span")).click();
					//휴대폰결제 팝업
					//팝업 제어
					String currentWindowId= driver.getWindowHandle();
					 Thread.sleep(3000);
						Set<String> allWindows = driver.getWindowHandles();
						if(!allWindows.isEmpty()){
							for (String windowId : allWindows){
								driver.switchTo().window(windowId);
									if(driver.getPageSource().contains("안내사항")){
										System.out.println("휴대폰결제 팝업창 찾음");
										try{
											//휴대폰 간편결제 탭 선택
											//휴대폰 결제(일반)
											driver.findElement(By.xpath("//*[@id='pop_content']/div[1]/ul/li[1]/a")).click();
											//휴대폰간편결제 비밀번호 입력
											driver.findElement(By.name("currentPassword")).sendKeys("0624");
											//휴대폰결제 팝업 - 결제 클릭
											driver.findElement(By.xpath("//*[@id='pop_footer']/a[1]")).click();
											 break;
										}catch(NoSuchWindowException e){
											e.printStackTrace();
											}
										}
									
								}
								}else
									System.out.println("휴대폰결제 팝업 못찾음");
						driver.switchTo().window(currentWindowId);
					assertTrue(driver.findElement(By.className("orderend_info")).isDisplayed());
					order_num = driver.findElement(By.xpath("//*[@id='order']/div[2]/div[2]/div[1]/a"));
					temp_num=order_num.getText();
					System.out.println(temp_num);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제정보에 일반결제 > 휴대폰 간편결제를 선택한 후 결제한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
	}

	@When("^발주/발송관리 메뉴에서 '퀵서비스'로 '발송처리'한다\\.$")
	public void 발주_발송관리_메뉴에서_퀵서비스_로_발송처리_한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			String currentWindowId= driver.getWindowHandle();
			//판매관리 접근
			driver.findElement(By.className("lnb_m3")).click();
			//주문번호로 상세조회 검색
			driver.findElement(By.name("detailSearch.type")).click();
			driver.findElement(By.xpath("//*[@id='detailSearch.type']/option[4]")).click();
			driver.findElement(By.name("detailSearch.keyword")).sendKeys(temp_num);
			driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div[2]/p/a")).click();
			//해당 내역 선택
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			//*[@id="gridbox"]/div[2]/div[2]/table/tbody/tr[2]/td[5]
			//퀵서비스 선택건적용
			driver.findElement(By.name("deliveryMethodType")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/select[1]/option[3]")).click();
			driver.findElement(By.xpath("//*[@id='registerDeliveryInfoForm']/a")).click();
			//발송처리 버튼 클릭
			driver.findElement(By.xpath("//*[@id='root']/div[9]/table/tbody/tr[2]/td/span/a[1]")).click();
			//얼럿 확인
			Set<String> allWindows1 = driver.getWindowHandles();
			System.out.println(allWindows1.size());
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			//팝업+얼럿 확인
			
			Thread.sleep(3000);
			Set<String> allWindows = driver.getWindowHandles();
			//String url = "https://alpha.sell.storefarm.naver.com/o/sale/delivery/dispatch2";
			//Thread.sleep(2000);
			//driver.switchTo().alert().accept();
			//Thread.sleep(2000);
			if(allWindows.size()==2){
				System.out.println("두번째 발송지연얼럿 닫기위해 드라이버 종료시킴");
				driver.quit();
			}
			data.put("status_id", new Integer(2));
			data.put("comment", "success => step : 퀵서비스 처리 -> 기타배송안내 알림 확인 필요");
			JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 발주/발송관리 메뉴에서 '퀵서비스'로 '발송처리'한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		System.out.println("catch로 넘어가나?");		
		}
	}

	@Then("^'기타배송안내 - 직배송,퀵서비스' me 알림을 확인한다\\.$")
	public void 기타배송안내_직배송_퀵서비스_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
					
			driver = new ChromeDriver();
			driver.get("http://dev.me.naver.com/box/noti.nhn");
			driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div[1]/div/a/span")).click();
			Login();
			driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
			//driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
		String text="휴대폰 간편결제로";
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.partialLinkText(text)).isDisplayed());
		System.out.println(driver.findElement(By.partialLinkText(text)).getText());
		
		data.put("status_id", new Integer(1));
		data.put("comment", driver.findElement(By.partialLinkText(text)).getText());
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 결제완료 - 휴대폰 간편결제 알림 확인 실패");
		JSONObject r = (JSONObject) client.sendPost("add_result/154891", data);
					
		}
		testId = "154892";
	    
	}*/
	@Given("^주문내역에서 문의하기 버튼 클릭하여 판매자 문의하기 팝업 접근한다\\.$")
	public void 주문내역에서_문의하기_버튼_클릭하여_판매자_문의하기_팝업_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			//주문상세로 이동
			driver.get("https://alpha-order.pay.naver.com/orderStatus/2016030411150241");
			//로그인
			Login();
			//판매자문의 버튼 클릭
			driver.findElement(By.xpath("//*[@id='content']/div/table/tbody/tr/td[4]/div/div[5]")).click();
					
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 주문내역에서 문의하기 버튼 클릭하여 판매자 문의하기 팝업 접근한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		
		
		
	}

	@Given("^판매자 문의내용 입력 후 네이버me로 알림 체크하여 제출한다\\.$")
	public void 판매자_문의내용_입력_후_네이버me로_알림_체크하여_제출한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			//팝업 제어
			String currentWindowId= driver.getWindowHandle();
			 Thread.sleep(1000);
				Set<String> allWindows = driver.getWindowHandles();
				if(!allWindows.isEmpty()){
					for (String windowId : allWindows){
						driver.switchTo().window(windowId);
						
							if(driver.getPageSource().contains("판매자 문의하기")){
								System.out.println("판매자 문의하기 팝업창 찾음");
								try{
									//문의유형선택
									driver.findElement(By.name("inquiryCategory")).click();
									driver.findElement(By.xpath("//*[@id='inquiryCategory']/option[3]")).click();
									//제목입력
									driver.findElement(By.id("title")).sendKeys("판매자문의 제목");
									//내용입력
									driver.findElement(By.id("inquiryContent")).sendKeys("판매자문의 내용");
									//네이버me로 알림받기 체크 설정
									driver.findElement(By.xpath("//input[@value='NAVERME']")).click();
									//확인
									driver.findElement(By.id("confirm_btn")).click();
									
									driver.findElement(By.id("closeBtn")).click();
									
																	
									 break;
								}catch(NoSuchWindowException e){
									e.printStackTrace();
									}
								}
							
						}
						}
				driver.switchTo().window(currentWindowId);
					
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 판매자 문의내용 입력 후 네이버me로 알림 체크하여 제출한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		
	}

	@When("^스토어팜 판매자센터의 고객문의 탭에 접근한다\\.$")
	public void 스토어팜_판매자센터의_고객문의_탭에_접근한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://alpha.sell.storefarm.naver.com/s/");
		data = new HashMap();
		try{
		
			
			//로그인
			driver.findElement(By.name("username")).sendKeys("qa1tc040");
			driver.findElement(By.name("password")).sendKeys("qa1tc040");
			driver.findElement(By.className("submit")).click();
			//판매관리 > 고객응대관리 > 고객문의
			driver.findElement(By.className("lnb_m3")).click();
			driver.findElement(By.xpath("//*[@id='lnb']/li[3]/ul/li[10]/a")).click();
			driver.findElement(By.xpath("//*[@id='content']/div[3]/ul/li[2]/a")).click();
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 스토어팜 판매자센터의 고객문의 탭에 접근한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		
		
	}

	@When("^해당 문의내역 선택한 후 답변한다\\.$")
	public void 해당_문의내역_선택한_후_답변한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//driver.findElement(By.name("searchType")).click();
		//driver.findElement(By.xpath("//*[@id='sel_choice3']/option[5]")).click();
		data = new HashMap();
		try{
			//문의 제목으로 내역 선택
			Thread.sleep(3000);
			driver.findElement(By.partialLinkText("판매자문의 제목")).click();
			System.out.println(driver.findElement(By.partialLinkText("판매자문의 제목")));
			//답변 내용 입력 후 제출
			driver.findElement(By.id("commentContent")).sendKeys("판매자문의 답변");
			driver.findElement(By.id("regComment")).click();
			//답변처리가 완료되었습니다. 얼럿 확인
			Thread.sleep(2000);
			 try {
			        driver.switchTo().alert().accept();
			    } // try
			    catch (NoSuchWindowException e) {
			      	e.printStackTrace();
			    } // catch
			 //driver.close();
		
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : 해당 문의내역 선택한 후 답변한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		
	}

	@Then("^'고객문의 답변' me 알림을 확인한다\\.$")
	public void 고객문의_답변_me_알림을_확인한다() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		data = new HashMap();
		try{
			driver.get("http://dev.me.naver.com/box/noti.nhn");
			if(driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div[1]/div/a/span")).isDisplayed()){
			driver.findElement(By.xpath("//*[@id='container']/div/div[2]/div[1]/div/a/span")).click();
			Login();}
			driver.findElement(By.xpath("//*[@id='header']/div/div[2]/div/menu/li[3]/a")).click();
			Thread.sleep(1000);	
		String text="판매자에게 문의하신 내용";
		assertTrue(driver.findElement(By.partialLinkText(text)).isDisplayed());
		System.out.println(driver.findElement(By.partialLinkText(text)).getText());
		
		data.put("status_id", new Integer(1));
		data.put("comment", driver.findElement(By.partialLinkText(text)).getText());
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
		driver.close();
		}
		catch(Exception e){
		data.put("status_id", new Integer(5));
		data.put("comment", "Fail => step : '고객문의 답변' me 알림을 확인한다");
		JSONObject r = (JSONObject) client.sendPost("add_result/" + testId, data);
					
		}
		testId = "154885";
	   
	}

	@After("@tagToOrderandDelivery")
	public static void teardown(){
		driver.quit();
					 
			 
	}

}
