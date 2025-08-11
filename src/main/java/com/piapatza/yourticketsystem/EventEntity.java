package com.piapatza.yourticketsystem;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "events")

public class EventEntity {

    public EventEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    public UUID id;

    @Temporal(TemporalType.DATE)
    public Date eventDate;

    public String city;
    public String name;
    public String hint;
    public String imagePath;
    private String qrCodePath;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TicketEntity ticket;

    public String getImagePath(){
        return this.imagePath;
    }

    public void setImagePath(String imagePath){
        this.imagePath=imagePath;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getEventDate(){
        return eventDate;
    }

    public void setEventDate(Date eventDate){
        this.eventDate=eventDate;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }



}
