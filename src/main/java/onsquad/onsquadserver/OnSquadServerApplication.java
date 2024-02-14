package onsquad.onsquadserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class OnSquadServerApplication {

    private final DataSourceProperties dataSourceProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        log.info("[LOGGING] : datasource:url --> {}", dataSourceProperties.getUrl());
    }

    public static void main(String[] args) {
        SpringApplication.run(OnSquadServerApplication.class, args);
    }
}
