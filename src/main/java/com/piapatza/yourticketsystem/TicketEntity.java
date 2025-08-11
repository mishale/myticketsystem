package com.piapatza.yourticketsystem;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;*/

    @OneToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private EventEntity event;

    @Column(nullable = false)
    private boolean validated;

    // Getter und Setter hier

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /*public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }*/

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    

}

