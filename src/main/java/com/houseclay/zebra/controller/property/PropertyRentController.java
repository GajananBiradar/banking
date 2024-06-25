package com.houseclay.zebra.controller.property;


import com.houseclay.zebra.dto.PropertyRentDTO;
import com.houseclay.zebra.model.rental.PropertyRent;
import com.houseclay.zebra.service.ImagesService;
import com.houseclay.zebra.service.impl.PropertyRentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "Rental Properties")
@RequestMapping(value = "/post-property-for-rent")
public class PropertyRentController {


    @Autowired
    PropertyRentServiceImpl propertyRentService;
    @Autowired
    ImagesService imagesService;

    @ApiOperation(value="Post a Property",response = PropertyRent.class)
    @PostMapping("/v2/residential")
    @Consumes({MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PropertyRent> saveResidentialPropertyForRentv2(@RequestPart("property_for_rent") String jsonProperty,
                                                                         @RequestPart("files") List<MultipartFile> files)
    {
        PropertyRent property_for_rent = propertyRentService.getJson(jsonProperty,files);
        PropertyRent data = propertyRentService.saveResidentialPropertyForRentv2(property_for_rent);
        return ResponseEntity.status(HttpStatus.OK)
                .body(data);

    }


    //Get all the details of the property including the owner Details
    @GetMapping("/v1/detail/{id}")
    public ResponseEntity<PropertyRentDTO> propertyRentDetails(@PathVariable("id") UUID property_id){

        PropertyRentDTO propertyRentDTO = propertyRentService.findPropertyForRentById(property_id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(propertyRentDTO);

    }



    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<String> deleteResidentialPropertyForRent(@PathVariable("id") UUID property_id){

        Boolean value= propertyRentService.deletePropertyForRentById(property_id);
        if(value){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body("Deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not Delete as Property is Not Available");
        }

    }

    @PutMapping
    public ResponseEntity<String> updateResidentialPropertyForRent(@PathVariable("id") UUID property_id){

        return null;
    }


    /**
     * ----------------------------BELOW DEPRECATED ENDPOINTS---------------------
     */


    /**
     * Deprecated EndPoint use v2
     * @param property_for_rent
     * @return
     */
    @Deprecated
    @PostMapping("/v1/residential")
    @Consumes({MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PropertyRent> saveResidentialPropertyForRentv1(@RequestBody PropertyRent property_for_rent){
        return new ResponseEntity<PropertyRent>(propertyRentService.saveResidentialPropertyForRent(property_for_rent),
                HttpStatus.CREATED);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("file")MultipartFile file) {

        try {
            imagesService.save(file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }


}
