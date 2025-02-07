package be.kdg.prog3.RealEstateServiceSystem.presentation.dto;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Property;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyStatus;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.PropertyType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class propertyDTO {

    private UUID propertyID;
    private String propertyName;
    private String address;
    private double price;
    private PropertyType type;
    private double size;
    private PropertyStatus status;
    private int numberOfRooms;
    private LocalDate dateListed;
    private String image;

    //Relations
    private List<UUID> agentIDs;

    public propertyDTO(Property property) {
        this.propertyID = property.getPropertyID();
        this.propertyName = property.getPropertyName();
        this.address = property.getAddress();
        this.price = property.getPrice();
        this.type = property.getType();
        this.size = property.getSize();
        this.status = property.getStatus();
        this.numberOfRooms = property.getNumberOfRooms();
        this.dateListed = property.getDateListed();
        this.image = property.getImage();
        this.agentIDs = property.getAgents().stream().map(Agent::getAgentID).toList();
    }
}
