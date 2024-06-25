package com.houseclay.zebra.service.impl;

import com.houseclay.zebra.dto.OwnerLeadDTO;
import com.houseclay.zebra.dto.OwnerLeadListDTO;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.lead.Lead;
import com.houseclay.zebra.model.lead.LeadOwner;
import com.houseclay.zebra.model.lead.enums.*;
import com.houseclay.zebra.repository.OwnerLeadRepository;
import com.houseclay.zebra.service.OwnerLeadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OwnerLeadServiceImpl implements OwnerLeadService {

    @Autowired OwnerLeadRepository ownerLeadRepository;
    @Override
    public String addOwnerLead(OwnerLeadDTO ownerLeadDTO, String username) {
        //sanitize the dto and map with object , then save the object
        try {
            //check if same lead exists or not - combination of phone number
            Optional<LeadOwner> leadOwner = ownerLeadRepository.findByContactNumber(ownerLeadDTO.getContactNumber());
            if(leadOwner.isPresent()) {
                return "Lead already exist with same phone number";
            }
            else {
                ownerLeadRepository.save(sanitizeDTO(ownerLeadDTO, username));
                return "Owner Lead Saved Successfully";
            }
        }catch(Exception exception){
            log.error("Exception occured in saving Owner Lead", exception);
            return "Owner Lead cannot be saved";
        }

    }

    @Override
    public String deleteById(UUID uuid) {
        try
        {
            ownerLeadRepository.deleteById(uuid);
            return "Lead Deleted Successfully!";
        }catch(Exception e){
            log.error("Exception occured", e);
            return "Error in Deleting Lead";
        }

    }

    @Override
    public Optional<LeadOwner> fetchOwnerLeadById(UUID uuid) {
        try {
            return ownerLeadRepository.findById(uuid);
        } catch (EntityNotFoundException e) {
            log.error("Failed to fetch lead owner by ID: {}. Entity not found.", uuid, e);
            return Optional.empty();
        }
    }

    public List<OwnerLeadListDTO> fetchAllOwnerLeads(){
        List<LeadOwner> listofLeadOwners = ownerLeadRepository.findAll();
        return listofLeadOwners.parallelStream().map(this::sanitizeList).collect(Collectors.toList());

    }

    /**
     * Convert Database List to portion of data mentioned in OwnerLeadListDTO
     * @param leadOwner
     * @return
     */
    private OwnerLeadListDTO sanitizeList(LeadOwner leadOwner) {
        return OwnerLeadListDTO
                .builder().ownerID(leadOwner.get_owner_lead_id())
                .firstName(leadOwner.getLead().getFirstName() != null ? leadOwner.getLead().getFirstName() : "")
                .lastName(leadOwner.getLead().getLastName() != null ? leadOwner.getLead().getLastName() : "")
                .contactNumber(leadOwner.getLead().getContactNumber() != null ? leadOwner.getLead().getContactNumber() : "")
                .emailId(leadOwner.getLead().getEmailId() != null ? leadOwner.getLead().getEmailId() : "")
                .leadStatus(leadOwner.getLeadStatus())
                .locality(leadOwner.getLocality() != null ? leadOwner.getLocality() : "")
                .bhkType(leadOwner.getBhkType() != null ? leadOwner.getBhkType() : "")
                .apartmentName(leadOwner.getApartmentName() != null ? leadOwner.getApartmentName() : "")
                .expectedRent(leadOwner.getExpectedRent() != null ? leadOwner.getExpectedRent().toString() : "")
                .googleMapLocationURL(leadOwner.getGoogleMapLocationURL() != null ? leadOwner.getGoogleMapLocationURL() : "")
                .availableFrom(leadOwner.getAvailableFrom()!=null ? String.valueOf(leadOwner.getAvailableFrom()).split(" ")[0] : "")
                .clayManage(leadOwner.isClayManage())
                .build();

    }
    private LeadOwner sanitizeDTO(OwnerLeadDTO ownerLeadDTO,String username) {
        LeadOwner leadOwner = LeadOwner.builder()
                .lead(Lead.builder().
                        firstName(ownerLeadDTO.getFirstName()).lastName(ownerLeadDTO.getLastName()).contactNumber(ownerLeadDTO.getContactNumber())
                        .emailId(ownerLeadDTO.getEmailId()).leadSource(ownerLeadDTO.getLeadSource().isEmpty() ? null: LeadSource.valueOf(ownerLeadDTO.getLeadSource())).
                        isLeadConverted(false).leadType(LeadType.valueOf(ownerLeadDTO.getLeadType())).isLeadTrashed(false)
                                .isPhoneVerified(ownerLeadDTO.getIsPhoneVerified()).isEmailVerified(ownerLeadDTO.getIsEmailVerified())
                                .trashedReason("").baseTimeStamp(BaseTimeStamp.builder().created_by(username).created_on(new Date()).build()).notes(ownerLeadDTO.getNotes()).
                        build()).propertyType(PropertyType.valueOf(ownerLeadDTO.getPropertyType())).bhkType(ownerLeadDTO.getBhkType()).apartmentName(ownerLeadDTO.getApartmentName())
                        .locality(ownerLeadDTO.getLocality()).googleMapLocationURL(ownerLeadDTO.getGoogleMapLocationUrl()).leadStatus(LeadStatus.NEW)
                        .preferredTenants(PreferredTenant.valueOf(ownerLeadDTO.getPreferredTenants())).isNonVegAllowed(ownerLeadDTO.getIsNonVegAllowed()).clayManage(ownerLeadDTO.getClayManage())
                        .availableFrom(ownerLeadDTO.getAvailableFrom()).expectedRent(ownerLeadDTO.getExpectedRent().isEmpty()? null: Long.valueOf(ownerLeadDTO.getExpectedRent()))
                        .expectedDeposit(ownerLeadDTO.getExpectedDeposit().isEmpty()? null : Long.valueOf(ownerLeadDTO.getExpectedDeposit())).furnishing(ownerLeadDTO.getFurnishing()).
                        build();
        log.info("Owner Lead",leadOwner);
        return leadOwner;
    }
}
