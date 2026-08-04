package com.verimi.testcommon.testdata;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DeutschlandAppUserData {
    String childFirstName;
    String childLastName;
    String email;
    String streetAddress;
    String houseNumber;
    String city;
    String additionalAddress;
    String poctcode;
    String phoneCode;
    String phoneNumber;
    String childBirthDate;
    String citizenship;
    String identificationNumber;
    String applicantBirthdate;
    String applicantFirstName;
    String applicantLastName;
    String furtherInfo;
    OtherParent otherParent;


    @Data
    @Builder
    public static class OtherParent {
        String firstName;
        String lastName;
        String iban;
        String bic;
        String bank;
    }

    public static DeutschlandAppUserData getUserData() {
       OtherParent otherParent = OtherParent.builder()
               .firstName("Jane")
               .lastName("Walter")
               .iban("DE89370400440532014000")
               .bic("COBADEFFXXX")
               .bank("Berliner Sparkasse")
               .build();
        return DeutschlandAppUserData.builder()
                .childFirstName("Leo")
                .childLastName("Günther")
                .childBirthDate("13.01.1970")
                .citizenship("DE")
                .poctcode("90403")
                .city("Nürnberg")
                .streetAddress("Luckeweg")
                .houseNumber("200")
                .phoneCode("+49")
                .phoneNumber("123456789")
                .additionalAddress("Erdgeschoss")
                .identificationNumber("123456789")
                .applicantFirstName("Hans")
                .applicantLastName("Günther")
                .applicantBirthdate("13.01.1970")
                .furtherInfo("Some additional information")
                .otherParent(otherParent)
                .build();


    }

}
