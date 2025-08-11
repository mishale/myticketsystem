package com.piapatza.yourticketsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findAll();
    Optional<TicketEntity> findTicketByEvent(Optional<EventEntity> event);
    List<TicketEntity> findByValidatedFalse();
    void deleteById(UUID id);
    

}

