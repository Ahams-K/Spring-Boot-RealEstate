package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;

import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgentService;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.Service.AgentServiceInterface;
import be.kdg.prog3.RealEstateServiceSystem.businessLayer.domain.Agent;
import be.kdg.prog3.RealEstateServiceSystem.exception.DatabaseException;
import be.kdg.prog3.RealEstateServiceSystem.exception.RealEstateException;
import be.kdg.prog3.RealEstateServiceSystem.presentation.viewModels.AgentViewModel;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/agents")
public class AgentController {

    private final AgentServiceInterface agentService;
    private final SessionController sessionController;
    private final Logger logger = LoggerFactory.getLogger(AgentController.class);

    public AgentController(AgentServiceInterface agentService, SessionController sessionController) {
        this.agentService = agentService;
        this.sessionController = sessionController;
    }



    @GetMapping
    public String showAgents(Model model) {
        sessionController.trackPage("Agents");
        logger.debug("Displaying agents");
        model.addAttribute("Agents", agentService.getAgentList());
        return "agents";
    }


    @GetMapping("/{id}")
    public String viewAgentDetails(@PathVariable UUID id, Model model) {
        try {
            Agent agent = agentService.findAgentById(id).orElseThrow(() -> new RealEstateException("Invalid Id " + id));
            model.addAttribute("agent", agent);
            return "agent-details";
        } catch (RealEstateException ex) {
            logger.error("Error occurred while fetching agent details: ", ex);
            throw ex;
        }
    }


    @GetMapping("/add")
    public String showAddAgentForm(Model model) {
        sessionController.trackPage("Add Agent Form");
        model.addAttribute("agentViewModel", new AgentViewModel()); // Provide an empty Agent object for the form
        return "addagent";
    }


    @PostMapping("/add")
    public String processAddAgent(@Valid @ModelAttribute AgentViewModel agentViewModel,
                                  BindingResult bindingResult, Model model){
        sessionController.trackPage("Add Agent Form Submission");
        if (bindingResult.hasErrors()) {
            model.addAttribute("agentViewModel", agentViewModel);
            return "addagent";  // Return the form with error messages if validation fails
        }
        agentService.addAgent(agentViewModel);
        return "redirect:/agents";
    }


    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable UUID id){
        sessionController.trackPage("Delete Agent");
        agentService.deleteAgent(id);
        return "redirect:/agents";
    }


    @GetMapping("/{id}/properties")
    public String viewAgentProperties(@PathVariable("id") UUID agentID, Model model) {
        sessionController.trackPage("View Agent Properties");
        Agent agent = agentService.getAllAgents()
                .stream()
                .filter(a -> a.getAgentID().equals(agentID))
                .findFirst()
                .orElse(null);

        if (agent != null) {
            model.addAttribute("properties", agent.getProperties());
        }
        return "propertyinfo";
    }


    @PostMapping("/error")
    @ExceptionHandler(RealEstateException.class)
    public String handleRealEstateException(RealEstateException ex, Model model) {
        logger.error("RealEstateException occurred: ", ex);
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @PostMapping("/database-error")
    @ExceptionHandler(DatabaseException.class)
    public String handleDatabaseException(DatabaseException ex, Model model) {
        logger.error("DatabaseException occurred: ", ex);
        model.addAttribute("error", ex.getMessage());
        return "database-errors";
    }

}
