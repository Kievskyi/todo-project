# Todo REST app with Spring 6, Tomcat, MySQL + Docker

### Requirements:

---

1. Java 17
2. MySQL 8.x.x
3. Tomcat 10.x.x
4. Docker + Docker compose V2.x.x and above (optional)

### Steps to Setup:

---

1. Clone the application

> https://github.com/Kievskyi/todo-project.git

2. Edit **application.properties** and **.env** file  in its sole discretion
> .env file
>>SPRING_DATASOURCE_USERNAME=user **(Editable)** <br>
SPRING_DATASOURCE_PASSWORD=password  **(Editable)** <br>
SPRING_DATASOURCE_ROOT_PASSWORD=strongpassword  **(Editable)** <br>
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/todo **(Use this path for configuration with Tomcat)**  **(Not editable)** <br>
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/todo **(Use this path for configuration with Docker)** **(Not editable)**
> 

>application.properties
>>spring.datasource.username=user **(Editable)**  <br>
spring.datasource.password=root **(Editable)** <br>
spring.datasource.url=jdbc:mysql://localhost:3306/todo **(Use this path for configuration with Tomcat)** **(Not editable)** <br>
spring.datasource.url=jdbc:mysql://db:3306/todo **(Use this path for configuration with Docker)** **(Not editable)**

2. Chose Tomcat like a local server in Configuration

> - in Deployment use "todo-project:war exploded"
>   - Keep in Application contex just "/"
>   - Press OK

3. Alternatively, you can build and run the app by Docker from the root directory using this command from terminal

> docker-compose run

The server will start at http://localhost:8080.

### Exploring the Rest APIs

---
The application defines following REST APIs

> 1. GET /tasks/ - Get All Tasks
>
>
>2. DELETE /tasks/ - Delete Task by ModelAttribute
>
>
>3. PATCH /tasks/{id} - Update a Task











