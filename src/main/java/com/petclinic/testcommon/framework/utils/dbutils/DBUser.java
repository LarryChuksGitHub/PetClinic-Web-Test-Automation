package com.petclinic.testcommon.framework.utils.dbutils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DBUser {

    final static String SUBACCOUNT = "00";

    private String filiale;
    private String konto;
    private String pin;

    public String getUSerName() {
        return filiale + konto + SUBACCOUNT;
    }

}
