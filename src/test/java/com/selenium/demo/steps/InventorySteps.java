package com.selenium.demo.steps;

import com.selenium.demo.page.InventoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventorySteps {
    private WebDriver driver;

    public InventorySteps(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return this.driver.findElement(InventoryPage.productsTitle).getText();
    }

    public int getItemsSize(){
        return this.driver.findElements(InventoryPage.items).size();
    }
}
