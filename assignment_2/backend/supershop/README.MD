```markdown
# API Documentation

## ProductController

### Base Path
`/api/products`

### Endpoints

#### GET `/api/products`
- **Description:** Retrieve a list of all products.
- **Response:** `200 OK`
- **Response Example:**
```json
[
  {
    "id": 1,
    "name": "Milk",
    "category": "DAIRY",
    "price": 2.5,
    "expiryDate": "2024-07-01"
  }
]
```

#### POST `/api/products`
- **Description:** Create a new product.
- **Request Body Example:**
```json
{
  "name": "Bread",
  "category": "BAKERY",
  "price": 1.2,
  "expiryDate": "2024-07-10"
}
```
- **Response:** `200 OK`
- **Response Example:**
```json
{
  "id": 2,
  "name": "Bread",
  "category": "BAKERY",
  "price": 1.2,
  "expiryDate": "2024-07-10"
}
```

#### POST `/api/products/bulk`
- **Description:** Add multiple products.
- **Request Body Example:**
```json
[
  {
    "name": "Eggs",
    "category": "DAIRY",
    "price": 3.0,
    "expiryDate": "2024-07-05"
  },
  {
    "name": "Butter",
    "category": "DAIRY",
    "price": 2.8,
    "expiryDate": "2024-07-15"
  }
]
```
- **Response:** `200 OK`
- **Response Example:**
```json
[
  {
    "id": 3,
    "name": "Eggs",
    "category": "DAIRY",
    "price": 3.0,
    "expiryDate": "2024-07-05"
  },
  {
    "id": 4,
    "name": "Butter",
    "category": "DAIRY",
    "price": 2.8,
    "expiryDate": "2024-07-15"
  }
]
```

#### PUT `/api/products/{id}`
- **Description:** Update an existing product.
- **Path Variable:** `id` (Long) — Product ID.
- **Request Body Example:**
```json
{
  "name": "Milk",
  "category": "DAIRY",
  "price": 2.7,
  "expiryDate": "2024-07-01"
}
```
- **Response:** `200 OK`
- **Response Example:**
```json
{
  "id": 1,
  "name": "Milk",
  "category": "DAIRY",
  "price": 2.7,
  "expiryDate": "2024-07-01"
}
```

#### GET `/api/products/expiring`
- **Description:** Retrieve products that are expiring soon.
- **Response:** `200 OK`
- **Response Example:**
```json
[
  {
    "id": 5,
    "name": "Yogurt",
    "category": "DAIRY",
    "price": 1.5,
    "expiryDate": "2024-06-30"
  }
]
```

#### GET `/api/products/category-value`
- **Description:** Get total value of products by category.
- **Response:** `200 OK`
- **Response Example:**
```json
{
  "DAIRY": 10.5,
  "BAKERY": 5.2
}
```

#### GET `/api/products/category/{category}`
- **Description:** Get products by category.
- **Path Variable:** `category` (String) — Product Category.
- **Response:** `200 OK`
- **Response Example:**
```json
[
  {
    "id": 6,
    "name": "Cheese",
    "category": "DAIRY",
    "price": 4.0,
    "expiryDate": "2024-07-20"
  }
]
```

#### POST `/api/products/apply-discounts`
- **Description:** Apply expiry-based discounts to products.
- **Response:** `200 OK`

#### POST `/api/products/mark-expired`
- **Description:** Mark expired products.
- **Response:** `200 OK`
```