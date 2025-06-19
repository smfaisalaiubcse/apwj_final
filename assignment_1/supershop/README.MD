# API Documentation

## ProductController

### Base Path
`/api/products`

### Endpoints

#### GET `/api/products`
- **Description:** Retrieve a list of all products.
- **Response:** `200 OK` — Returns a list of products.

#### GET `/api/products/{id}`
- **Description:** Retrieve a product by its ID.
- **Path Variable:** `id` (Long) — Product ID.
- **Response:** `200 OK` — Returns the product details.
- **Error:** `404 Not Found` — If product does not exist.

#### POST `/api/products`
- **Description:** Create a new product.
- **Request Body:** Product object (JSON).
- **Response:** `201 Created` — Returns the created product.

#### PUT `/api/products/{id}`
- **Description:** Update an existing product.
- **Path Variable:** `id` (Long) — Product ID.
- **Request Body:** Product object (JSON).
- **Response:** `200 OK` — Returns the updated product.
- **Error:** `404 Not Found` — If product does not exist.

#### DELETE `/api/products/{id}`
- **Description:** Delete a product by its ID.
- **Path Variable:** `id` (Long) — Product ID.
- **Response:** `204 No Content`
- **Error:** `404 Not Found` — If product does not exist.

---

## UserRoleController

### Base Path
`/api/user-roles`

### Endpoints

#### GET `/api/user-roles`
- **Description:** Retrieve a list of all user roles.
- **Response:** `200 OK` — Returns a list of user roles.

#### GET `/api/user-roles/{id}`
- **Description:** Retrieve a user role by its ID.
- **Path Variable:** `id` (Long) — User Role ID.
- **Response:** `200 OK` — Returns the user role details.
- **Error:** `404 Not Found` — If user role does not exist.

#### POST `/api/user-roles`
- **Description:** Create a new user role.
- **Request Body:** UserRole object (JSON).
- **Response:** `201 Created` — Returns the created user role.

#### PUT `/api/user-roles/{id}`
- **Description:** Update an existing user role.
- **Path Variable:** `id` (Long) — User Role ID.
- **Request Body:** UserRole object (JSON).
- **Response:** `200 OK` — Returns the updated user role.
- **Error:** `404 Not Found` — If user role does not exist.

#### DELETE `/api/user-roles/{id}`
- **Description:** Delete a user role by its ID.
- **Path Variable:** `id` (Long) — User Role ID.
- **Response:** `204 No Content`
- **Error:** `404 Not Found` — If user role does not exist.

---

**Note:** Replace object names and fields with actual model details as implemented in your project.