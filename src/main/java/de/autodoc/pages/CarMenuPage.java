package de.autodoc.pages;

import de.autodoc.core.BasePage;
import de.autodoc.pages.objects.CarMenuPageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static de.autodoc.util.InteractionMethods.waitForPresence;

public class CarMenuPage extends BasePage {

    private CarMenuPageObjects carMenuPageObjects;

    CarMenuPage(AndroidDriver driver) {
        super(driver);
        carMenuPageObjects = new CarMenuPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), carMenuPageObjects);
    }

    public VehiclesCataloguePage clickCarMaker() {
        waitForPresence(this.driver, carMenuPageObjects.AddBySelectorLayout);
        carMenuPageObjects.CarMaker.click();
        return new VehiclesCataloguePage(driver);

    }
}


