package CourseProj2.config;

import CourseProj2.models.Examination;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

@Configuration
public class PersistenceConfig {
    /**
     * Используется упрощенный вариант, только для Examination
     * @return
     */
    @Bean
    public ApplicationListener<BeforeSaveEvent> idGenerator(){
        return event -> {
            var entity = event.getEntity();
            if (entity instanceof Examination) {
                var examination = (Examination) entity;
                if (examination.getId() == null) {
                    examination.setId(Examination.generateId());
                }
            }
        };
    }
}
