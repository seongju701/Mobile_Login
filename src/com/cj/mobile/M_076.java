package com.cj.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;
public class M_076 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-10-30
	 * Subject : CJ Mall 운영  
	 * Name : M_076
	 * Scenario : 로그인 > 성인용품 검색 
	 * Assertion : "본 상품은 청소년 유해매체물로 청소년은 이용이 제한됩니다." 얼럿 확인
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
	public void m_076() throws Exception {
		
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
		
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"input_srh\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"input_srh\"]")).sendKeys("성인용품");
		driver.findElement(By.xpath("//*[@id=\"submitFrm\"]/fieldset/button[2]")).click();
		System.out.println("검색 완료");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"prd_lst0\"]/li/a/div[2]")).click();
		System.out.println("상품진입");
		Thread.sleep(5000);
		//로그인 클릭
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"id_input\"]")).sendKeys("child03");
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"password_input\"]")).sendKeys("whtjdwn1!");
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/h1")).click();
		driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]")).click();
		System.out.println("로그인 성공");
		Thread.sleep(10000);
		
		
		
		if ("본 상품은 청소년 유해매체물로 청소년은 이용이 제한됩니다.".equals(driver.switchTo().alert().getText())) {
			System.out.println("TC_076 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_076 PASS");
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