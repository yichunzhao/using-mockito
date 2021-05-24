# using-mockito
Mockito with Junit5

The annotation @ExtendWith(MockitoExtension.class) is applied on the Test class level, bridges the JUnit5 and Mockito framework, and it needs the following Maven dependency.
````
  <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>3.10.0</version>
            <scope>test</scope>
  </dependency>
 ````

After bridging Junit 5 and Mockitor, it allows to use the annotation @Mock to inject the mocked instances, instead of manually creation. 

````
@ExtendWith(MockitoExtension.class)
class DemoMockitoBasicsTest {

    @Mock
    private List<String> mockedList;
````
