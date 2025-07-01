# 🚌 Bus Ticket Booking Management System

A Spring Boot-based backend system for managing bus ticket bookings. Built as a university project to demonstrate practical use of **Spring Security**, **Spring JDBC**, **Spring AOP**, and **JWT-based Authentication** in a layered architecture.

---

## ✅ Features

- 🔐 **Authentication & Authorization**
  - Login / Signup using JWT
  - Role-based access: `ADMIN`, `USER`
- 🚌 **Bus Management (Admin)**
  - Add buses, assign routes and companies
- 🧾 **Invoice & History**
  - View booking history with invoices (future enhancement)
- 📜 **Spring AOP Logging**
  - Logs username, timestamp & method in `application.log`
- 💾 **Database**
  - Spring JDBC for all data operations
- 📦 **Layered Architecture**
  - Controller → Service → Repository
- 📧 **Email Notification**
  - JavaMailSender for sending emails (optional)
- 🛠️ **GitHub**
  - Maintained with 25+ commits, daily commit limits respected

---

## 🔧 Tech Stack

- **Spring Boot**
- **Spring Security (JWT)**
- **Spring JDBC**
- **Spring AOP**
- **MySQL Database**
- **JavaMailSender**
- **Maven**
- **Postman (for testing)**

---

## 🔐 Authentication API

### POST `/api/auth/signup`

Registers a new user.

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "ADMIN"
}
