package com.illichso.e2etests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
public class IMDBSeleniumTest {

    private static final String URL = "https://www.imdb.com/";
    private static final String EMAIL = "garp.preferences@gmail.com";
    private static final String PASSWORD = "Karolina789*";

    private WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void shouldSignInWithExistingImdbAccount() {
        driver.findElement(By.id("nblogin")).click();

        WebElement signInOptions = driver.findElement(By.id("signin-options"));
        signInOptions.findElement(By.linkText("Sign in with IMDb")).click();

        driver.findElement(By.id("ap_email")).sendKeys(EMAIL);
        driver.findElement(By.id("ap_password")).sendKeys(PASSWORD);
        driver.findElement(By.id("signInSubmit")).submit();

        String userNameExpected = EMAIL.substring(0, EMAIL.indexOf("@"));
        WebElement navigationUserMenu = driver.findElement(By.id("navUserMenu"));
        Assert.assertThat(navigationUserMenu.getText(), equalTo(userNameExpected));
    }

    @Test
    public void shouldFindFilmByUserRequest() {
        driver.findElement(By.id("navbar-query")).sendKeys("Inception");
        driver.findElement(By.id("navbar-submit-button")).submit();

        WebElement mainSection = driver.findElement(By.id("main"));
        WebElement searchingResults = mainSection.findElement(By.className("findList"));
        List<WebElement> textsInSearchingResults = searchingResults.findElements(By.className("result_text"));

        WebElement webElementWanted = textsInSearchingResults
                .stream()
                .filter(webElement -> webElement.getText().equals("Inception (2010)")).findFirst().get();

        webElementWanted.findElement(By.linkText("Inception")).click();
    }
}
