package com.verimi.testcommon.model.common.accessibility.androidaccessibility;

import static com.verimi.testcommon.config.Config.isAndroid;

import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder()
@Slf4j
public class AndroidAccessibilityMobleElement {

    public static final String BUTTON = "android.widget.Button";
    public static final String IMAGE = "android.widget.Image";
    public static final String TEXT_FIELD = "android.widget.EditText";
    public static final String STATIC_TEXT = "android.widget.TextView";
    public static final String CLASS = "class";
    public static final String CLASS_NAME = "className";
    public static final String PACKAGE = "package";
    public static final String TEXT = "text";
    public static final String CHECKABLE = "checkable";
    public static final String CHECKED = "checked";
    public static final String CLICKABLE = "clickable";
    public static final String NAME = "name";
    public static final String LABEL = "label";
    public static final String ENABLED = "enabled";
    public static final String RESOURCE_ID = "resource-id";
    public static final String VISIBLE = "visible";
    public static final String ACCESSIBLE = "accessible";
    public static final String INDEX = "index";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String WIDTH = "width";
    public static final String HEIGTH = "height";
    public static final String FOCUSED = "focused";
    public static final String FOCUSABLE = "focusable";
    public static final String SELECTED = "selected";
    public static final String LONG_CLICKABLE = "long-clickable";
    public static final String PASSWORD = "password";
    public static final String VALUE = "value";
    public static final String SCROLLABLE = "scrollable";
    public static final String SELECTION_START = "selection-start";
    public static final String SIZE = "size";
    public static final String BOUNDS = "bounds";
    public static final String DISPLAYED = "displayed";
    public static final int TOUCH_AREA_MIN_WIDTH = 44;
    public static final int TOUCH_AREA_MIN_HEIGHT = 44;

    public static final String RECT = "rect";
    private static JSONObject rect;

    private String className;
    private String contentDescription;
    private String text;
    private String name;
    private String contentSize;
    private String resourceId;
    private String checkable;
    private String checked;
    private String clickable;
    private String focusable;
    private String longClickble;
    private boolean enabled;
    private String password;
    private String selectionStart;
    private String visible;
    private String scrollable;
    private String bounds;
    private String displayed;
    private int x;
    private int y;
    private int width;
    private int height;
    private String index;
    private String uid;
    private String focused;
    private String selected;
    private String value;
    private Dimension size;

    public static AndroidAccessibilityMobleElement getDefaultAndroidAccessibilityElement(WebElement button) {
        if (isAndroid()) {
            Dimension dimension = button.getSize();
            AndroidAccessibilityMobleElement accessibilityElement = AndroidAccessibilityMobleElement.builder()
                    .className(button.getAttribute(CLASS_NAME))
                    .text(button.getAttribute(TEXT))
                    .checkable(button.getAttribute(CHECKABLE))
                    .checked(button.getAttribute(CHECKED))
                    .clickable(button.getAttribute(CLICKABLE))
                    .focusable(button.getAttribute(FOCUSABLE))
                    .longClickble(button.getAttribute(LONG_CLICKABLE))
                    .password(button.getAttribute(PASSWORD))
                    .scrollable(button.getAttribute(SCROLLABLE))
                    .bounds(button.getAttribute(BOUNDS))
                    .displayed(button.getAttribute(DISPLAYED))
                    .enabled(Boolean.parseBoolean(button.getAttribute(ENABLED)))
                    .width(dimension.getWidth())
                    .height((dimension.getHeight()))
                    .focused(button.getAttribute(FOCUSED))
                    .selected(button.getAttribute(SELECTED))
                    .size(dimension)
                    .build();
            log.info("Element width: " + accessibilityElement.getWidth());
            log.info("Element height: " + accessibilityElement.getHeight());
            return accessibilityElement;
        } else {
            return null;
        }
    }
}
