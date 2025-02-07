# Programming3 Project

## Name
- Ahams Kingsley

## Project Description
This project is a real estate management system designed to streamline the management of properties, agents and agencies. The system provides a structured way to manage relationships between entities and facilitates the operational efficiency of real estate agencies.

## Entities
1. **Property**
    - Represents a real estate property that can be managed by agents and agencies.

2. **Agent**
    - Represents a real estate agent who manages properties and is employed by an agency.

3. **RealEstateAgency**
    - Represents a real estate agency that employs agents and oversees properties.

4. **Owner**
    - Represents the owner of a property.

## Relationships
1. **Agents - Property**: Many-to-Many
    - An agent can manage multiple properties, and a property can be managed by multiple agents.

2. **Agencies - Property**: One-to-Many
    - An agency can hire or manage many agents, and an agent is associated with one agency only.

## Explanation of Profiles
The project uses Spring Boot profiles to manage different environments and configurations:
- **JDBC**: For local development purposes, using the H2 database for in-memory testing.
- **Production**: Uses persistent database like PostgreSQL
- **Jpa**: Uses persistent database like PostgreSQL
- **default**: Uses java Collection

## Database Configuration
The project currently uses the H2 database for development. Below are the connection details:
- **Name**: gradle_project
- **User**: sa
- **Password**: password

For the SQL database for production
- **Name**: gradle_project
- **User**: postgres
- **Password**: Student_1234

To switch to a production database, modify the `application.properties` file with the appropriate database configuration.

## Start URL for the Web App
The application starts at:
- [http://localhost:8008](http://localhost:8008)

## Completed Parts
- Entity creation and relationships.
- Validations and session history
- Basic CRUD operations for properties, agents, agencies.
- Initial database setup and configuration.
- Exporting Entities to JSON format

