package com.houseclay.zebra.repository;


import com.houseclay.zebra.model.lead.LeadOwner;
import com.houseclay.zebra.repository.queries.SqlQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OwnerLeadRepository extends JpaRepository<LeadOwner, UUID> {

    @Query(value = SqlQuery.OWNERLEADFINDBYCONTACTNUMBER,nativeQuery = true)
    public Optional<LeadOwner> findByContactNumber(String contactNumber);

}
