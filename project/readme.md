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


### POST `/api/auth/login`

Authenticate user and receive JWT token.

**Request Body:**
```json
{
    "email": "shakil@gmail.com",
    "password": "1234"
}
```

**Response:**
```json
{
    "token": "JWT_TOKEN_HERE"
}
```

---

### POST `/api/auth/password/forgot?email={email}`

Request password reset link to be sent to email.

**Query Parameter:**  
- `email` (string): User's email address

---

### POST `/api/auth/password/reset?token={token}&newPassword={newPassword}`

Reset password using token received via email.

**Query Parameters:**  
- `token` (string): Reset token  
- `newPassword` (string): New password

---

## 👤 User APIs

### GET `/api/user/profile`

Get current user's profile.  
**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

---

### PUT `/api/user/profile/update`

Update user profile.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
```json
{
    "name": "Samia Rahman",
    "email": "samia@gmail.com"
}
```

---

### PUT `/api/user/password/change`

Change user password.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
```json
{
    "oldPassword": "12345",
    "newPassword": "1234"
}
```

---

## 🚌 Bus & Route APIs

### GET `/api/buses/search?route={route}&fromDate={fromDate}&toDate={toDate}`

Search available buses by route and date range.

**Query Parameters:**  
- `route` (string)  
- `fromDate` (YYYY-MM-DD)  
- `toDate` (YYYY-MM-DD)

---

### GET `/api/routes/all`

Get all available routes.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

---

### GET `/api/routes/search?name={routeName}`

Search for a route by name.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

**Query Parameter:**  
- `name` (string): Route name

---

## 🎟️ Booking APIs

### POST `/api/bookings`

Book a seat on a trip.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

**Request Body:**
```json
{
    "tripId": 4,
    "seatNumber": "A35"
}
```

---

### GET `/api/bookings/my`

Get all bookings for the current user.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`

---

## 🛠️ Admin APIs

### GET `/admin/stats`

Get system statistics (admin only).

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`
- `x-api-key: <API_KEY>`

---

### POST `/admin/trips/create`

Create a new trip.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`
- `x-api-key: <API_KEY>`

**Request Body:**
```json
{
    "busId": 1,
    "routeId": 3,
    "departureTime": "2025-07-02T10:00:00",
    "arrivalTime": "2025-07-02T18:30:00",
    "price": 650.00
}
```

---

### POST `/admin/bus/add`

Add a new bus with company and routes.

**Headers:**  
- `Authorization: Bearer <JWT_TOKEN>`
- `x-api-key: <API_KEY>`

**Request Body:**
```json
{
    "name": "Rozina 101",
    "capacity": 40,
    "company": {
        "name": "Rozina"
    },
    "routes": [
        { "name": "Dhaka-Saidpur" },
        { "name": "Dhaka-Chattogram" },
        { "name": "Dhaka-Thakurgaon" },
        { "name": "Dhaka-Dinajpur" }
    ]
}
```
