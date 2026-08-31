package com.petclinic.testcommon.model.petclinic;

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
public class PetData {
    String name;
    String dateOfBirth;
    PetType petType;


    public static PetData getEmptyPetData(){
        return PetData.builder()
                .build();
    }
}
