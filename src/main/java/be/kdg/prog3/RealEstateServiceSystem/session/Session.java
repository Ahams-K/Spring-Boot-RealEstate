package be.kdg.prog3.RealEstateServiceSystem.session;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class Session {
    private String page;
    private LocalDateTime timestamp;

    public Session(String page, LocalDateTime timestamp){
        this.page = page;
        this.timestamp = timestamp;
    }
}
