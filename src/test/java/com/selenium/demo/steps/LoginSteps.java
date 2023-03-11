package com.selenium.demo.steps;

import com.selenium.demo.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private WebDriver driver;
    public LoginSteps( WebDriver driver){
        this.driver = driver;
    }

    public void typeUser(String user) {
        driver.findElement(LoginPage.userInput).sendKeys(user);
    }

    public void typePassword(String password) {
        driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void login() {
        driver.findElement(LoginPage.loginButton).click();
    }


    public void loginWithCredentials(String usuario, String password) throws InterruptedException {
        typeUser(usuario);
        Thread.sleep(1000);
        typePassword(password);
        Thread.sleep(1000);
        login();
        Thread.sleep(1000);
    }


    public void loginWithEmptyPassword(String usuario, String password) throws InterruptedException {
        typeUser(usuario);
        Thread.sleep(1000);
        login();
        Thread.sleep(1000);
    }
}
