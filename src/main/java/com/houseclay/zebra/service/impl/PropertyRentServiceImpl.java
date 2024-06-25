package com.houseclay.zebra.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.houseclay.zebra.dto.PropertyRentDTO;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.rental.Images;
import com.houseclay.zebra.model.rental.PropertyRent;
import com.houseclay.zebra.dto.ImageResponse;
import com.houseclay.zebra.repository.ImageRepository;
import com.houseclay.zebra.repository.PropertyForRentRepository;
import com.houseclay.zebra.service.PropertRentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PropertyRentServiceImpl implements PropertRentService {

    @Autowired
    PropertyForRentRepository propertyForRentRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public PropertyRent saveResidentialPropertyForRent(PropertyRent property_for_rent) {
        return propertyForRentRepository.save(property_for_rent);
    }

    @Override
    public PropertyRent saveResidentialPropertyForRentv2(PropertyRent property_for_rent)  {
        return propertyForRentRepository.save(property_for_rent);
    }

    @Override
    public PropertyRentDTO findPropertyForRentById(UUID uuid) {

        //String jsonData = null;
        //Fetch the List of Images from the Images Table by providing the property uuid

        Optional<PropertyRent> propertyRent = propertyForRentRepository.findById(uuid);
        PropertyRentDTO propertyRentDTO;
        try{
            List<ImageResponse> list_of_images = propertyRent.get().
                    getImageMap().stream().map(this::mapToImageResponse).collect(Collectors.toList());
            propertyRentDTO = mapToPropertyRentDTO(propertyRent.get(),list_of_images);
        }
        catch (Exception e){
            throw new NullPointerException("The Property is Not Present");
        }


        return propertyRentDTO;
    }



    @Override
    public Boolean deletePropertyForRentById(UUID uuid) {

        Optional<PropertyRent> propertyForRent = propertyForRentRepository.findById(uuid);

        if(propertyForRent.isPresent()){
            propertyForRentRepository.deleteById(uuid);
            return true;
        }

        return false;
    }

    @Override
    public PropertyRent updatePropertyForRentById(UUID uuid) {
        return null;
    }



    private PropertyRentDTO mapToPropertyRentDTO(PropertyRent proRent, List<ImageResponse> imageResponse){

        PropertyRentDTO propertyRentDTO = PropertyRentDTO.builder().
                name(proRent.getName()).title(proRent.getTitle()).owner(proRent.getOwner()).
                isManaged(proRent.isManaged()).propertySpecs(proRent.getPropertySpecs()).
                imageMap(imageResponse).active_status(proRent.isActive_status()).
                inactive_reason(proRent.getInactive_reason()).posted_on(proRent.getPosted_on()).
                available_from(proRent.getAvailable_from()).
                property_rent(proRent.getProperty_rent())
                .property_maintenance(proRent.getProperty_maintenance()).
                preferred_tenant_type(proRent.getPreferred_tenant_type()).created_by(proRent.getBaseTimeStamp().getCreated_by())
                        .created_on(proRent.getBaseTimeStamp().getCreated_on()).
                build();

         return propertyRentDTO;
    }

    private ImageResponse mapToImageResponse(Images image){

        String downloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/").path(image.getImage_id().toString()).toUriString();

        ImageResponse imageResponse = ImageResponse.builder().id(image.getImage_id())
                .name(image.getName()).contentType(image.getContentType())
                .size(image.getSize()).url(downloadUrl).build();
        return imageResponse;
    }


    /**
     * This method is for converting the json String to PropertyRent object using ObjectMapper
     * It is also binding the data of the Image List of Files into the PropertyRent Object
     * @param jsonProperty
     * @param files
     * @return
     */
    public PropertyRent getJson(String jsonProperty, List<MultipartFile> files){

        log.info("Inside getJson Method---- PropertyRentServiceImpl");
        PropertyRent propertyRentJson = new PropertyRent();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            propertyRentJson = objectMapper.readValue(jsonProperty,PropertyRent.class);
            System.out.println(propertyRentJson);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }




        List<Images> list_of_Images = new ArrayList<Images>();
        for(MultipartFile f: files){
            Images image = null;
            try {
                image = Images.builder().
                        name(StringUtils.cleanPath(f.getOriginalFilename())).
                        contentType(f.getContentType()).baseTimeStamp(BaseTimeStamp.builder().created_by("SYSTEM").created_on(new Date()).build())
                        .size(f.getSize()).image_data(f.getBytes()).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            list_of_Images.add(image);
        }

        propertyRentJson.setImageMap(list_of_Images);
        return propertyRentJson;
    }
}
