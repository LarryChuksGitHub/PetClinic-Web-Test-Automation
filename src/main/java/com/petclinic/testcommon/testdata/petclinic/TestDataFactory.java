package com.petclinic.testcommon.testdata.petclinic;

import java.time.LocalDate;
import java.util.UUID;

import com.petclinic.testcommon.framework.utils.datetime.DateTimeUtils;
import com.petclinic.testcommon.framework.utils.random.RandomUtilities;
import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.model.petclinic.PetType;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static PetOwnerData uniqueOwner() {
        String id = UUID.randomUUID()
                        .toString()
                        .substring(0, 8);
        PetOwnerData owner = PetOwnerData.getEmptyOwnerData();
        owner.setFirstName(RandomUtilities.generateRandomName() + id)
                .setLastName("LastName" + id)
                .setAddress("Teststraße" + id)
                .setCity("Berlin")
                .setTelephone("030" + RandomUtilities.generateRandomStringDigit(7));
        return owner;
    }
    public static PetData uniquePet() {
        String id = UUID.randomUUID()
                        .toString()
                        .substring(0, 8);
        PetData pet = PetData.getEmptyPetData();

        // Define the date range boundaries
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(2026, 12, 31);

        pet.setName(RandomUtilities.generateRandomName()+ id)
                .setPetType(PetType.BIRD)
                .setDateOfBirth(DateTimeUtils.generateRandomDateInDdMmYyyyFormat(startDate, endDate));

        return pet;
    }

    public static String uniquePetName() {

        return "Bello" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 6);
    }

    public static String uniqueOwnerLastName() {

        return "PetOwnerData" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 6);
    }
}