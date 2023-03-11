package com.selenium.demo;

import com.selenium.demo.page.InventoryPage;
import com.selenium.demo.page.LoginPage;
import com.selenium.demo.steps.InventorySteps;
import com.selenium.demo.steps.LoginSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class SauceDemoTest {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        //setup
        //System.setProperty("webdriver.chrome.driver", "D:\\renzo\\Desarrollador\\Mundo Java\\Automatizacion\\Automatizacione01-selenium\\Drivers\\chromedriver.exe");
        //crear el driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();//maximizar ventana
        //driver.get("https://www.saucedemo.com/");
        driver.get("https://www.google.com/");
    }

    @AfterEach
    public void quitDriver(){
        driver.quit();
    }

    //@Test
    public void loginHappyPath() throws InterruptedException{
        String user = "standard_user";
        String password = "secret_sauce";
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginWithCredentials(user, password);

        InventorySteps inventoryPage = new InventorySteps(driver);
        String title = inventoryPage.getTitle();
        Thread.sleep(1000);
        //prueba
        Assertions.assertEquals("Products", title);
    }

    //@Test
    public void validateItemsSize() throws InterruptedException{
        String user = "standard_user";
        String password = "secret_sauce";
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginWithCredentials(user, password);

        InventorySteps inventoryPage = new InventorySteps(driver);
        int itemsListSize = inventoryPage.getItemsSize();
        Thread.sleep(1000);
        //prueba
        Assertions.assertTrue(itemsListSize > 0, "El tamaño de la lista es:"+ itemsListSize);
    }

    //@Test
    public void searchSingers() throws InterruptedException {
        By searchInput = By.id("APjFqb");

        Thread.sleep(10000);
        WebElement searchElement = driver.findElement(searchInput);
        searchElement.sendKeys("Los mejores cantantes de rock del mundo");


        searchElement.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        By itemsList = By.cssSelector("div.UnFsfe.SoZvjb");
        List<WebElement> itemsListElement = driver.findElements(itemsList);
        Assertions.assertTrue(itemsListElement.size()> 3, "");

        Thread.sleep(1000);


    }




}
//# --> id
//. --> classname

//<input class="input_error form_input" placeholder="Username" type="text" data-test="username" id="user-name" name="user-name" autocorrect="off" autocapitalize="none" value="">

//cssSelector
// id : #user-name
// class : input.input_error.form_input

//xpath
//relativo: //input[@id='user-name']

//<div id="login_credentials" class="login_credentials"><h4>Accepted usernames are:</h4>standard_user<br>locked_out_user<br>problem_user<br>performance_glitch_user<br></div>
//<h4>Accepted usernames are:</h4>
//xpath: h4[text()='Accepted usernames are:']
//
//div[@id='login_credentials']/h4