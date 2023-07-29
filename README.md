# REST API For E-Commerce Application
This is a Individual project, I have built a rest API service for an Online Shopping Application which can be used by customers to login into their profile, update their information and Add the product to the cart and order them. All this is over looked by the admin who can also login and update their information as well as access the data in the Admin Module. 

### Database Schema

![Untitled (1)](https://github.com/Aman103767/E-Commerce-Application/assets/54835356/ac2d2320-46fe-4017-9f03-18b81e654a5d)


# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger UI
- Maven
- JWT authentication
- Criteria Query

# Modules

- Login Module
- Customer Module
- Admin Module

# Features

- Data Authentication and Validation for all the users (Admin and Customer)

## Admin Features
- Admin can access all Order details along with specific Order details using a particular customerId.
- Admin can Add product and remove as well.


## Customer Features
- Customer can login in the application and update their information using their mobile number and password.
- Customer can add product to cart and remove the product from cart as well.
- Customer can update quantity after adding the product to the cart.
- Customer can Order the product and Cancel as Well.



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


## Login Requests
```http
   Customer And Admin Login
```
| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Login` | `http://localhost:8888/login` | Login Customer or Admin |
| `POST` | `Current User` | `http://localhost:8888//current-user` | Current User |


## Admin Requests

```http
  Admin Controller
```

| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create` | `http://localhost:8888/admin/create` | Create Admin |
| `PUT` | `Update` | `http://localhost:8888/admin/update` | Update Admin |
| `DELETE` | `Delete` | `http://localhost:8888/admin/delete` | Delete Admin |
| `GET` | `Get All Orders` | `http://localhost:8888/admin/getAllOrders` | Get All Orders |
| `GET` | `Get All Product` | `http://localhost:8888/admin/getAllProduct` | Get All Product |
| `GET` | `Get Product By Id` | `http://localhost:8888/admin/getProductById` | Get Product By Id |
| `DELETE` | `Remove Product` | `http://localhost:8888/admin/removeProduct` | Remove Product |
| `GET` | `Find All Customer` | `http://localhost:8888/admin/findAllCustomer` | Find All Customer |
| `GET` | `Find Customer By Id` | `http://localhost:8888/admin/findCustomerById` | Find Customer By Id |

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
| `GET` | `Get All Product` | `http://localhost:8888/customer/getAllProduct` | Product Admin Upload |
| `GET` | `Get All Order` | `http://localhost:8888/customer/getAllOrdersByCustomer` | Get All Order |
| `GET` | `Add review` | `http://localhost:8888/customer/review` | Add review admin |
| `GET` | `Get review` | `http://localhost:8888/customer/review` | Get review admin|


## Order Requests

```http
  Order Controller
```


| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `GET` | `Create` | `http://localhost:8888/order/orderProduct` | Order Product |
| `GET` | `Get` | `http://localhost:8888/order/getOrderById` | Get Order |

## Product Requests

```http
  Product Controller
```


| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create Product` | `http://localhost:8888/admin/createProduct` | Create Product |
| `PUT` | `Update Product` | `http://localhost:8888/admin/updateProduct` | Update Product |
| `GET` | `Get Products` | `http://localhost:8888/product/pagination` | Get Products |
| `POST` | `Post Product` | `http://localhost:8888/product/pagination` | Post Products |
| `GET` | `Add review` | `http://localhost:8888/product/review` | Add and Update Review Product |
| `GET` | `Get review` | `http://localhost:8888/product/review` | Get Review Product|
| `GET` | `Get All review` | `http://localhost:8888/product/review` | Get All Review Product|
| `GET` | `Add helpfull Count` | `http://localhost:8888/product/addReviewCount` | Get Count Helpfull Product|


## Address Requests

```http
  Address Controller
```


| Request | METHOD     |  URI | Description                |
| :-------- | :------- | :----- | :------------------------- |
| `POST` | `Create Address` | `http://localhost:8888/address/add` | Create Address |
| `GET` | `Get All Address` | `http://localhost:8888/address/getAllAddress` | Get All Address |
| `GET` | `Get Address` | `http://localhost:8888/address/getById` | Get Address |
| `Delete` | `Delete Address` | `http://localhost:8888/address/deleteById` | Delete Address |
| `GET` | `Set Default Address` | `http://localhost:8888/address/setDefault` | Set Default Address |


















