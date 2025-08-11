package com.piapatza.yourticketsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;

@RestController
@RequestMapping("/events")
public class EventController {

    private final String uploadDir = "src/main/resources/static/uploads";
    @Autowired
    private EventService eventService;

    @PostMapping
    public RedirectView createEvent(
            @RequestParam("eventDate") String eventDateStr,
            @RequestParam("image") MultipartFile image,
            @RequestParam("city") String city,
            @RequestParam("name") String name,
            @RequestParam("hint") String hint, Model model) {

        String imagePath = saveUploadedFile(image);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date eventDate = dateFormat.parse(eventDateStr);

            String eventId = UUID.randomUUID().toString();
            
            // Generieren des QR-Codes basierend auf der Event-ID
            String qrCodePath = uploadDir + "/" + eventId + "_QRCode.png";
            QRCodeGenerator.generateQRCode(eventId, qrCodePath, 200, 200);

            System.out.println("QR Code generated at: " + qrCodePath);

            EventEntity event = new EventEntity();
            event.setEventDate(eventDate);
            event.setCity(city);
            event.setName(name);
            event.setHint(hint);
            event.setImagePath(imagePath);
            event.setQrCodePath("/uploads/" + eventId + "_QRCode.png"); // Pfad relativ zum Static-Verzeichnis

            eventService.createEvent(event);
            return new RedirectView("/allTickets");

        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("/newLocation?error");
        }
    }

    private String saveUploadedFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);

            // Verzeichnis anlegen, falls es nicht existiert
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Normalisiere den Dateinamen und speichere die Datei
            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
            Path targetPath = uploadPath.resolve(fileName).normalize();
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // Rückgabe des Pfades relativ zum Verzeichnis
            return "/uploads/" + fileName;

        } catch (IOException ex) {
            throw new RuntimeException("Could not store the file. Error: " + ex.getMessage());
        }
    }
}
