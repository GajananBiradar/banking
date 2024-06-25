package com.houseclay.zebra.repository;

import com.houseclay.zebra.model.CommandCustomRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandCustomRunnerRepository extends JpaRepository<CommandCustomRunner, Boolean> {
}
