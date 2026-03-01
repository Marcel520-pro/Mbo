# Quick Reference - Spring Security Setup

## Login with Jerry Account
```bash
curl -X POST http://localhost:8081/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jerry@mboacare.com",
    "password": "06jan2008"
  }'
```

## Key Credentials
- **Email**: jerry@mboacare.com
- **Password**: 06jan2008
- **Role**: PATIENT

## Protected Endpoints

### PATIENT Endpoints (requires PATIENT or ADMIN role)
- `GET /ap1/v1/profil-utilisateur/get_patient_by_id/{id}`
- `GET /ap1/v1/profil-utilisateur/liste_patients`
- `PATCH /ap1/v1/profil-utilisateur/modify_patient/{id}`

### MEDECIN Endpoints (requires MEDECIN or ADMIN role)
- `GET /ap1/v1/profil-utilisateur/get_medecin_by_id/{id}`
- `GET /ap1/v1/profil-utilisateur/liste_medecins`
- `PATCH /ap1/v1/profil-utilisateur/modify_medecin/{id}`

### ADMIN Endpoints (requires ADMIN role)
- `DELETE /ap1/v1/profil-utilisateur/delete_profile`

## Public Endpoints
- `POST /ap1/v1/profil-utilisateur/create_patient`
- `POST /ap1/v1/profil-utilisateur/create_medecin`
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/logout`
- `GET /api/v1/auth/health`
- `GET /swagger-ui.html`

## Start Application
```bash
java -jar target/stockini_g2_2025-0.0.1-SNAPSHOT.jar --server.port=8081
```

## Features
✅ Form-based login/logout
✅ Role-based access control
✅ BCrypt password encryption
✅ REST API authentication
✅ Custom user details service
✅ Database-backed user store
✅ Automatic user initialization
