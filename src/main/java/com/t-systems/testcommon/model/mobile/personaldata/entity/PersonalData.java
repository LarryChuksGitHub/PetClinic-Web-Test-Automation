package com.verimi.testcommon.model.mobile.personaldata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalData {
    private String screenTitle;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String insuranceNumber;
    private String continueButton;
    private String cancelButton;
    private String message;
}
