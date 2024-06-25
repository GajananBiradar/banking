package com.houseclay.zebra.service;


import com.houseclay.zebra.dto.OwnerLeadDTO;
import com.houseclay.zebra.model.lead.LeadOwner;

import java.util.Optional;
import java.util.UUID;

public interface OwnerLeadService {

    public String addOwnerLead(OwnerLeadDTO ownerLeadDTO, String token);

    public String deleteById(UUID uuid);

    public Optional<LeadOwner> fetchOwnerLeadById(UUID uuid);
}
