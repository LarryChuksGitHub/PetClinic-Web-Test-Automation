package com.petclinic.testcommon.model.petclinic;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Accessors(chain = true)
@Data
public class VisitData {
    Date date;
    String description;

    public static VisitData getEmptyVisitData() {
        return VisitData.builder()
                .build();
    }
}
