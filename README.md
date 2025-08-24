# Spring Boot Ticket System

This is a **backend project** built with **Java Spring Boot (MVC)**.  
It provides a complete **ticket management system** for events, including user accounts, ticket creation, validation with QR codes, and persistent storage via an **H2 in-memory database**.

---

## Features
-  **Event Management**: Add, update, and list events
-  **Ticket Management**: Generate and manage tickets for events
-  **QR Code Integration**:  
  - Tickets are assigned a **unique QR code**  
  - A **QR code scanner** can validate and mark tickets as used
-  **Database**: Uses **H2 Database** for persistence (easy to set up, web console available)

---

##  Tech Stack
- **Java**
- **Spring Boot** (MVC, REST API)
- **Spring Data JPA** – ORM for database access
- **H2 Database** – in-memory or file-based storage


## 📊 API Endpoints

### 👤 Users
- `POST /users` – create new user  
- `GET /users` – list all users  
- `GET /users/{id}` – get user by ID  

### 🎉 Events
- `POST /events` – create event  
- `GET /events` – list all events  
- `GET /events/{id}` – get event by ID  

### 🎟️ Tickets
- `POST /tickets` – create ticket for event  
- `GET /tickets` – list all tickets  
- `GET /tickets/{id}` – get ticket by ID  
- `POST /tickets/{id}/validate` – validate ticket via QR code  

---

## 📱 QR Code Validation
Each ticket gets a **unique QR code**.  
When scanned, the backend checks:
- ✅ If the ticket exists  
- ✅ If it belongs to the correct event  
- ✅ If it is already used or still valid  

---

## 🤝 Contributing
Contributions are welcome! Please fork the repo and create a pull request.

---
