package com.petclinic.testcommon.framework.testgroup;

public class TestGroup {


    //PetClinic
    public static final String FRONTEND_SMOKE_PETCLINIC = "frontend-smoke-petclinic";
    public static final String BACKEND_SMOKE_PETCLINIC = "backend-smoke-petclinic";
    public static final String MOBILE_SMOKE_PETCLINIC= "mobile-smoke-petclinic";
    public static final String MOBILE_REGRESSION_PETCLINIC = "mobile-regression-petclinic";
    public static final String FRONTEND_REGRESSION_PETCLINIC = "frontend-regression-petclinic";
    public static final String BACKEND_REGRESSION_PETCLINIC = "backend-regression-petclinic";

    private TestGroup() {
        throw new IllegalStateException("Utility class");
    }

}
