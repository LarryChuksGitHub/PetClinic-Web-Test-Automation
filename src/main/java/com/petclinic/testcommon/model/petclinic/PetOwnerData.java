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
public class PetOwnerData {
    String firstName;
    String lastName;
    String address;
    String city;
    String telephone;


    public static PetOwnerData getEmptyOwnerData(){
        return PetOwnerData.builder()
                .build();
    }
}
