package de.autodoc.pages.objects;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainCataloguePageObjects {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please add car to see catalog for your car']")
    public AndroidElement AddCarButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/rvCarsList")
    public AndroidElement CarsList;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/bottomNavigation")
    public AndroidElement Footer;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Catalogue\"))")
    public AndroidElement CatalogueTextView;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/swipeContent")
    public AndroidElement SwipeableTopCarInfo;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/rvCategories")
    public AndroidElement CategoriesView;


    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnEdit")
    public AndroidElement EditButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnDelete")
    public AndroidElement DeleteButton;

}
