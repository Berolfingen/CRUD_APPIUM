package de.autodoc.pages.objects;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EditCarPageObjects {
    @AndroidFindBy(id = "de.autodoc.gmbh:id/coordinator")
    public AndroidElement EditYourVehiclesView;

    @AndroidFindBy(xpath = "(//android.widget.CheckedTextView)[1]")
    public AndroidElement EditCarView;

    @AndroidFindBy(xpath = "(//android.widget.CheckedTextView)[2]")
    public AndroidElement EditCarModel;

    @AndroidFindBy(xpath = "(//android.widget.CheckedTextView)[3]")
    public AndroidElement EditCarEngine;


    @AndroidFindBy(id = "de.autodoc.gmbh:id/tsbSaveCar")
    public AndroidElement SaveButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/deleteCar")
    public AndroidElement DeleteButton;

}
