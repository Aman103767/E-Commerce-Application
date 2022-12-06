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
| `GET` | `Add To Cart` | `http://localhost:8888/customer/addToCart` | Add To Cart |
| `GET` | `Update Quantity` | `http://localhost:8888/customer/updatingQuantity` | Update Quantity Of Product |
| `GET` | `Get All Product` | `http://localhost:8888/customer/getAllProductAddedInCart` | Get All Product Added In Cart |
| `DELETE` | `Remove Product From Cart` | `http://localhost:8888/customer/removeProductFromCart` | Remove Product From Cart |
| `DELETE` | `Remove All Product From Cart` | `http://localhost:8888/customer/removeAllProductfromCart` | Remove All Product From Cart |
| `POST` | `Order Product` | `http://localhost:8888/customer/orderProduct` | Order |
| `GET` | `Get Order By Id` | `http://localhost:8888/customer/getOrderById` | Get Order By Id |
| `DELETE` | `Cancel Order` | `http://localhost:8888/customer/cancelOrder` | Cancel Order |


## Admin Requests

```http
  Admin Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/admin/create` | Create Admin |
| `PUT` | `Update` | `http://localhost:8888/admin/update` | Update Admin |
| `DELETE` | `Delete` | `http://localhost:8888/admin/delete` | Delete Admin |

