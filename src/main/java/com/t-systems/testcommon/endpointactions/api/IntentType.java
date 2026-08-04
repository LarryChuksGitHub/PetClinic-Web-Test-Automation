package com.verimi.testcommon.endpointactions.api;

public enum IntentType {
    CHILD_SUPPORT_FROM_BIRTH(1, "Child support for underage"),
    CHILD_SUPPORT_FOR_ADULT(2, "Child support for adult"),
    HOUSE_REGISTRATION(3, "House registration"),
    PASSPORT_APPLICATION(4, "Passport application"),
    JOB_CENTER_SUPPORT(5, "Job center support"),
    HEALTH_INSURANCE(6, "Health insurance"),
    RESIDENCE_PERMIT(7, "Residence permit"),
    FAMILY_STATUS_CHANGE(8, "Family status change"),
    STUDENT_SUPPORT(9, "Student support"),
    CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT(10, "Child support for underage or adult"),
    LIST_ALL_INTENT_OPTIONS(11, "Options are: Child support for underage, Child support for adult, House registration, " +
            "Passport application, Job center support, Health insurance, Residence permit, Family status change, Student support");


    private final int intentId;
    private final String intentMessage;


    IntentType(int intentId, String intentMessage) {
        this.intentId = intentId;
        this.intentMessage = intentMessage;
    }

    public int getIntentId() {
        return intentId;
    }

    public String getIntentMessage() {
        return intentMessage;
    }

}

