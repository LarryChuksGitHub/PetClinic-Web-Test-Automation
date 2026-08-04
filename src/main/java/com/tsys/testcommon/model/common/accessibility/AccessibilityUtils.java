package com.tsys.testcommon.model.common.accessibility;

import static com.tsys.testcommon.model.common.accessibility.androidaccessibility.AndroidAccessibilityMobleElement.TOUCH_AREA_MIN_HEIGHT;
import static com.tsys.testcommon.model.common.accessibility.androidaccessibility.AndroidAccessibilityMobleElement.TOUCH_AREA_MIN_WIDTH;
import static com.tsys.testcommon.model.common.accessibility.iosaccessibility.IosAccessibilityMobleElement.BUTTON;
import static com.tsys.testcommon.model.common.accessibility.iosaccessibility.IosAccessibilityMobleElement.STATIC_TEXT;
import static com.tsys.testcommon.model.common.accessibility.iosaccessibility.IosAccessibilityMobleElement.TEXT_FIELD;
import static com.tsys.testcommon.model.common.accessibility.iosaccessibility.IosAccessibilityMobleElement.VALUE;

import org.openqa.selenium.WebElement;

import com.tsys.testcommon.framework.asserts.DippSoftAssertions;
import com.tsys.testcommon.model.common.accessibility.androidaccessibility.AndroidAccessibilityMobleElement;
import com.tsys.testcommon.model.common.accessibility.iosaccessibility.IosAccessibilityMobleElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessibilityUtils {
    private static final DippSoftAssertions softAssertions = new DippSoftAssertions();

    private AccessibilityUtils() {
    }

    public static void checkIosElementAccessibility(IosAccessibilityMobleElement accessibilityelement, WebElement element) {
        softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.NAME), String.format("Wrong accessibility name: %s", accessibilityelement.getName()), accessibilityelement.getName());
        softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.LABEL), String.format("Wrong accessibility label: %s", accessibilityelement.getLabel()), accessibilityelement.getLabel());
        checkIosButtonTouchArea(accessibilityelement, element);
        if (accessibilityelement.getType().contains(STATIC_TEXT)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(VALUE), String.format("Wrong accessibility value: %s", accessibilityelement.getLabel()), accessibilityelement.getLabel());
            softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.TYPE), String.format("Wrong accessibility type: %s", accessibilityelement.getType()), STATIC_TEXT);
        }
        if (accessibilityelement.getType().contains(TEXT_FIELD)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.TYPE), String.format("Wrong accessibility type: %s", accessibilityelement.getType()), TEXT_FIELD);
        }
        if (accessibilityelement.getType().contains(BUTTON)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.TYPE), String.format("Wrong accessibility type: %s", accessibilityelement.getType()), BUTTON);
        }
        softAssertions.assertTrue(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.ENABLED)), String.format("Wrong accessibility enabled: %s", IosAccessibilityMobleElement.ENABLED));
        softAssertions.assertTrue(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.VISIBLE)), String.format("Wrong accessibility visible: %s", IosAccessibilityMobleElement.VISIBLE));
        softAssertions.assertTrue(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.ACCESSIBLE)), String.format("Wrong accessibility accessible: %s", IosAccessibilityMobleElement.ACCESSIBLE));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getX())), String.format("Wrong accessibility X: %s", accessibilityelement.getX()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getY())), String.format("Wrong accessibility Y: %s", accessibilityelement.getY()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getIndex())), String.format("Wrong accessibility Index: %s", accessibilityelement.getIndex()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getWidth())), String.format("Wrong accessibility width width: %s", accessibilityelement.getWidth()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getHeight())), String.format("Wrong accessibility width height: %s", accessibilityelement.getHeight()));
        softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.FOCUSED), String.format("Wrong accessibility focused: %s", accessibilityelement.getFocused()), accessibilityelement.getFocused());
        softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.SELECTED), String.format("Wrong accessibility selected: %s", accessibilityelement.getSelected()), accessibilityelement.getSelected());
        softAssertions.assertFalse(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.FOCUSED)), String.format("Wrong accessibility focused: %s", IosAccessibilityMobleElement.FOCUSED));
        softAssertions.assertFalse(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.SELECTED)), String.format("Wrong accessibility selected: %s", IosAccessibilityMobleElement.SELECTED));
        softAssertions.assertTrue(Boolean.parseBoolean(element.getAttribute(IosAccessibilityMobleElement.HITTABLE)), String.format("Wrong accessibility hittable: %s", IosAccessibilityMobleElement.HITTABLE));
        softAssertions.assertAll();
    }

    public static void checkAndroidElementAccessibility(AndroidAccessibilityMobleElement accessibilityelement, WebElement element) {
        checkAndroidButtonTouchArea(accessibilityelement, element);
        if (accessibilityelement.getClassName().contains(AndroidAccessibilityMobleElement.STATIC_TEXT)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.TEXT), String.format("Wrong accessibility value: %s", accessibilityelement.getText()), accessibilityelement.getText());
            softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.CLASS_NAME), String.format("Wrong accessibility type: %s", accessibilityelement.getClassName()), AndroidAccessibilityMobleElement.STATIC_TEXT);
        }
        if (accessibilityelement.getClassName().contains(AndroidAccessibilityMobleElement.TEXT_FIELD)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.CLASS_NAME), String.format("Wrong accessibility type: %s", accessibilityelement.getClassName()), AndroidAccessibilityMobleElement.TEXT_FIELD);
        }
        if (accessibilityelement.getClassName().contains(BUTTON)) {
            softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.CLASS_NAME), String.format("Wrong accessibility type: %s", accessibilityelement.getClassName()), AndroidAccessibilityMobleElement.BUTTON);
        }
        softAssertions.assertTrue(Boolean.parseBoolean(element.getAttribute(AndroidAccessibilityMobleElement.ENABLED)), String.format("Wrong accessibility enabled: %s", AndroidAccessibilityMobleElement.ENABLED));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getX())), String.format("Wrong accessibility X: %s", accessibilityelement.getX()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getY())), String.format("Wrong accessibility Y: %s", accessibilityelement.getY()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getWidth())), String.format("Wrong accessibility width width: %s", accessibilityelement.getWidth()));
        softAssertions.assertNotNull(String.valueOf((accessibilityelement.getHeight())), String.format("Wrong accessibility width height: %s", accessibilityelement.getHeight()));
        softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.FOCUSED), String.format("Wrong accessibility focused: %s", accessibilityelement.getFocused()), accessibilityelement.getFocused());
        softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.SELECTED), String.format("Wrong accessibility selected: %s", accessibilityelement.getSelected()), accessibilityelement.getSelected());
        softAssertions.assertFalse(Boolean.parseBoolean(element.getAttribute(AndroidAccessibilityMobleElement.FOCUSED)), String.format("Wrong accessibility focused: %s", AndroidAccessibilityMobleElement.FOCUSED));
        softAssertions.assertFalse(Boolean.parseBoolean(element.getAttribute(AndroidAccessibilityMobleElement.SELECTED)), String.format("Wrong accessibility selected: %s", AndroidAccessibilityMobleElement.SELECTED));
        softAssertions.assertAll();
    }


    public static void checkIosButtonTouchArea(IosAccessibilityMobleElement accessibilityelement, WebElement element) {
        if (accessibilityelement.getType().contains(BUTTON)) {
            log.info(element.getText() + " Touch target width: " + accessibilityelement.getWidth());
            log.info(element.getText() + " Touch target heigth: " + accessibilityelement.getHeight());
            //Will be activated when Button touch target area will be fixed (height and width will be increased to minimum of 44pt.)
//            softAssertions.assertGreaterThan((accessibilityelement.getWidth()), String.format("Wrong accessibility width width: %s", accessibilityelement.getWidth()), TOUCH_AREA_MIN_WIDTH);
//            softAssertions.assertGreaterThan((accessibilityelement.getHeight()), String.format("Wrong accessibility height: %s", accessibilityelement.getHeight()), TOUCH_AREA_MIN_HEIGHT);
//            softAssertions.assertContainsIgnoreCase(element.getAttribute(IosAccessibilityMobleElement.TYPE), String.format("Wrong accessibility type: %s", accessibilityelement.getType()), BUTTON);
        }
        softAssertions.assertAll();
    }

    public static void checkAndroidButtonTouchArea(AndroidAccessibilityMobleElement accessibilityelement, WebElement element) {
        if (accessibilityelement.getClassName().contains(AndroidAccessibilityMobleElement.BUTTON)) {
            log.info(element.getText() + " Touch target width: " + accessibilityelement.getWidth());
            log.info(element.getText() + " Touch target heigth: " + accessibilityelement.getHeight());

            softAssertions.assertGreaterThan((accessibilityelement.getWidth()), String.format("Wrong accessibility width width: %s", accessibilityelement.getWidth()), TOUCH_AREA_MIN_WIDTH);
            softAssertions.assertGreaterThan((accessibilityelement.getHeight()), String.format("Wrong accessibility height: %s", accessibilityelement.getHeight()), TOUCH_AREA_MIN_HEIGHT);
            softAssertions.assertContainsIgnoreCase(element.getAttribute(AndroidAccessibilityMobleElement.CLASS_NAME), String.format("Wrong accessibility type: %s", accessibilityelement.getClassName()), AndroidAccessibilityMobleElement.BUTTON);
        }
        softAssertions.assertAll();
    }

}
