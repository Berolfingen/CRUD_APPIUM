package de.autodoc.pages;

import de.autodoc.core.BasePage;
import de.autodoc.pages.objects.VehiclesCataloguePageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static de.autodoc.util.InteractionMethods.clickOnTextView;
import static de.autodoc.util.InteractionMethods.waitForPresence;

public class VehiclesCataloguePage extends BasePage {

    private VehiclesCataloguePageObjects vehiclesCataloguePageObjects;

    VehiclesCataloguePage(AndroidDriver driver) {
        super(driver);
        vehiclesCataloguePageObjects = new VehiclesCataloguePageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), vehiclesCataloguePageObjects);
    }

    public void fillSearchField(String text) {
        waitForPresence(this.driver, vehiclesCataloguePageObjects.CoordinatorViewGroup);
        vehiclesCataloguePageObjects.SearchField.sendKeys(text);
    }

    public void clickOnCarDataFields(String text) {
        waitForPresence(this.driver, vehiclesCataloguePageObjects.CoordinatorViewGroup);
        clickOnTextView(this.driver, text);
    }

    public MainCataloguePage addEngine(String text) {
        clickOnCarDataFields(text);
        return new MainCataloguePage(driver);
    }

    public EditCarPage editEngine(String text) {
        clickOnCarDataFields(text);
        return new EditCarPage(driver);
    }
}
