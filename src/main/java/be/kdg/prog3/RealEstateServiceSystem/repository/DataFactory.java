package be.kdg.prog3.RealEstateServiceSystem.repository;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.*;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;


@Component
@Data
@Profile("default")
public class DataFactory {

    private Logger logger = LoggerFactory.getLogger(DataFactory.class);


    private final List<Agent> agents;
    private final List<Property> properties;
    private final List<RealEstateAgency> agencies;
    private final List<Owner> owners;





    @PostConstruct
    public void seed() {
        agents.add(new Agent(UUID.randomUUID(), "Kingsley", "+32465787555", "3A3-NWA", "kingley.ahams@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Lesley", "+32843992200", "AAA-ZE5", "Lesley.Tianga@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Fortune", "+3274399307", "YUB-456", "Fortune@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Aisosa", "+3245676788", "ZES-2RE", "Aisosa@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Maimuna", "+32466789704", "RXE-BE1", "Maimuna@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Alex","+32478927929", "BID-D16", "Alex@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Elijah", "+32499876543", "LOP-11X", "Elijah@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Sophia", "+32456712345", "MNO-45Z", "Sophia@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Olivia", "+32465873901", "QRS-88W", "Olivia@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Ethan", "+32467238945", "TUV-34R", "Ethan@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Liam", "+32489123456", "WXY-67P", "Liam@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Charlotte", "+32456789012", "ZAB-12Y", "Charlotte@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Amara", "+32467812349", "DEF-56T", "Amara@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "Nathan", "+32456987654", "GHI-78M", "Nathan@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Emma", "+32468754321", "JKL-99N", "Emma@gmail.com"));
        agents.add(new Agent(UUID.randomUUID(), "James", "+32467984567", "PQR-44C", "James@student.kdg.be"));
        agents.add(new Agent(UUID.randomUUID(), "Abir", "+32467890123", "STU-56V", "Abir@student.kdg.be"));

        properties.add(new Property(UUID.randomUUID(), "Modern Haven", "/images/properties/Modern Haven.jpg", "Nationalstraat 1 box 2", 1400000.0, PropertyType.RESIDENTIAL, 3000.0, PropertyStatus.SOLD, 18, LocalDate.of(2024, 4, 19)));
        properties.add(new Property(UUID.randomUUID(), "Cityview Residence", "/images/properties/Cityview Residence.jpg", "Steynstraat 1 box 2", 3000000.0, PropertyType.RESIDENTIAL, 7000.0, PropertyStatus.AVAILABLE, 10, LocalDate.of(2025, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "Business Plaza", "/images/properties/Business Plaza.jpg", "Bolivarplaats 1 box 2", 15000000.0, PropertyType.COMMERCIAL, 8000.0, PropertyStatus.RENTED, 25, LocalDate.of(2024, 6, 28)));
        properties.add(new Property(UUID.randomUUID(), "Suburban Retreat", "/images/properties/Suburban Retreat.jpg", "Boukunststraat 1 box 2", 1400000.0, PropertyType.RESIDENTIAL, 2000.0, PropertyStatus.LEASED, 8, LocalDate.of(2024, 7, 12)));
        properties.add(new Property(UUID.randomUUID(), "Weekend Escape", "/images/properties/Weekend Escape.jpg", "Ruggeveld 1 box 2", 1400000.0, PropertyType.RECREATIONAL, 2000.0, PropertyStatus.AVAILABLE, 8, LocalDate.of(2025, 1, 4)));
        properties.add(new Property(UUID.randomUUID(), "Greenfield Factory", "/images/properties/Greenfield Factory.jpg", "Groenplats 3 box 5", 6000000.0, PropertyType.INDUSTRIAL, 12000.0, PropertyStatus.AVAILABLE, 40, LocalDate.of(2022, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "Vacation Cabin", "/images/properties/Cabin.jpg", "flyhigh 3 block 12", 450000.0, PropertyType.RESIDENTIAL, 4000.0, PropertyStatus.AVAILABLE, 4, LocalDate.of(2022, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "The View", "/images/properties/Penthouse.jpg", "No 19 Stevens lane", 10000000.0, PropertyType.COMMERCIAL, 20000.0, PropertyStatus.AVAILABLE, 30, LocalDate.of(2024, 12, 1)));
        properties.add(new Property(UUID.randomUUID(), "Relaxation Haven", "/images/properties/Suite.jpg", "Groenplats 10 box 9", 2500000.0, PropertyType.RESIDENTIAL, 7000.0, PropertyStatus.AVAILABLE, 7, LocalDate.of(2024, 11, 11)));
        properties.add(new Property(UUID.randomUUID(), "Prime Villa", "/images/properties/Apartment.jpg", "NO 12 Main street", 5000000.0, PropertyType.RECREATIONAL, 12000.0, PropertyStatus.AVAILABLE, 8, LocalDate.of(2024, 12, 25)));
        properties.add(new Property(UUID.randomUUID(), "Sunny Haven", "/images/properties/Bungalow.jpg", "N0 3 third street", 120000.0, PropertyType.RECREATIONAL, 1500, PropertyStatus.AVAILABLE, 2, LocalDate.of(2024, 12, 27)));
        properties.add(new Property(UUID.randomUUID(), "Aqua Oasis", "/images/properties/Aqua Oasis.jpg", "No 35 Main Street", 3500000.0, PropertyType.INDUSTRIAL, 4500.0, PropertyStatus.AVAILABLE, 5, LocalDate.of(2024, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "Forest Edge Retreat", "/images/properties/Forest Retreat.jpg", "No 3 Cresent close off road", 2000000.0, PropertyType.RECREATIONAL, 4000.0, PropertyStatus.AVAILABLE, 4, LocalDate.of(2024, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "Lake Villa", "/images/properties/Lake Villa.jpg", " 12 Handel Second Street", 3000000.0, PropertyType.COMMERCIAL, 6000.0, PropertyStatus.AVAILABLE, 5, LocalDate.of(2024, 1, 1)));
        properties.add(new Property(UUID.randomUUID(), "Apartment Residence", "/images/properties/Apartment2.jpg", "Groenplats 3 box 3", 200000.0, PropertyType.RESIDENTIAL, 1000.0, PropertyStatus.AVAILABLE, 3, LocalDate.of(2024, 11, 1)));





        agencies.add(new RealEstateAgency(UUID.randomUUID(), "Premium Realty", "789 High Street", "premium@realty.com","Atlanta","/images/agencies/Premium Realty.jpg"));
        agencies.add(new RealEstateAgency(UUID.randomUUID(), "Budget Homes", "555 Low Street", "budget@homes.com","New York","/images/agencies/Budget Homes.jpg"));
        agencies.add(new RealEstateAgency(UUID.randomUUID(), "City plus", "777 Middle Street", "cityplus@plus.com","London","/images/agencies/City Plus.jpg"));
        agencies.add(new RealEstateAgency(UUID.randomUUID(), "Immovlan", "888 Main Street", "immovlan@web.com","Brussels","/images/agencies/Immovlan.jpg"));
        agencies.add(new RealEstateAgency(UUID.randomUUID(), "Lands and Homes", "999 Nationalstraat", "LandandHome@web.com","Antwerp","/images/agencies/Land And Home.jpg"));

        owners.add(new Owner(UUID.randomUUID(), "John Doe", "123-456-7890", "johndoe@example.com", "123 Elm Street"));
        owners.add(new Owner(UUID.randomUUID(), "Jane Smith", "987-654-3210", "janesmith@example.com", "456 Oak Avenue"));

        establishConnections();

    }

    public void establishConnections() {
        Random random = new Random();

        for (Agent agent : agents) {
            int numProperties = random.nextInt(properties.size()) + 1;
            for (int i = 0; i < numProperties; i++) {
                Property randomProperty = properties.get(random.nextInt(properties.size()));
                agent.addProperty(randomProperty); // Uses the addProperty method in Agent
            }
        }

        // Associate agents with agencies
        for (RealEstateAgency agency : agencies) {
            int agentNumber = random.nextInt(agents.size()) + 1;
            for (int i = 0; i < agentNumber; i++) {
                Agent randomAgent = agents.get(random.nextInt(agents.size()));
                agency.addAgent(randomAgent); // Uses the addAgent method in RealEstateAgency
            }
        }


        for (Property property : properties) {
            if (property.getStatus() == PropertyStatus.SOLD) {
                Owner randomOwner = owners.get(random.nextInt(owners.size()));
                property.setOwner(randomOwner);
                randomOwner.addPurchasedProperty(property); // maintain the bidirectional relationship
            }
        }


    }

}
