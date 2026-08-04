package com.verimi.testcommon.framework.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ErrorResponseBody {
    String id;
    String code;
    String timestamp;
    String title;
    String detail;
    ErrorResponseValidation validation;
}
