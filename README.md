\# Insurance Claim Tracker – Backend



\*\*Student:\*\* IGIRANEZA Honette

\*\*Student ID:\*\* 27707

\*\*Project:\*\* Insurance Claim Tracker

\*\*Course:\*\* INSY 8322 Web Technology and Internet (AUCA), Mid-Term Examination

\*\*Lecturer:\*\* Patrick DUSHIMIMANA



Spring Boot REST API that tracks insurance claims. Policyholders file claims, adjusters

(assigned to a branch and qualified for several claim types) assess them, and payouts are

recorded once a claim is settled.



\## Tech stack

Java 17, Spring Boot 3, Spring Web, Spring Data JPA, PostgreSQL, Maven



\## Steps to run the API

1\. Install Java 17+, Maven and PostgreSQL.

2\. Create the database:

&#x20;  ```sql

&#x20;  CREATE DATABASE insurance\_claim\_tracker;

&#x20;  ```

3\. Copy `src/main/resources/application.properties.example` to

&#x20;  `src/main/resources/application.properties` and put your PostgreSQL password in it.

4\. Run the application:

&#x20;  ```bash

&#x20;  mvnw spring-boot:run

&#x20;  ```

5\. The API starts on `http://localhost:8084`. The tables are created automatically

&#x20;  (`spring.jpa.hibernate.ddl-auto=update`).

6\. Import `insurance-claim-tracker-midterm.postman\_collection.json` into Postman and enter

&#x20;  the seed data (claim types, branches, adjusters, policyholders, claims) through the

&#x20;  `/save` endpoints.



\## Progress

| Part | Content | Status |



| A | Domain model and CRUD    | Done |

| B | Derived queries          | Done |

| C | JPQL with joins          | Done |

| D | Business rules           | Done |

| E | Payout entity            | Done |

| F | Reports and bulk updates | Done |

|Bonus| Dashboard and Pagination |Done|

