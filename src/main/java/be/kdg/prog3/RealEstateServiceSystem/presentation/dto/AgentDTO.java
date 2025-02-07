package be.kdg.prog3.RealEstateServiceSystem.presentation.dto;


import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;

import java.util.List;
import java.util.UUID;

public class AgentDTO {

    private UUID agentID;
    private String agentName;
    private String contactInfo;
    private String LicenceNumber;
    private String email;

    // Relations
    private UUID agencyID;
    private List<propertyDTO> properties;

    public AgentDTO(Agent agent){
        this.agentID = agent.getAgentID();
        this.agentName = agent.getAgentName();
        this.contactInfo = agent.getContactInfo();
        this.LicenceNumber = agent.getLicenceNumber();
        this.email = agent.getEmail();
        this.agencyID =  (agent.getAgency() != null) ? agent.getAgency().getAgencyID() : null;
        this.properties = agent.getProperties().stream().map(propertyDTO::new).toList();
    }

}
