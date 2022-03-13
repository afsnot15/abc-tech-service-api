package br.com.fiap.abctechservice.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class PropertiesComponent {

    private Properties properties;

    public PropertiesComponent() {
        properties = new Properties();

        InputStream input = getClass().getClassLoader().getResourceAsStream("application.yml");

        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return properties.getProperty("build.name");
    }

    public String getVersion() {
        return properties.getProperty("build.version");
    }

}
