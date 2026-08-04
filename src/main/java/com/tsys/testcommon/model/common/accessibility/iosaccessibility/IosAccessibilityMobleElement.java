package com.tsys.testcommon.model.common.accessibility.iosaccessibility;

import static com.tsys.testcommon.config.Config.isIOS;

import org.json.JSONException;
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
public class IosAccessibilityMobleElement {

    public static final String BUTTON = "XCUIElementTypeButton";
    public static final String IMAGE = "XCUIElementTypeImage";
    public static final String TEXT_FIELD = "TextField";
    public static final String STATIC_TEXT = "StaticText";
    public static final String TYPE = "type";
    public static final String NAME = "name";
    public static final String LABEL = "label";
    public static final String UNCHECKEDBOX = "checkBoxUnChecked";
    public static final String BOX = "Box";
    public static final String CHECKEDBOX = "checkBoxChecked";
    public static final String ENABLED = "enabled";
    public static final String VISIBLE = "visible";
    public static final String ACCESSIBLE = "accessible";
    public static final String INDEX = "index";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String WIDTH = "width";
    public static final String HEIGTH = "height";
    public static final String FOCUSED = "focused";
    public static final String SELECTED = "selected";
    public static final String HITTABLE = "hittable";
    public static final String VALUE = "value";
    public static final String SIZE = "size";
    public static final int TOUCH_AREA_MIN_WIDTH = 44 - 1;
    public static final int TOUCH_AREA_MIN_HEIGHT = 44 - 1;

    public static final String RECT = "rect";
    private static JSONObject rect;

    private String type;
    private String name;
    private String label;
    private boolean enabled;
    private String visible;
    private String accessible;
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

    public static IosAccessibilityMobleElement getDefaultIosAccessibilityElement(WebElement button) {
        if (isIOS()) {
            Dimension dimension = button.getSize();
            IosAccessibilityMobleElement accessibilityElement = IosAccessibilityMobleElement.builder()
                    .type(button.getAttribute(TYPE))
                    .value(button.getAttribute(VALUE))
                    .name(button.getAttribute(NAME))
                    .label(button.getAttribute(LABEL))
                    .enabled(Boolean.parseBoolean(button.getAttribute(ENABLED)))
                    .visible(button.getAttribute(VISIBLE))
                    .accessible(button.getAttribute(ACCESSIBLE))
                    .width(dimension.getWidth())
                    .height((dimension.getHeight()))
                    .index(button.getAttribute(INDEX))
                    .uid(button.getAttribute("UID"))
                    .focused(button.getAttribute(FOCUSED))
                    .selected(button.getAttribute(SELECTED))
                    .size(dimension)
                    .build();
            JSONObject rect;
            try {
                rect = new JSONObject(button.getAttribute(RECT));
                accessibilityElement.setX((Integer) rect.get(X))
                        .setY((Integer) rect.get(Y));
            } catch (JSONException exception) {
                log.info("JSON object, Rect is null: " + exception.getMessage());
            }
            return accessibilityElement;
        } else {
            return null;
        }
    }
}
