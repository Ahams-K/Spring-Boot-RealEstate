package be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID ownerID;
    private String name;
    private String contactInfo;
    private String email;
    private String address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> purchasedProperties = new ArrayList<>();

    public Owner(UUID ownerID, String name, String contactInfo, String email, String address) {
        this.ownerID = ownerID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.email = email;
        this.address = address;
    }

    public Owner() {

    }


    public void addPurchasedProperty(Property property) {
        if (!purchasedProperties.contains(property)) {
            purchasedProperties.add(property);
            property.setOwner(this); // maintain bidirectional relationship
        }
    }

    public void removePurchasedProperty(Property property) {
        purchasedProperties.remove(property);
        property.setOwner(null);
    }

    public List<Property> getPurchasedProperties() {
        return new ArrayList<>(purchasedProperties);
    }


    @Override
    public String toString() {
        return " OWNER DETAILS " + "\n" +
                "Owner ID: " + ownerID +"\n"+
                "Name: " + name +"\n" +
                "Contact Info: " + contactInfo +"\n" +
                "Email: " + email +"\n" +
                "Address: " + address;
    }
}
