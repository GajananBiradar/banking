package com.houseclay.zebra.repository;

import com.houseclay.zebra.model.lead.LeadTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantLeadRepository extends JpaRepository<LeadTenant, UUID> {

}
