# Spring Security Configuration Summary - Mboa_Care

## Overview
Spring Security has been successfully configured for the Mboa_Care application with user authentication and role-based access control.

## Configuration Details

### 1. **Security Configuration** ([SecurityConfig.java](src/main/java/org/logonedigital/mboa_care/SecurityConfig.java))
- **Password Encoder**: BCryptPasswordEncoder for secure password hashing
- **Authentication Manager**: Configured with UserDetailsService and PasswordEncoder
- **Authorization Rules**:
  - **Public Endpoints** (permitAll):
    - `/login`, `/register`, `/`, `/health`
    - `/swagger-ui.html`, `/swagger-ui/**`, `/v3/api-docs/**`
    - `/api/v1/auth/**` - All authentication endpoints
    - `/ap1/v1/profil-utilisateur/create_patient`
    - `/ap1/v1/profil-utilisateur/create_medecin`

  - **PATIENT Role** (hasAnyRole("PATIENT", "ADMIN")):
    - `GET /ap1/v1/profil-utilisateur/get_patient_by_id/**`
    - `GET /ap1/v1/profil-utilisateur/liste_patients`
    - `PATCH /ap1/v1/profil-utilisateur/modify_patient/**`

  - **MEDECIN Role** (hasAnyRole("MEDECIN", "ADMIN")):
    - `GET /ap1/v1/profil-utilisateur/get_medecin_by_id/**`
    - `GET /ap1/v1/profil-utilisateur/liste_medecins`
    - `PATCH /ap1/v1/profil-utilisateur/modify_medecin/**`

  - **ADMIN Role** (hasRole("ADMIN")):
    - `DELETE /ap1/v1/profil-utilisateur/delete_profile`

### 2. **Custom User Details Service** ([CustomUserDetailsService.java](src/main/java/org/logonedigital/mboa_care/security/CustomUserDetailsService.java))
- Implements `UserDetailsService` interface
- Loads user details from the database by email
- Maps user roles to Spring Security authorities (ROLE_MEDECIN, ROLE_PATIENT)

### 3. **Security Initializer** ([SecurityInitializer.java](src/main/java/org/logonedigital/mboa_care/security/SecurityInitializer.java))
- Creates a default user on application startup
- **Default Credentials**:
  - **Username (Email)**: `jerry@mboacare.com`
  - **Password**: `06jan2008`
  - **Role**: PATIENT
  - **Location**: Douala, Akwa

### 4. **Authentication Controller** ([AuthController.java](src/main/java/org/logonedigital/mboa_care/controllers/AuthController.java))
- **Endpoints**:
  - `POST /api/v1/auth/login` - User login
  - `POST /api/v1/auth/logout` - User logout
  - `GET /api/v1/auth/health` - Server health check

### 5. **DTOs for Authentication**
- **LoginDto** ([LoginDto.java](src/main/java/org/logonedigital/mboa_care/dto/LoginDto.java)):
  - `email`: String (Email format validation)
  - `password`: String (Required)

- **LoginResponseDto** ([LoginResponseDto.java](src/main/java/org/logonedigital/mboa_care/dto/LoginResponseDto.java)):
  - `message`: String
  - `username`: String
  - `roles`: List<String>

## Database Updates
- Added `findByEmail()` method to [ProfilRepo.java](src/main/java/org/logonedigital/mboa_care/repository/ProfilRepo.java)

## Entity Updates
- Added `@EqualsAndHashCode(callSuper = true)` to [Patient.java](src/main/java/org/logonedigital/mboa_care/entity/Patient.java) and [Medecin.java](src/main/java/org/logonedigital/mboa_care/entity/Medecin.java) to fix Lombok warnings

## Testing Credentials
```
Email: jerry@mboacare.com
Password: 06jan2008
Role: PATIENT
Location: Douala, Akwa
```

## API Testing
### Login
```bash
curl -X POST http://localhost:8081/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"jerry@mboacare.com","password":"06jan2008"}'
```

**Response (Success)**:
```json
{
  "message": "Login successful",
  "username": "jerry@mboacare.com",
  "roles": ["ROLE_PATIENT"]
}
```

### Health Check
```bash
curl -X GET http://localhost:8081/api/v1/auth/health
```

## Build & Run
```bash
# Clean build
mvn clean package -DskipTests

# Run the application
java -jar target/stockini_g2_2025-0.0.1-SNAPSHOT.jar --server.port=8081
```

## Notes
- CSRF protection is disabled for API endpoints (can be enabled for web forms if needed)
- Form login and logout are configured
- All passwords are encrypted using BCrypt
- Role-based access control is fully implemented
- The default user 'jerry' is automatically created on first startup if it doesn't exist

## Resolved Issues
✅ Spring Security authentication configured
✅ Role-based authorization implemented
✅ Default user (jerry/06jan2008) created automatically
✅ Custom UserDetailsService integrated
✅ Authentication API endpoints created
✅ All compilation warnings fixed
✅ Application successfully running on port 8081
