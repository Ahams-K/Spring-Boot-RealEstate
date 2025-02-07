package be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.*;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RealEstateAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID agencyID;
    private String agencyName;
    private String address;
    private String contactInfo;
    private String city;
    private String image;
    // Method to retrieve all agents
    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Agent> agents = new HashSet<>();

    public RealEstateAgency(UUID agencyID, String agencyName, String address, String contactInfo, String city, String image) {
        this.agencyID = agencyID;
        this.agencyName = agencyName;
        this.address = address;
        this.contactInfo = contactInfo;
        this.city = city;
        this.image = image;
    }

    public RealEstateAgency() {

    }

    public void addAgent(Agent agent) {
        if (!agents.contains(agent)) {
            agents.add(agent);
            agent.setAgency(this); // maintain bidirectional relationship
        }
    }


    @Override
    public String toString() {
        return "REAL ESTATE AGENCY" + "\n" +
                "Agency ID: " + agencyID + "\n" +
                "Agency Name: " + agencyName + "\n" +
                "Address: " + address + "\n" +
                "Contact Info: " + contactInfo + "\n" +
                "City: " + city;
    }
}
