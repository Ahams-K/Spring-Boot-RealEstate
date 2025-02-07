package be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID agentID;

    private String agentName;
    private String contactInfo;
    private String licenceNumber;
    private String email;

    @ManyToOne
    private RealEstateAgency agency;

    @ManyToMany
    @JoinTable(name = "agent_property",
    joinColumns = @JoinColumn(name = "agent_id"),
    inverseJoinColumns = @JoinColumn(name = "property_id"))
    @Cascade(CascadeType.ALL)
    private List<Property> properties = new ArrayList<>(); // by the way this is for the relationship

    public Agent(UUID agentID, String agentName, String contactInfo, String licenceNumber, String email) {
        this.agentID = agentID;
        this.agentName = agentName;
        this.contactInfo = contactInfo;
        this.licenceNumber = licenceNumber;
        this.email = email;
    }

    public Agent() {

    }




    @Override
    public String toString() {
        return "AGENT DETAILS " + "\n" +
                "Agent ID: " + agentID +"\n"+
                "Agent Name: " + agentName +"\n"+
                "Contact Info: "+ contactInfo +"\n"+
                "Licence Number: " + licenceNumber +"\n"+
                "Email: " + email ;

    }

    // might still change in the future
    // A function for an agent to acquire,manage or add a property

    public void addProperty(Property property) {
        if (!properties.contains(property)) {
            properties.add(property);
            property.addAgent(this); // maintain bidirectional relationship
        }
    }
}