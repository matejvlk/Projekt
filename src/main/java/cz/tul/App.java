package cz.tul;
import cz.tul.Config.Conditions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@SpringBootApplication
public class App {

    @Bean
    @Conditional(Conditions.ReadOnlyModeDisabled.class)
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run(){
        SpringApplication app = new SpringApplication(App.class);
        ApplicationContext ctx = app.run();
    }
}