package com.piapatza.yourticketsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Optional<TicketEntity> getTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public void saveTicket(TicketEntity ticket) {
        ticketRepository.save(ticket);
    }

    public boolean validateTicket(UUID ticketId) {
        Optional<TicketEntity> ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isPresent()) {
            TicketEntity ticket = ticketOptional.get();
            if (!ticket.isValidated()) {
                ticket.setValidated(true);
                ticketRepository.save(ticket);
                return true;
            }
        }
        return false;
    }

    public Optional<TicketEntity> findTicket(Optional<EventEntity> event) {
        return ticketRepository.findTicketByEvent(event);
    }

    public List<TicketEntity> getUnvalidatedTickets() {
        return ticketRepository.findByValidatedFalse();
    }
    
    @Transactional
    public void deleteTicketById(UUID ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
