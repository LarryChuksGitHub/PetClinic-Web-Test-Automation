package com.tsys.testcommon.framework.utils.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {

    //CSS constants
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String TEXT_CONTEXT_ATTRIBUTE = "textContent";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String CONTENT_DESC_ATTRIBUTE = "content-desc";

    //API constants
    public static final String LANGUAGE_HEADER = "language";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String SUCCEEDED = "SUCCEEDED";
    public static final String FAILED = "FAILED";
    public static final String PENDING = "PENDING";
    public static final String SUCCESS = "SUCCESS";
    public static final String FINISHED = "FINISHED";
    public static final String WAITING_FOR_IDENT_DATA = "WAITING_FOR_IDENT_DATA";
    public static final String CANCELLED = "CANCELLED";
    public static final String STARTED = "STARTED";
    public static final String NEW = "NEW";

    //DB constants
    public static final String HASH = "hash";
    public static final String CREATED_AT = "created_at";
    public static final String STATUS = "status";
    public static final String DEVICE_ID = "so_device_id";
    public static final String VERIMI_IDENT_TXN_ID = "verimi_ident_txn_id";
    public static final String COUPON_EXPIRES_AT = "coupon_expires_at";
    public static final String CASE_ID = "case_id";
    public static final String TRANSACTION_CASE_ID = "caseId";
    public static final String SCHUFA_IDENT_STATUS = "schufa_ident_status";
    public static final String SCHUFA_BANK_STATUS = "schufa_bank_status";
    public static final String REQUESTING_METHOD = "requesting_method";
    public static final String AIS_STATUS = "ais_status";
    public static final String UID = "uid";
    public static final String PERMANENT_BLOCKED = "permanent_blocked";
    public static final String INLINE_REGISTRATION_EXECUTED = "inline_registration_executed";
    public static final String TEMPORARY = "temporary";
    public static final String SUSPENDED = "suspended";
    public static final String NFA_REQUIRED = "nfa_required";
    public static final String OTP_VERIFIED = "otp_verified";
    public static final String EMAIL_VERIFIED = "email_verified";
    public static final String LOGIN_FAILURE_COUNT = "login_failure_count";
    public static final String DATA_TYPE = "data_Type";
    public static final String USER_DATA = "user_data";
    public static final String USER_TYPE = "user_type";
    public static final String IDENT_PROVIDER_DATA = "ident_provider_data";
    public static final String PREFILL_EDITABLE = "prefill_editable";
    public static final String TRANSLITERATION_APPLIED = "transliteration_applied";
    public static final String MODIFIED_AT = "modified_at";
    public static final String PREFILLED = "prefilled";
    public static final String FOURTHLINE_VERIFICATION_ID = "fourthline_verification_id";
    public static final String SDA_STATUS = "source_data_archive_status";
    public static final String VERIFICATION_STATUS = "verification_status";
    public static final String SD_RETRY_COUNT_COLUMN = "source_data_retry_count";
    public static final String SUCCESS_STATUS = "SUCCESS";
    public static final String REASON = "reason";
    public static final String ID = "id";
    public static final String ID_CARD = "ID_CARD";
    public static final String PASSPORT = "PASSPORT";
    public static final String DOCUMENT_ID = "document_id";
    public static final String FIELD_NAME = "field_name";
    public static final String CONFIDENCE_CATEGORY = "confidence_category";
    public static final String SOURCES = "sources";
    public static final String UID_COLUMN = "uid";
    public static final String VERIFIED_COLUMN = "verified";
    public static final String REASON_COLUMN = "reason";
    public static final String VERIFIED_STATE = "VERIFIED";
    public static final String PENDING_STATE = "PENDING";

    //Text
    public static final String PUT_REQUEST_LOG_MESSAGE = "Execute PUT request to '{}' endpoint";
    public static final String EMPTY_STRING = "";
    public static final String ERROR_MESSAGE_NOT_CORRECT = "Error message is not the expected";

    //Date
    public static final String DATE_FORMAT_EN = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DE = "dd.MM.yyyy";
}
