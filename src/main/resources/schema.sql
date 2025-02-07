DROP TABLE IF EXISTS AgentProperty;
DROP TABLE IF EXISTS Property;
DROP TABLE IF EXISTS Agent;
DROP TABLE IF EXISTS Owner;
DROP TABLE IF EXISTS RealEstateAgency;

-- Create table for RealEstateAgency
CREATE TABLE RealEstateAgency (
                                  agency_id UUID PRIMARY KEY,
                                  agency_name VARCHAR(50) NOT NULL,
                                  address VARCHAR(100) NOT NULL,
                                  contact_info VARCHAR(25) NOT NULL,
                                  city VARCHAR(50) NOT NULL,
                                  image VARCHAR(255)
);

-- Create table for Agent
CREATE TABLE Agent (
                       agent_id UUID PRIMARY KEY,
                       agent_name VARCHAR(25) NOT NULL,
                       contact_info VARCHAR(25) NOT NULL,
                       licence_number VARCHAR(100),
                       email VARCHAR(50),
                       agency_id UUID,
                       FOREIGN KEY (agency_id) REFERENCES RealEstateAgency(agency_id)
);

-- Create table for Owner
CREATE TABLE Owner (
                       owner_id UUID PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       contact_info VARCHAR(50) NOT NULL,
                       email VARCHAR(50),
                       address VARCHAR(100)
);

-- Create table for Property
CREATE TABLE Property (
                          property_id UUID PRIMARY KEY,
                          property_name VARCHAR(255) NOT NULL,
                          address VARCHAR(100) NOT NULL,
                          price DECIMAL(14, 2) NOT NULL,
                          property_type VARCHAR(25) NOT NULL,
                          size DECIMAL(14, 2) NOT NULL,
                          property_status VARCHAR(25) NOT NULL,
                          number_of_rooms INT NOT NULL,
                          dateListed DATE NOT NULL,
                          image VARCHAR(255),
                          owner_id UUID,
                          FOREIGN KEY (owner_id) REFERENCES Owner(owner_id)
);

-- Create join table for many-to-many relationship between Agent and Property
CREATE TABLE Agent_Property (
                                agent_id UUID,
                                property_id UUID,
                                PRIMARY KEY (agent_id, property_id),
                                FOREIGN KEY (agent_id) REFERENCES Agent(agent_id),
                                FOREIGN KEY (property_id) REFERENCES Property(property_id)
);
