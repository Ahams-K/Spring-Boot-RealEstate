package be.kdg.prog3.RealEstateServiceSystem.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionHistory {
    // Add necessary fields and methods to track the session history

    private final List<Session> history = new ArrayList<>();

    public void addSession(String page) {
        history.add(new Session(page, LocalDateTime.now()));
    }

    public List<Session>  getHistory(){
        return history;
    }
}
