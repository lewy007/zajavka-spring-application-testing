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

### 2 commit - to wykorzystanie springowych adnotacji. *Testujmey aplikacje springowa razem z podniesieniem calego kontekstu springowego*, tak jakbysmy uruchamiai aplikacje. Mozna uzyc tych dwoch wpisow nad klasa, co sa zakomnetowane lub lednego uzytego w ponizszym kodzie:
```java
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
```

### Natomiast w ponizszej klasie testowej nie zostal podniesiony kontekst springowy, poniewaz przetestowlismy klase jednostkowo, a wszytskie zaleznosci zostaly zaslepione przy wykorzystaniu Mockito
```java
@ExtendWith(MockitoExtension.class)
class ExampleBeanServiceImplTest {

    @InjectMocks
    private ExampleBeanServiceImpl exampleBeanService;

    @Mock
    private InjectedBeanService injectedBeanService;

    @Test
    public void sampleMethodTest() {

        //given
        Mockito.when(injectedBeanService.anotherSampleMethod()).thenReturn(true);

        //when
        boolean result = exampleBeanService.sampleMethod();

        //then
        Assertions.assertEquals(true, result);
    }

}
```