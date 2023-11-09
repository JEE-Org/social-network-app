# Project Architecture Documentation

## Overview

Our platform is built upon a microservices architecture, consisting of three core components: RegistrationService, MailingService, and PostService. These microservices seamlessly communicate with one another through Apache Kafka, a powerful distributed data streaming platform.

![social-net-archi](https://github.com/JEE-Org/social-network-app/assets/94399032/b3dcf7c8-92fa-4aa0-9b37-5adb4c4b768c)


## Management and Containerization

To enhance the management of our microservices, we've implemented an API Gateway, which centralizes and streamlines various functionalities. We've also containerized the microservices using Docker, providing benefits such as simplified management and enhanced portability.

Furthermore, we leverage Kubernetes pods generated via Kompose, simplifying the orchestration and management of our microservices within a containerized environment.

## CI/CD Pipeline

In our development process, we've established a robust CI/CD pipeline. This pipeline automates key stages, including building, testing, and deploying the containerized microservices to Docker Hub. This automation allows us to provide a flexible, extensible, and easily manageable platform for our users.

For more details on each component and the technical specifics, please refer to the respective documentation for RegistrationService, MailingService, and PostService.

# Communication Diagram

![com-diagram](https://github.com/JEE-Org/social-network-app/assets/94399032/ac4d7e5a-f8fb-4667-b2d9-8e7cdc55e03a)

This communication diagram illustrates the interaction between the core components of our microservices platform: RegistrationService, MailingService, and PostService. These components work together through the API Gateway and Apache Kafka to deliver seamless functionality.

## Components

- **RegistrationService**: Manages user accounts and registration processes.
- **MailingService**: Handles email delivery for various platform activities.
- **PostService**: Oversees user-generated posts and content.

## User Actions

- When a user initiates an action, such as creating an account, it is sent to the API Gateway.
- The API Gateway, using Apache Kafka, routes the action to the appropriate service.
- In the case of user registration, the action reaches RegistrationService via Kafka.
- RegistrationService processes the request, creates the account and responds via Kafka.
- The API Gateway relays the response back to the user, completing the action.

## User-Generated Posts

- Similarly, when a user creates a post, the action is transmitted to the API Gateway.
- The API Gateway forwards the request to PostService via Kafka.
- PostService handles the post creation, generates a response, and communicates it via Kafka.
- The API Gateway returns the response to the user, finalizing the post creation.

This communication diagram showcases the architectural design of our microservices platform, emphasizing its commitment to delivering a smooth and efficient user experience.

For detailed technical information about each individual microservice component, please refer to the respective documentation.
