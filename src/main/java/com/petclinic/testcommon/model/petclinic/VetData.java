package com.petclinic.testcommon.model.petclinic;

import java.util.List;

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
public class VetData {
    String name;
    List<String> specialization;

    public static VetData getEmptyVetData() {
        return VetData.builder()
                .build();
    }
}
