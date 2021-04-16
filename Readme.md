# Dice Roll Simulator

Solution uses in-memory H2 database (configured in applicaton.properties). H2 Console is available at http://localhost:8080/h2-console/.

## Usage
### Req. 1 - Execute simulation
**POST** http://localhost:8080/simulation

Parameters:
* `dices`
* `sides`
* `rolls`

### Req. 2.1 - Query simulations grouped by dice numbers and dice sides
All simulations: **GET** http://localhost:8080/total

Parameters for specific simulation type:
* `dices`
* `sides`

### Req. 2.2 - Query relative distribution of single simulation type
All simulations: **GET** http://localhost:8080/relative

Parameters are mandatory:
* `dices`
* `sides`

## Dependencies
Following libraries were used:
* `spring-boot-starter-data-jpa` - Java Persistence API
* `spring-boot-starter-web` - Spring Web
* `org.springframework.boot:spring-boot-starter-hateoas` - Support for hypermedia-based REST
* `org.hibernate.validator:hibernate-validator` - Input validation

## Notes
* HAL links are not set-up properly yet.


