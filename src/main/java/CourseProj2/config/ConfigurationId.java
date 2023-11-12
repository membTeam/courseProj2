package CourseProj2.config;

import java.util.UUID;

public class ConfigurationId {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
