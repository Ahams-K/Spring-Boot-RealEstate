package be.kdg.prog3.RealEstateServiceSystem.presentation.dto;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.RealEstateAgency;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RealEstateAgencyDTO {

    private UUID agencyID;
    private String agencyName;
    private String address;
    private String contactInfo;
    private String city;
    private String image;

    //Relations
    private Set<AgentDTO> agents;

    public RealEstateAgencyDTO(RealEstateAgency agency) {
        this.agencyID = agency.getAgencyID();
        this.agencyName = agency.getAgencyName();
        this.address = agency.getAddress();
        this.contactInfo = agency.getContactInfo();
        this.city = agency.getCity();
        this.image = agency.getImage();
        this.agents = agency.getAgents().stream().map(AgentDTO::new).collect(Collectors.toSet());
    }
}
