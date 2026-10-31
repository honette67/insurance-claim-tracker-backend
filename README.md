# Insurance Claim Tracker – Backend

| | |
|---|---|
| **Student names** | IGIRANEZA Honette |
| **Student ID** | 27707 |
| **Project name** | Insurance Claim Tracker |
| **Course** | INSY 8322 Web Technology and Internet, Mid-Term Examination |
| **Lecturer** | Patrick DUSHIMIMANA |
| **University** | Adventist University of Central Africa (AUCA) |

## About the project

Insurance Claim Tracker is a REST API that follows every claim an insurance company
receives from its policyholders. A policyholder files a claim that is handled by a claims
adjuster working from one branch, and each adjuster is qualified for several claim types
(for example motor or health). A claim moves from `FILED` to `UNDER_ASSESSMENT` and ends
`SETTLED` or `REJECTED`. Once a claim is settled, the adjuster records the payouts made to
the policyholder.

## Tech stack

- Java 21
- Spring Boot 4.1.1 (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven (Maven Wrapper included, no separate Maven install needed)

## Entities

`ClaimType`, `Branch`, `Adjuster`, `Policyholder`, `Claim`, `Payout`
and the enum `ClaimStatus` (`FILED`, `UNDER_ASSESSMENT`, `SETTLED`, `REJECTED`).

## Steps to run the API

1. **Install** Java 21 (or newer) and PostgreSQL.

2. **Create the database** (in pgAdmin or `psql`):
   ```sql
   CREATE DATABASE insurance_claim_tracker;
   ```

3. **Clone the repository:**
   ```bash
   git clone https://github.com/honette67/insurance-claim-tracker-backend.git
   cd insurance-claim-tracker-backend
   ```

4. **Create the configuration file.** Copy
   `src/main/resources/application.properties.example` to
   `src/main/resources/application.properties` and put your own PostgreSQL password in it:
   ```properties
   spring.application.name=claim_tracker
   spring.datasource.url=jdbc:postgresql://localhost:5432/insurance_claim_tracker
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   server.port=8084
   ```

5. **Run the application:**
   - Windows: `mvnw.cmd spring-boot:run`
   - Mac / Linux: `./mvnw spring-boot:run`

6. Wait for the line `Started ClaimTrackerApplication`. The API is now available at
   **http://localhost:8084**. The tables are created automatically
   (`spring.jpa.hibernate.ddl-auto=update`).

7. **Test in Postman.** Import `insurance-claim-tracker-midterm.postman_collection.json`
   (it is in the root of this repository). It has one request per requirement, named by its
   ID (A2, B1, C4, D2, ...).

8. **Enter the seed data** through the `/save` endpoints, in this order:
   claim types (Motor, Health, Property, Marine) → branches (1 to 4) → adjusters →
   policyholders → claims. Enter all claims before using the business rules of Part D, because
   they reject past dates and force new claims to `FILED`.

## Endpoints

| Part | Endpoint |
|---|---|
| A: CRUD | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` where `{path}` is `claim-types`, `branches`, `adjusters`, `policyholders` or `claims` |
| B1 | `GET /api/policyholders/by-last-name?lastName=` |
| B2 | `GET /api/claims/by-status?status=` |
| B3 | `GET /api/claims/between?start=&end=` |
| C1 | `GET /api/adjusters/by-claim-type?name=` |
| C2 | `GET /api/branches/free` |
| C3 | `GET /api/claim-types/unused` |
| C4 | `GET /api/policyholders/of-adjuster/{adjusterId}` |
| D1 | `POST /api/claims/save` (business rules) |
| D2 | `PATCH /api/claims/{id}/status?status=` |
| D3 | `PUT /api/adjusters/{adjusterId}/claim-types/{claimTypeId}` |
| D4 | `PATCH /api/adjusters/{adjusterId}/branch?branchNumber=` |
| E1 | `POST /api/payouts/save?claimId=` |
| E2 | `GET /api/payouts/policyholder/{policyNumber}` |
| E3 | `DELETE /api/payouts/delete/{id}` and `DELETE /api/claims/delete/{id}` |
| F1 | `GET /api/claims/stats/by-status` |
| F2 | `PATCH /api/claims/cancel-day?adjusterId=&date=` |
| Bonus | `GET /api/dashboard/summary` and `GET /api/claims/page?page=&size=&sort=` |

## Progress

| Part | Content | Status |
|---|---|---|
| A | Domain model and CRUD | Done |
| B | Derived queries | Done |
| C | JPQL with joins | Done |
| D | Business rules | Done |
| E | Payout entity | Done |
| F | Reports and bulk updates | Done |
| Bonus | Dashboard and paged claims | Done |