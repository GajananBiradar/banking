package com.houseclay.zebra.controller.leads;


import com.houseclay.zebra.dto.TenantLeadDTO;
import com.houseclay.zebra.model.lead.LeadTenant;
import com.houseclay.zebra.model.rental.PropertyRent;
import com.houseclay.zebra.service.impl.TenantLeadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Api(tags = "Tenant Leads")
@RequestMapping(value = "/tenant-leads")
public class TenantLeadController {

    @Autowired
    TenantLeadServiceImpl tenantLeadService;
    //add a new tenant lead
    @PostMapping(value = "/v1/register-tenant-lead")
    public ResponseEntity<LeadTenant> addTenantLead(@RequestBody TenantLeadDTO newLeadTenantDTO, @RequestHeader("Autorization") String token){
        //User user = (User) authentication.getPrincipal();
        //leadTenant.getLead().getBaseTimeStamp().setCreated_by(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK)
                .body(tenantLeadService.addTenantLead(newLeadTenantDTO));

    }

//    //get a specific details of a lead
//    @ApiOperation(value="Get Tenant Lead by Id",response = NewLeadTenantDTO.class)
//    @GetMapping(value = "/get-lead/{id}")
//    public ResponseEntity<NewLeadTenantDTO> getTenantLead(@PathVariable("id")UUID lead_id) {
//        NewLeadTenantDTO newLeadTenantDTO=  this.tenantLeadService.findTenantLeadById(lead_id);
//        return  ResponseEntity.status(HttpStatus.OK)
//                .body(newLeadTenantDTO);
//    }

    //Get all leads with Pagegable Request
    @GetMapping(value="/get-leads")
    public Page<LeadTenant> getPagableLeads(@NotNull final Pageable pageable){
        return tenantLeadService.findAllLeads(pageable);
    }
}
