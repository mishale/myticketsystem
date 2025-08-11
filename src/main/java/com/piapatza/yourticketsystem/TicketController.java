package com.piapatza.yourticketsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;
    

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateTicket(@RequestBody Map<String, String> request) {
        String ticketId = request.get("ticketId");
        String qrCodePath = "/uploads/"+ticketId+"_QRCode.png";
        Optional<EventEntity> eventOpt = eventService.findEvent(qrCodePath);
        Optional<TicketEntity> ticketOpt = ticketService.findTicket(eventOpt);
        Map<String, Object> response = new HashMap<>();
        if (ticketOpt.isPresent()) {
            TicketEntity ticket = ticketOpt.get();
            if (ticket.isValidated()) {
                response.put("status", "failed");
                response.put("message", "Ticket ist bereits validiert.");
            } else {
                ticket.setValidated(true);
                ticketService.saveTicket(ticket);
                response.put("status", "success");
                response.put("message", "Ticket erfolgreich validiert.");
                //response.put("redirect", "/allTickets2");
            }
        } else {
            response.put("status", "failed");
            response.put("message", "Ungültige Ticket-ID.");
        }

        return ResponseEntity.ok(response);
    }

}


