package com.petclinic.testcommon.framework.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseValidation {
    String email;
    String representativeBusinessPartnerNumber;
}
