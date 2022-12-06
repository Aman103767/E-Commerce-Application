# E-Commerce
This is a Individual project, I have built a rest API service for an Online Shopping Application. This service provides users to register and login, view/add products, and add to cart & order.

### Database Schema
![Untitled](https://user-images.githubusercontent.com/54835356/205990063-0019a1a9-434c-4b1a-8e43-bbfd83f3aa8c.png)

# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger UI
- Maven

# Modules

- Login Module
- Cab Driver Module
- Customer Module
- Admin Module
- Trip Details Module

# Features

- Data Authentication and Validation for all the users (Admin and Customer)

- # Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:3306/edb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

# API Root Endpoint
```
https://localhost:8888/
```
```
http://localhost:8888/swagger-ui/
```
# API Reference

## Customer Requests

```http
  Customer Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/customer/create` | Create Customer |
| `PUT` | `Update` | `http://localhost:8888/customer/update` | Update Customer |
| `DELETE` | `Delete` | `http://localhost:8888/customer/delete` | Delete Customer |
| `POST` | `Book Trip` | `http://localhost:8888/customer/booktriping` | Book Trip |
| `DELETE` | `Cancel Trip` | `http://localhost:8888/customer/canceltrip` | Cancel Trip |
| `POST` | `Trip List` | `http://localhost:8888/customer/triplist` | Trip List |
| `POST` | `Generate Bill` | `http://localhost:8888/customer/generateBill` | Generate Bill |
| `GET`  |  `View All Customer` | `http://localhost:8888/customer/viewAll`| View All Customer|
| `GET`  |  `View Customer` | `http://localhost:8888/customer/viewAll`| View Customer|


## Admin Requests

```http
  Admin Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/admin/create` | Create Admin |
| `PUT` | `Update` | `http://localhost:8888/admin/update` | Update Admin |
| `DELETE` | `Delete` | `http://localhost:8888/admin/delete` | Delete Admin |
| `POST` | `Get All Trip` | `http://localhost:8888/admin/getalltrips` | Show All Trip |
| `GET` | `Get Trip By Cab` | `http://localhost:8888/admin/getalltripsbycab/{cabId}` | Get All Trip By Cab ID |



