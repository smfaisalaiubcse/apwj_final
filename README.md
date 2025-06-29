# apwj_final

This repository contains assignments and projects for the university course **Advanced Programming with Java**.

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Assignments](#assignments)
- [Projects](#projects)
- [Contributing](#contributing)
- [License](#license)

## Overview

This repository includes various assignments and projects developed as part of the Advanced Programming with Java course. The focus is on applying advanced Java concepts such as object-oriented programming, data structures, algorithms, multithreading, networking, and GUI development.

## Project Structure

```
apwj_final/
├── assignments/
│   ├── assignment1/
│   ├── assignment2/
│   └── ...
├── projects/
│   ├── project1/
│   ├── project2/
│   └── ...
├── README.md
└── ...
```

- **assignments/**: Contains individual assignment folders.
- **projects/**: Contains larger course projects.
- **README.md**: Project documentation.

## Getting Started

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/apwj_final.git
    cd apwj_final
    ```

2. **Open in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).**

3. **Build and run the Java files as needed.**

## Assignments

### Assignment 1: Inventory Management System (Super Shop)

**Final: Assignment-1 (Legacy) [Super Shop]**  
**Due:** June 19, 2025, 11:59 PM  
**Closes:** June 21, 2025, 11:59 PM

#### Business Requirements

Develop an Inventory Management System for a super shop to manage product stock, categorization, pricing, and expiry-based discounts. Use **Spring Legacy configuration (XML-based)** for beans, services, repositories, and controllers.

#### Functional Requirements

**Product Categorization**
- Products must be categorized into types such as:
    - Beauty Care
    - Vegetables
    - Meat
    - Groceries
    - Others (optional)
- Each product will have:
    - ID
    - Name
    - Category
    - Price
    - Quantity
    - Expiry Date (if applicable)
    - Discount (optional)

**Product Management**
- Admin can add products one by one or in bulk.
- Admin can modify a product’s details individually (e.g., price, name, expiry).

**Expiry and Discount Logic**
- Show a list of all products that will expire within the next 7 days.
- For these products, apply a discount (e.g., 20%) and show the new discounted price.
- Expired products (expiry date < today) should be marked as unavailable.

**Reports**
- Generate a report showing:
    - Total price (value) of all available products grouped by category.
    - List of products by category with applied discounts (if any).

#### Technical Requirements

- Backend: Spring Framework with XML-based configuration only, exposing RESTful APIs for the above requirements.
- Frontend: Plain HTML, CSS, and JavaScript consuming the backend APIs.
- No database required for this task.

**Note:** See the `assignment1/` folder for source code, instructions, and sample input/output.


## Projects

Each project folder includes:
- Project description
- Source code
- Documentation and usage instructions

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This repository is for educational purposes. See individual files for license information if applicable.
