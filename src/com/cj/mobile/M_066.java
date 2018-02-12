package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
public class M_066 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : TC_66
	 * Scenario : 네로그인 > 회원가입 > 네이버 간편회원가입 > 상세정보입력
	 * Assertion : 회원가입 확인 얼럿 확인
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		M_URL = sp.getProperty("M_URL");
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps = DesiredCapabilities.android();
			// Devices Name 은 아무거나 입력해도 상관없음
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "1a66dc8f");
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
			System.out.println("Start driver.");
			driver = new AndroidDriver<WebElement>(appiumUrl, caps);

		} catch (Exception e) {
			try {
				Thread.sleep(5000);
				DesiredCapabilities caps = new DesiredCapabilities();
				caps = DesiredCapabilities.android();

				// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
				caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
				caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

				URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

				System.out.println("Start driver.");
				driver = new AndroidDriver<WebElement>(appiumUrl, caps);

			} catch (Exception e1) {
				{
					try {
						Thread.sleep(5000);
						DesiredCapabilities caps = new DesiredCapabilities();
						caps = DesiredCapabilities.android();

						// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
						caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
						caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
						caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

						URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

						System.out.println("Start driver.");
						driver = new AndroidDriver<WebElement>(appiumUrl, caps);

					} catch (Exception e2) {
						System.out.println("Session Creation failed.");
						e.printStackTrace();
						assertTrue(false);
						return;
					}
				}
			}
		}
	}

	@Test
	public void m_066() throws Exception {
		
		driver.get(M_URL);
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath("//*[@id='notToday']"), "오늘 하루 보지 않기");
		if (isExist) {
			driver.findElement(By.xpath("//*[@id=\"notToday\"]/label")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		
		// 좌측 메뉴 버튼 클릭
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/a[1]")).click();
		System.out.println("좌측 메뉴 버튼 클릭");
		Thread.sleep(3000);
		//로그인 버튼 클릭
		driver.findElement(By.xpath("//*[@id=\"gnb\"]/div[1]/a/span")).click();
		System.out.println("로그인 버튼 클릭");
		Thread.sleep(3000);
		//회원가입 이동
		driver.findElement(By.xpath("//*[@id=\"register\"]")).click();
		System.out.println("회원가입 이동");
		Thread.sleep(3000);
		
		// 스크롤 내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div[1]/ul/li[2]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		// 네이버 아이디로 회원가입
		driver.findElement(By.xpath("//*[@id=\"naver\"]")).click();
		System.out.println("네이버 아이디로 회원가입");
		Thread.sleep(3000);
		//아이디 로그인
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"id\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("cjlogintest");
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"pw\"]")).sendKeys("whtjdwn1");
		driver.findElement(By.xpath("//*[@id=\"frmNIDLogin\"]/fieldset/input")).click();
		Thread.sleep(3000);
		System.out.println("로그인 성공");
		//정보입력
		driver.findElement(By.xpath("//*[@id=\"birthdate\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"birthdate\"]")).sendKeys("19840705");
		driver.findElement(By.xpath("//*[@id=\"cellPhone\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"cellPhone\"]")).sendKeys("01072771111");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/h1")).click();
		System.out.println("회원가입 텍스트 입력");
		driver.findElement(By.xpath("//*[@id=\"registerForm\"]/fieldset/div[2]/span[1]/label/span")).click();
		// 스크롤 내리기
		Thread.sleep(3000);
		WebElement searchBtn1 = driver.findElement(By.xpath("//*[@id=\"footer\"]/div[1]/div[1]/ul/li[2]/a"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(searchBtn1).perform();
		
		System.out.println("완료 이동");
		driver.findElement(By.xpath("//*[@id=\"registerAccountSubmit\"]")).click();
		Thread.sleep(3000);
		System.out.println("가입완료");

		Thread.sleep(15000);

		// alert check
		if ("CJmall 회원가입이 완료되었습니다.".equals(driver.switchTo().alert().getText())) {
			System.out.println("TC_66 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_66 FAIL");
			assertTrue(false);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		// wait.ignoring(NoSuchElementException.class);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
}
