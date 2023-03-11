package com.selenium.demo.glue;

import com.selenium.demo.steps.InventorySteps;
import com.selenium.demo.steps.LoginSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginGlue {

    private WebDriver driver;
    private Scenario scenario;
    private InventorySteps inventorySteps(WebDriver driver){
        return new InventorySteps(driver);
    }

    @Before(order = 0)
    public void setup(){
        //setup
        //System.setProperty("webdriver.chrome.driver", "D:\\renzo\\Desarrollador\\Mundo Java\\Automatizacion\\Automatizacione01-selenium\\Drivers\\chromedriver.exe");
        //crear el driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();//maximizar ventana
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        driver.quit();
    }

    @Dado("que me encuentro en la pagina de login de SauceDemo")
    public void que_me_encuentro_en_la_pagina_de_login_de_sauce_demo() {
        driver.get("https://www.saucedemo.com/");
        screenshot();
    }
    @Cuando("inicio sesion con las credenciales usuario: {string}, contraseña: {string}")
    public void inicio_sesion_con_las_credenciales_usuario_contraseña(String user, String password) throws InterruptedException {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginWithCredentials(user, password);
        screenshot();
    }
    @Entonces("valido que deberia aparecer el titulo de {string}")
    public void valido_que_deberia_aparecer_el_titulo_de(String expectedTitle) throws InterruptedException {

        String title = inventorySteps(driver).getTitle();
        Thread.sleep(1000);
        //prueba
        Assertions.assertEquals(expectedTitle, title);
        screenshot();
    }
    @Entonces("valido que al menos exista {int} item")
    public void valido_que_al_menos_exista_item(Integer int1) throws InterruptedException {

        int itemsListSize = inventorySteps(driver).getItemsSize();
        Thread.sleep(1000);
        //prueba
        Assertions.assertTrue(itemsListSize > 0, "El tamaño de la lista es:"+ itemsListSize);
        screenshot();
    }

    public void screenshot(){
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(evidencia,"image/png", "evidencias");
    }
}
