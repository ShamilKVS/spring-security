# spring-security

This project demonstrates the implementation of authentication in a Spring Security application using Basic Authentication and JWT Token Authentication.

## Authentication

This project uses Spring Security to handle authentication. Two authentication mechanisms are implemented:

### 1. Basic Authentication

Basic Authentication is a simple authentication method where the username and password are sent in the HTTP request headers. This project uses Basic Authentication for secure communication.

### 2. JWT Token Authentication

JSON Web Token (JWT) Authentication is a stateless authentication method using JSON-encoded tokens. This project utilizes JWTs for securing and validating user authentication.

## Usage

To access protected resources, you may need to include authentication details in your requests.

### Basic Authentication Example (cURL):

```bash
curl -u username:password https://example.com/api/resource
