package pl.zajavka;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ConfigScanBean.class)
@SpringJUnitConfig(classes = ConfigScanBean.class)
public class NoSpringTestContextFrameworkExampleTest {

    @Autowired
    private ExampleBeanService exampleBeanService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(exampleBeanService);
    }

    @Test
    public void sampleMethodTest() {

        boolean result = exampleBeanService.sampleMethod();

        Assertions.assertEquals(false, result);
    }
}
