package com.houseclay.zebra.service.impl;


import com.auth0.jwt.JWT;
//import com.houseclay.zebra.dto.NewLeadTenantDTO;
import com.houseclay.zebra.dto.TenantLeadDTO;
import com.houseclay.zebra.model.User;
import com.houseclay.zebra.model.common.BaseTimeStamp;
import com.houseclay.zebra.model.lead.Lead;
import com.houseclay.zebra.model.lead.LeadTenant;
import com.houseclay.zebra.model.lead.enums.LeadSource;
import com.houseclay.zebra.model.lead.enums.LeadStatus;
import com.houseclay.zebra.model.lead.enums.PropertyType;
import com.houseclay.zebra.repository.TenantLeadRepository;
import com.houseclay.zebra.service.TenantLeadService;
import com.houseclay.zebra.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.Instant;
import java.util.*;

import static com.houseclay.zebra.model.lead.enums.LeadStatus.NEW;
import static com.houseclay.zebra.model.lead.enums.PropertyType.GATED_APARTMENT;
@Service
public class TenantLeadServiceImpl implements TenantLeadService {

    @Autowired
    TenantLeadRepository tenantLeadRepository;

    @Autowired
    ObjectMapperUtils objectMapperUtils;
    @Override
    public Page<LeadTenant> findAllLeads(Pageable pageable) {
        return tenantLeadRepository.findAll(pageable);
    }

    public LeadTenant addTenantLead(TenantLeadDTO newLeadTenantDTO) {
        System.out.println(this.newLeadDTOMapper(newLeadTenantDTO));
        return tenantLeadRepository.save(this.newLeadDTOMapper(newLeadTenantDTO));
    }

    /**
     * This method is for fetching Tenant Lead By UUID
     * @param uuid
     * @return
     */
    public TenantLeadDTO findTenantLeadById(UUID uuid) {
        Optional<LeadTenant> newLeadTenantByID= tenantLeadRepository.findById(uuid);
        TenantLeadDTO newLeadTenantDTO;
        try{
            LeadTenant newLeadTenant = newLeadTenantByID.get();
            newLeadTenantDTO= mapToNewLeadTenantDTO(newLeadTenant);
        }
        catch (Exception e){
            throw new NullPointerException("The Property is Not Present");
        }
        return newLeadTenantDTO;
    }


    /**
     * This method is for Updating Tenant Lead data
     * @param newLeadTenantDTO, uuid
     * @return
     */
//    @Override
    public TenantLeadDTO updateTenantLeadById(UUID uuid, TenantLeadDTO newLeadTenantDTO) {
        Optional<LeadTenant> newLeadTenantByID= tenantLeadRepository.findById(uuid);
        TenantLeadDTO newLeadTenantdto;

        try{
            LeadTenant  newLeadTenant = newLeadTenantByID.get();
            BaseTimeStamp existingBaseTimeStamp = newLeadTenant.getLead().getBaseTimeStamp();

            newLeadTenant.setLead(newLeadTenantDTO.getLead());

            newLeadTenant.getLead().getBaseTimeStamp().setCreated_by(existingBaseTimeStamp.getCreated_by());
            newLeadTenant.getLead().getBaseTimeStamp().setCreated_on(existingBaseTimeStamp.getCreated_on());

            newLeadTenant.getLead().getBaseTimeStamp().setChanged_by("Ankit");
            newLeadTenant.getLead().getBaseTimeStamp().setChanged_on(Date.from(Instant.now()));

            newLeadTenant.setMinBudget(newLeadTenantDTO.getMinBudget());
            newLeadTenant.setMaxBudget(newLeadTenantDTO.getMaxBudget());
            newLeadTenant.setLeadStatus(String.valueOf(newLeadTenantDTO.getLeadStatus()));
            newLeadTenant.setPropertyType(newLeadTenantDTO.getPropertyType());
            newLeadTenant.setPreferredLocations(objectMapperUtils.extractList(newLeadTenantDTO.getPreferredLocations()));
            newLeadTenant.setAssetConfigurations(objectMapperUtils.extractList(newLeadTenantDTO.getAssetConfigurations()));
            newLeadTenant.setOccupancyDate(newLeadTenantDTO.getOccupancyDate());
            newLeadTenant.setIsDateFlexible(newLeadTenantDTO.getIsDateFlexible());
            newLeadTenant.setTenantType(newLeadTenantDTO.getTenantType());
            newLeadTenant.setIsVegetarian(newLeadTenantDTO.getIsVegetarian());
            newLeadTenant.setIsHavingPets(newLeadTenantDTO.getIsHavingPets());
            newLeadTenant.setIsLookingForARoom(newLeadTenantDTO.getIsLookingForARoom());
            LeadTenant newLeadTen = this.tenantLeadRepository.save(newLeadTenant);
            newLeadTenantdto= mapToNewLeadTenantDTO(newLeadTen);
        }
        catch (Exception e){
            throw new NullPointerException("The Property is Not Present " );
        }
        return newLeadTenantdto;
    }

//    @Override
    public TenantLeadDTO trashTenantLeadById(UUID uuid, TenantLeadDTO newLeadTenantDTO) {
        Optional<LeadTenant> newLeadTenantByID= tenantLeadRepository.findById(uuid);
        TenantLeadDTO newLeadTenantD;
        try{
            LeadTenant newLeadTenant = newLeadTenantByID.get();
            newLeadTenant.getLead().setIsLeadTrashed(true);
            newLeadTenant.getLead().setTrashedReason(newLeadTenantDTO.getLead().getTrashedReason());
            LeadTenant newLeadTen = this.tenantLeadRepository.save(newLeadTenant);
            newLeadTenantD= mapToNewLeadTenantDTO(newLeadTen);
        }
        catch (Exception e){
            throw new NullPointerException("The Property is Not Present");
        }
        return newLeadTenantD;
    }

//    @Override
    public TenantLeadDTO untrashTenantLeadById(UUID uuid) {
        Optional<LeadTenant> newLeadTenantByID= tenantLeadRepository.findById(uuid);
        TenantLeadDTO newLeadTenantDTO;
        try{
            LeadTenant newLeadTenant = newLeadTenantByID.get();
            newLeadTenant.getLead().setIsLeadTrashed(false);
            newLeadTenant.getLead().setTrashedReason(null);
            LeadTenant newLeadTen = this.tenantLeadRepository.save(newLeadTenant);
            newLeadTenantDTO= mapToNewLeadTenantDTO(newLeadTen);
        }
        catch (Exception e){
            throw new NullPointerException("The Property is Not Present");
        }
        return newLeadTenantDTO;
    }

    private TenantLeadDTO mapToNewLeadTenantDTO(LeadTenant leadTenant)
    {
        return TenantLeadDTO.builder()
                .lead(Lead.builder()
                        .firstName(leadTenant.getLead().getFirstName())
                        .lastName(leadTenant.getLead().getLastName())
                        .emailId(leadTenant.getLead().getEmailId())
                        .contactNumber(leadTenant.getLead().getContactNumber())
                        .isEmailVerified(leadTenant.getLead().getIsEmailVerified())
                        .isPhoneVerified(leadTenant.getLead().getIsPhoneVerified())
                        .notes(leadTenant.getLead().getNotes())
                        .isLeadConverted(leadTenant.getLead().getIsLeadConverted())
                        .leadType(leadTenant.getLead().getLeadType())
                        .isLeadTrashed(leadTenant.getLead().getIsLeadTrashed())
                        .trashedReason(leadTenant.getLead().getTrashedReason())
                        .leadSource(leadTenant.getLead().getLeadSource())
                        .baseTimeStamp(BaseTimeStamp.builder()
                                .created_by(leadTenant.getLead().getBaseTimeStamp().getCreated_by())
                                .created_on(leadTenant.getLead().getBaseTimeStamp().getCreated_on())
                                .changed_by(leadTenant.getLead().getBaseTimeStamp().getChanged_by())
                                .changed_on(Date.from(Instant.now()))
                                .build())
                        .build())
                .minBudget(leadTenant.getMinBudget())
                .maxBudget(leadTenant.getMaxBudget())
                .propertyType(leadTenant.getPropertyType())
                .leadStatus(LeadStatus.valueOf(leadTenant.getLeadStatus()))
                .preferredLocations(new ArrayList<>(Arrays.asList(leadTenant.getPreferredLocations().split("-"))))
                .assetConfigurations(new ArrayList<>(Arrays.asList(leadTenant.getAssetConfigurations().split("-"))))
                .occupancyDate(leadTenant.getOccupancyDate())
                .isDateFlexible(leadTenant.getIsDateFlexible())
                .tenantType(leadTenant.getTenantType())
                .isVegetarian(leadTenant.getIsVegetarian())
                .isHavingPets(leadTenant.getIsHavingPets())
                .isLookingForARoom(leadTenant.getIsLookingForARoom())
                .build();
    }


    private LeadTenant newLeadDTOMapper(TenantLeadDTO newLeadTenantDTO) {
        return LeadTenant.builder().
                lead(Lead.builder().firstName(newLeadTenantDTO.getLead().getFirstName())
                        .lastName(newLeadTenantDTO.getLead().getLastName())
                        .emailId(newLeadTenantDTO.getLead().getEmailId())
                        .contactNumber(newLeadTenantDTO.getLead().getContactNumber())
                        .isEmailVerified(newLeadTenantDTO.getLead().getIsEmailVerified())
                        .isPhoneVerified(newLeadTenantDTO.getLead().getIsPhoneVerified())
                        .notes(newLeadTenantDTO.getLead().getNotes())
                        .leadType(newLeadTenantDTO.getLead().getLeadType())
                        .leadSource(newLeadTenantDTO.getLead().getLeadSource())
                        .baseTimeStamp(BaseTimeStamp.builder().created_by(newLeadTenantDTO.getLead().getBaseTimeStamp().getCreated_by()).created_on(Date.from(Instant.now()))
                                .changed_by("gajanan").changed_on(Date.from(Instant.now())) .build())
                        .build()).minBudget(newLeadTenantDTO.getMinBudget()).maxBudget(newLeadTenantDTO.getMaxBudget())
                .propertyType(newLeadTenantDTO.getPropertyType()).leadStatus(String.valueOf(LeadStatus.valueOf(NEW.label)))
                .preferredLocations(objectMapperUtils.extractList(newLeadTenantDTO.getPreferredLocations()))
                .assetConfigurations(objectMapperUtils.extractList(newLeadTenantDTO.getAssetConfigurations()))
                .occupancyDate(newLeadTenantDTO.getOccupancyDate()).isDateFlexible(newLeadTenantDTO.getIsDateFlexible()).tenantType(newLeadTenantDTO.getTenantType())
                .isVegetarian(newLeadTenantDTO.getIsVegetarian()).isHavingPets(newLeadTenantDTO.getIsHavingPets()).isLookingForARoom(newLeadTenantDTO.getIsLookingForARoom())
                .build();
    }
}
