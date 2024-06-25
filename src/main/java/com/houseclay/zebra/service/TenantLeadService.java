package com.houseclay.zebra.service;


import com.houseclay.zebra.dto.PropertyRentDTO;
import com.houseclay.zebra.model.lead.LeadTenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.UUID;

public interface TenantLeadService {

    Page<LeadTenant> findAllLeads(Pageable pageable);

}
