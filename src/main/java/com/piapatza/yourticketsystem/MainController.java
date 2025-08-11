package com.piapatza.yourticketsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
//import org.springframework.web.bind.annotation.RequestBody;
import java.util.stream.Collectors;


@Controller
public class MainController {

   /* @Autowired
    private UserRepository userRepository; */

    @Autowired 
    private UserService userService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;


    @Autowired 
    private EventService eventService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/qrscanner")
    public String qrScanner() {
        return "qr-scanner";
    }

@GetMapping("/allTickets")
public String allEvents(Model model) {

    /*UUID userId = UUID.fromString(principal.getName()); // Assuming principal.getName() returns the user ID

    Optional<UserEntity> userOpt = userService.findUserById(userId);

    UserEntity user = userOpt.get();*/

    List<EventEntity> events = eventService.getAllEvents();
    model.addAttribute("events", events);
    
    /*model.addAttribute("userID", user.getId());*/

    return "allTickets";
}

    @GetMapping("/allTickets2")
    public String getUnvalidatedTickets(Model model) {
        List<TicketEntity> tickets = ticketRepository.findAll();
        List<TicketEntity> unvalidatedTickets = tickets.stream()
                                                  .filter(ticket -> !ticket.isValidated())
                                                  .collect(Collectors.toList());
        model.addAttribute("tickets", unvalidatedTickets);
        return "allTickets2";
    }

    @PostMapping("/tickets/{id}")
    public String showTicket(@PathVariable UUID id, @RequestParam UUID ticketId, Model model) {
        Optional<TicketEntity> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            TicketEntity ticket = ticketOpt.get();
            model.addAttribute("ticket", ticket);
            return "ticket";
        } else {
            return "redirect:/allTickets2";
        }
    }

    @PostMapping("/allTickets2")
    public RedirectView createTicket(@RequestParam UUID eventId, Model model/* , @RequestParam UUID userID*/) {
        Optional<EventEntity> eventOpt = eventService.getEventById(eventId);
        //Optional<UserEntity> userOpt = userService.findUserById(userID);

        if (eventOpt.isPresent()) {
            EventEntity event = eventOpt.get();
            //UserEntity user = userOpt.get();

            TicketEntity ticket = new TicketEntity();
            ticket.setEvent(event);
            //ticket.setUser(user);
            ticket.setValidated(false);

            ticketRepository.save(ticket);

            return new RedirectView("/allTickets2"); 
        } else {
          
            return new RedirectView("/allTickets");
        }
    }
    @GetMapping("/newLocation")
    public String newLocation() {
        return "newLocation";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @DeleteMapping("/tickets/{id}")
        @ResponseBody
        public RedirectView deleteTicket(@PathVariable UUID id) {
            ticketService.deleteTicketById(id);
            return new RedirectView("/allTickets2");
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password) {

        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password); 

        userService.saveUser(user);

        return "redirect:/allTickets"; 
    }
}

