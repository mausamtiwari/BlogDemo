# Post Management System Application

![Java](https://img.shields.io/badge/Java-F9981D?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB5D?style=for-the-badge&logo=springboot&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![BootStrap](https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)


## Overview
Welcome to the Post Management System repository! This project is a simple web application developed using Spring Boot, Java, Thymeleaf, HTML, and Bootstrap. It allows users to manage posts and comments with CRUD operations, complete with pagination for seamless browsing.

## Project Structure
The project is structured as follows:
- **src/main/java**: Contains Java source code
    - **com.example.postmanagementsystem**: Main package
        - **controller**: Handles HTTP requests
        - **model**: Defines entity classes for posts and comments
        - **repository**: Provides repository interfaces for database access
        - **service**: Contains service classes for business logic
        - **PostManagementSystemApplication.java**: Entry point for the Spring Boot application
- **src/main/resources**: Contains application properties and Thymeleaf templates
    - **templates**: Houses HTML templates using Thymeleaf
    - **application.properties**: Stores Spring Boot application configuration

## Pages
1. **new_post.html**: Form for creating a new post.
2. **edit_comment.html**: Form for editing a comment.
3. **edit_post.html**: Form for editing a post.
4. **error.html**: Error page template.
5. **index.html**: Main page displaying posts pagination.
6. **comments.html**: Page showing comments with pagination.

## Dependencies
- Spring Boot
- Thymeleaf
- Bootstrap

## Deployment Instructions
1. **Clone the repository:**
   ```bash
   git clone https://github.com/mausamtiwari/BlogDemo
