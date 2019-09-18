package de.autodoc.pages.objects;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CarMenuPageObjects {
    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Car maker']")
    public AndroidElement CarMaker;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/llAddBySelectorPart")
    public AndroidElement AddBySelectorLayout;
}
