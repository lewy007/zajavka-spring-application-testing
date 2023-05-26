## Testy integracyjne z wykorzystaniem spring context
### 1szy commit to klasa testowa bez adnotacji springowych. Spring context został podniesiony recznie.
```java
public class NoSpringTestContextFrameworkExampleTest {

    private ExampleBeanService exampleBeanService;

    @BeforeEach
    // podnosimy kontekst springowy
    public void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigScanBean.class);
        // wskazujemy beana
        exampleBeanService = applicationContext.getBean(ExampleBeanService.class);

        boolean result = exampleBeanService.sampleMethod();

        Assertions.assertNotNull(exampleBeanService);

    }

    @AfterEach
    public void tearDown() {

        exampleBeanService = null;

        Assertions.assertNull(exampleBeanService);
    }

    @Test
    public void sampleMethodTest() {

        boolean result = exampleBeanService.sampleMethod();

        Assertions.assertEquals(false, result);
    }
}
```