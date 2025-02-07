package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgencyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgentServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.PropertyServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.presentation.dto.AgentDTO;
import be.kdg.prog3.RealEstateServiceSystem.presentation.dto.propertyDTO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/export")
@Controller
public class JsonController {
    private Logger logger = LoggerFactory.getLogger(JsonController.class);
    private AgentServiceInterface agentService;
    private PropertyServiceInterface propertyService;
    private Gson gson;

    @Autowired
    public JsonController(PropertyServiceInterface propertyService, AgentServiceInterface agentService, Gson gson) {
        this.propertyService = propertyService;
        this.agentService = agentService;
        this.gson = gson;
    }

    @GetMapping("/agents.json")
    public ResponseEntity<String> downloadAgents() {
        logger.info("Donwloading all agents and their , schools and courses");
        List<Agent> agents = agentService.getAllAgents();
        List<AgentDTO> studentDTOS = agents.stream().map(AgentDTO::new).toList();
        String jsonString = gson.toJson(studentDTOS);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=agents.json");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.ok()
                .headers(headers)
                .body(jsonString);
    }

    @GetMapping("/properties.json")
    public ResponseEntity<String> downloadProperties() {
        logger.info("Donwloading all properties and agents.");
        List<propertyDTO> properties = propertyService.getPropertiesList().stream().map(propertyDTO::new).toList();
        String jsonString = gson.toJson(properties);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=properties.json");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return ResponseEntity.ok()
                .headers(headers)
                .body(jsonString);
    }

}
