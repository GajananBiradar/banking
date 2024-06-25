package com.houseclay.zebra.service;

import com.houseclay.zebra.dto.PropertyRentDTO;
import com.houseclay.zebra.model.rental.PropertyRent;

import java.util.UUID;

public interface PropertRentService {

    PropertyRent saveResidentialPropertyForRent(PropertyRent property_for_rent);

    PropertyRent saveResidentialPropertyForRentv2(PropertyRent property_for_rent);

    PropertyRentDTO findPropertyForRentById(UUID uuid);

    Boolean deletePropertyForRentById(UUID uuid);

    PropertyRent updatePropertyForRentById(UUID uuid);


}
