package onsquad.onsquadserver.global.config.time;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.TimeZone;

@Component
public class TimeZoneConfiguration implements ApplicationRunner {

    private static final String TIMEZONE_SEOUL = "Asia/Seoul";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_SEOUL));
    }
}
