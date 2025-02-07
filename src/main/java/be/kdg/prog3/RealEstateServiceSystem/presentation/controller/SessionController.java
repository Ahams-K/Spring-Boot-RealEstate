package be.kdg.prog3.RealEstateServiceSystem.presentation.controller;

import be.kdg.prog3.RealEstateServiceSystem.session.SessionHistory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    private final SessionHistory sessionHistory;

    public SessionController(SessionHistory sessionHistory) {
        this.sessionHistory = sessionHistory;
    }


    public void trackPage(String page){
        sessionHistory.addSession(page);
    }

    @GetMapping("/history")
    public String showSessionHistory(Model model){
        trackPage("History");
        model.addAttribute("sessionHistory", sessionHistory.getHistory());
        return "sessionhistory";
    }
}
