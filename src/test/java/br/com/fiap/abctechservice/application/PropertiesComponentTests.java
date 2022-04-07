package br.com.fiap.abctechservice.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesComponentTests {

    private PropertiesComponent propertiesComponent;

    @BeforeEach
    void setup() {
        propertiesComponent = new PropertiesComponent();
    }

    @Test
    public void testGetName() {
        Assertions.assertNotNull(propertiesComponent.getName());
    }
    
    @Test
    public void testGetVersion() {
        Assertions.assertNotNull(propertiesComponent.getVersion());
    }
}
