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

public class M_043 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	// boolean setupSuccess = true;
	private String ID_1 = null;
	private String PW_1 = null;
	private String M_URL = null;
	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-06-19
	 * Subject : CJ Mall 운영  
	 * Name : M_043
	 * Scenario :  TV 쇼핑 > 편성표 > 방송알림 신청 버튼 선택 > 알럿 확인 버튼 선택 > ID / PW 입력 > 로그인
	 * Assertion : 방송알림 신청 Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		//System.out.println("=====setUp start======");
		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
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
	public void m_043() throws Exception {
		
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
		
		// TV쇼핑
		driver.findElement(By.linkText("TV쇼핑")).click();
		System.out.println("TV쇼핑 진입");
		Thread.sleep(3000);
		// 편성표 진입

		boolean isExist1 = false;
		isExist1 = existElement(driver, By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[2]/ul/li[2]/a"), "편성표_1");
		if (isExist1) {
			driver.findElement(By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[2]/ul/li[2]/a")).click();
		} else {
			driver.findElement(By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[3]/ul/li[2]/a")).click();
		}
		System.out.println("편성표 보기 클릭");
		// 다음달 진입
		driver.findElement(By.xpath("//*[@id='scheduleDate']/div/ul/li[7]/a")).click();
		System.out.println("다음달 진입");
		Thread.sleep(3000);
		boolean isExist4 = false;
		boolean isExist5 = false;
		// 상담신청 체크
		isExist4 = existElement(driver, By.xpath(".//*[@id='scheduleItem']/div[2]/ul/li/a/div[2]/div/span"),
				"단일 상담신청 상품");
		isExist5 = existElement(driver, By.xpath(".//*[@id='scheduleItem']/div[2]/ul/li[1]/a/div[2]/div[1]/div/span"),
				"복수 상담신청 상품");
		if (isExist4 && driver.findElement(By.xpath(".//*[@id='scheduleItem']/div[2]/ul/li/a/div[2]/div/span"))
				.getText().equals("상담신청 상품")) {
			System.out.println("상담신청상품 입니다. (단일)");
			assertTrue(true);
			return;
		} else {
			// 상담신청 체크 (복수상품)
			// 대표상품 가격란에 '상담신청상품' 체크
			if (isExist5
					&& driver.findElement(By.xpath(".//*[@id='scheduleItem']/div[2]/ul/li[1]/a/div[2]/div[1]/div/span"))
							.getText().equals("상담신청 상품")) {
				System.out.println("상담신청상품 입니다. (복수)");
				assertTrue(true);
				return;
			}
			// 상담신청 상품이 아닐경우 else 진행
			else {
				System.out.println("상담신청상품 아닙니다. 계속 진행");
				Thread.sleep(3000);
			}
		}
		driver.findElement(By.xpath("//*[@id='scheduleItem']/div[2]/ul/li/div/a")).click();
		System.out.println("방송알림 진입");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/fieldset/div[2]")).click();
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		
		// text check
		Thread.sleep(7000);
		System.out.println(driver.findElement(By.xpath("//*[@id='notificationItem']/div/div[1]/h1")).getText());
		
		if ("방송알림 신청".equals(driver.findElement(By.xpath("//*[@id='notificationItem']/div/div[1]/h1")).getText())) {
			Thread.sleep(3000);
			System.out.println("TC_43 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_43 FAIL");
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
