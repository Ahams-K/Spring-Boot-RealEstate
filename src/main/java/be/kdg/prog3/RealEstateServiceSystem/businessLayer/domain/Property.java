package be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain;


import com.fasterxml.jackson.databind.util.ClassUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID propertyID;
    private String propertyName;
    private String address;
    private double price;

    @Enumerated(EnumType.STRING)
    private PropertyType type;
    private double size;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;
    private int numberOfRooms;
    private LocalDate dateListed;

    @ManyToOne
    private Owner owner;
    private String image;

    @ManyToMany(mappedBy = "properties")
    private List<Agent> agents = new ArrayList<>();

    public Property(UUID propertyID, String propertyName, String image,String address, double price, PropertyType type, double size, PropertyStatus status, int numberOfRooms, LocalDate dateListed) {
        this.propertyID = propertyID;
        this.propertyName = propertyName;
        this.image = image;
        this.address = address;
        this.price = price;
        this.type = type;
        this.size = size;
        this.status = status;
        this.numberOfRooms = numberOfRooms;
        this.dateListed = dateListed;
    }

    public Property() {

    }


    public void addAgent(Agent agent) {
        if (!agents.contains(agent)) {
            agents.add(agent);
            agent.addProperty(this);
        }
    }


    @Override
    public String toString() {
        return "PROPERTY DETAILS" + "\n" +
                "Property ID: " + propertyID +"\n" +
                "Address:  " + address + "\n" +
                "Price: " + price + "\n" +
                "Property Type: " + type + "\n" +
                "Size: " + size + "\n" +
                "Status: " + status + "\n" +
                "Number Of Rooms: " + numberOfRooms + "\n" +
                "DateListed: " + dateListed + "\n";


    }
}
