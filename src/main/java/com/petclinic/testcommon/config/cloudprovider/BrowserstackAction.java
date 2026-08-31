package com.petclinic.testcommon.config.cloudprovider;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.petclinic.testcommon.model.common.SwipeDirection;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserstackAction extends CloudSpecificAction {

    protected BrowserstackAction(WebDriver driver) {
        super(driver);
    }

    @Override
    public void swipe(SwipeDirection swipeDirection, Duration duration) {
        Dimension size = driver.manage().window().getSize();
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;
        if (driver instanceof AndroidDriver) {
            AndroidTouchAction touchAction = null;
            log.info("Device screen size: " + size);
            startX = 0;
            endX = 0;
            startY = 0;
            endY = 0;

            switch (swipeDirection) {
                case RIGHT:
                    startY = size.height / 2;
                    startX = (int) (size.width * 0.10);
                    endX = (int) (size.width * 0.50);
                    touchAction = new AndroidTouchAction(((PerformsTouchActions) driver));
                    var perform = touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    break;

                case LEFT:
                    startY = size.height / 2;
                    startX = (int) (size.width * 0.90);
                    endX = (int) (size.width * 0.10);
                    touchAction = new AndroidTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();

                    break;

                case DOWN:
                    endY = (int) (size.height * 0.98);
                    startY = (int) (size.height * 0.70);
                    startX = (size.width / 2);
                    touchAction = new AndroidTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();
                    break;


                case UP:
                    endY = (int) (size.height * 0.20);
                    startY = (int) (size.height * 0.70);
                    startX = (size.width / 2);
                    touchAction = new AndroidTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();

                    break;
            }
        } else if (driver instanceof IOSDriver) {
            IOSTouchAction touchAction = null;
            size = driver.manage().window().getSize();
            log.info("Device screen size: " + size);
            startX = 0;
            endX = 0;
            startY = 0;
            endY = 0;
            switch (swipeDirection) {
                case RIGHT:
                    startY = size.height / 2;
                    startX = (int) (size.width * 0.10);
                    endX = (int) (size.width * 0.90);
                    touchAction = new IOSTouchAction(((PerformsTouchActions) driver));
                    var perform = touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    break;

                case LEFT:
                    startY = size.height / 2;
                    startX = (int) (size.width * 0.90);
                    endX = (int) (size.width * 0.10);
                    touchAction = new IOSTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();

                    break;
                case DOWN:
                    endY = (int) (size.height * 0.70);
                    startY = (int) (size.height * 0.30);
                    startX = (size.width / 2);
                    touchAction = new IOSTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();
                    break;
                case UP:
                    startY = (int) (size.height * 0.80);
                    endY = (int) (size.height * 0.10);
                    startX = (size.width / 2);
                    touchAction = new IOSTouchAction((PerformsTouchActions) driver);
                    touchAction.press(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(duration))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();
                    break;
            }
        }
    }
}
