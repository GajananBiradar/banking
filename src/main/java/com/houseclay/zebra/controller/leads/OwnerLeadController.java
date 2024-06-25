package com.houseclay.zebra.controller.leads;

import com.houseclay.zebra.controller.BaseController;
import com.houseclay.zebra.dto.OwnerLeadDTO;
import com.houseclay.zebra.dto.OwnerLeadListDTO;
import com.houseclay.zebra.model.lead.LeadOwner;
import com.houseclay.zebra.service.impl.OwnerLeadServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value ="/owner-leads")
@Api(tags ="Owner Leads")
public class OwnerLeadController extends BaseController {


    @Autowired OwnerLeadServiceImpl ownerLeadService;
    /**
     * Adding an Owner Lead
     * @return
     */
    @PostMapping("/v1/register-owner-lead")
    public ResponseEntity<String> addOwnerLead(@RequestBody OwnerLeadDTO ownerLeadDTO, @RequestHeader("Authorization") String token){

        System.out.println(ownerLeadDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ownerLeadService.addOwnerLead(ownerLeadDTO,findUsernameFromHeader(token)));
    }


    @GetMapping("/v1/fetchAll")
    public ResponseEntity<List<OwnerLeadListDTO>> fetchAllOwnerLeads() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ownerLeadService.fetchAllOwnerLeads());
    }


    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<String> deleteOwnerLead(@PathVariable("id") String uuid) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ownerLeadService.deleteById(UUID.fromString(uuid)));
    }

    @GetMapping("/v1/view/{id}")
    public ResponseEntity<LeadOwner> fetchLeadById(@PathVariable("id") String uuid) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ownerLeadService.fetchOwnerLeadById(UUID.fromString(uuid)).get());
    }

}
