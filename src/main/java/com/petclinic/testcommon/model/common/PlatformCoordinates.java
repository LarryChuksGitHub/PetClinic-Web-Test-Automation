package com.petclinic.testcommon.model.common;

import org.openqa.selenium.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PlatformCoordinates {
    private Point androidCoordinates;
    private Point iOSCoordinates;
    private Point iPadCoordinates;

}
