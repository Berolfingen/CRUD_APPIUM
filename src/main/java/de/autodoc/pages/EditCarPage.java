package de.autodoc.pages;

import de.autodoc.core.BasePage;
import de.autodoc.pages.objects.EditCarPageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static de.autodoc.util.InteractionMethods.waitForPresence;

public class EditCarPage extends BasePage {
    private EditCarPageObjects editCarPageObjects;

    EditCarPage(AndroidDriver driver) {
        super(driver);
        editCarPageObjects = new EditCarPageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(driver), editCarPageObjects);
    }

    public VehiclesCataloguePage clickOnEditCarField() {
        waitForPresence(this.driver, editCarPageObjects.EditYourVehiclesView);
        editCarPageObjects.EditCarView.click();

        return new VehiclesCataloguePage(driver);
    }

    public String getFirstChosenCarText() {
        return editCarPageObjects.EditCarView.getText();
    }

    public String getFirstChosenCarModelText() {
        return editCarPageObjects.EditCarModel.getText();
    }

    public String getFirstChosenCarEngineText() {
        return editCarPageObjects.EditCarEngine.getText();
    }

    public MainCataloguePage clickSaveChanges() {
        waitForPresence(this.driver, editCarPageObjects.EditYourVehiclesView);
        editCarPageObjects.SaveButton.click();

        return new MainCataloguePage(driver);
    }

    public MainCataloguePage clickDeleteButton() {
        waitForPresence(this.driver, editCarPageObjects.EditYourVehiclesView);
        editCarPageObjects.DeleteButton.click();

        return new MainCataloguePage(driver);
    }
}
