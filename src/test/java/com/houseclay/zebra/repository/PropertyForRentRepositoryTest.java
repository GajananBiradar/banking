package com.houseclay.zebra.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyForRentRepositoryTest {

    @Autowired
    private PropertyForRentRepository propertyForRentRepository;

    @Test
    public void savePropertyForRent(){

//        Address addr= Address.builder().house_no("A093").street_name("Kaverappa Layout")
//                .state("Karnataka").city("Bangalore").pincode(560103L).property_name("Krishvi Gavakshi").build();
//
//        Owner owner = Owner.builder().owner_name("Siva Reddy").owner_email("siva.reddy98@gmail.com").owner_contact("7892014327")
//                .owner_pan("CGVPB2011D").owner_contact_addr("A093 Krishvi Gavakshi Kaverappa Layout Bangalore").build();
//
//        Images img1 = Images.builder().build();
//        Images img2 = Images.builder().build();
//        List<Images> imgMap=new ArrayList<>();
//        imgMap.add(img1);
//        imgMap.add(img2);
//
//        PropertyRent propertyRent = PropertyRent.builder()
//                .name("Krishvi Gavakshi").title("2 BHK in Krishvi Gavakshi")
//                .isManaged(true).full_address(addr).owner(owner).imageMap(imgMap)
//                                    .build();
//        propertyForRentRepository.save(propertyRent);
    }
}