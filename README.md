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

After bridging Junit 5 and Mockito, it allows to use the annotation @Mock to inject an mocked instance, instead of manual creation. 

````
@ExtendWith(MockitoExtension.class)
class DemoMockitoBasicsTest {

    @Mock
    private List<String> mockedList;
````

Difference between Mock and Spy

When Mockito creates a mock, it does so from the Class of a Type, not from an actual instance, creates a bar-boned instance.  
On the other hand, the spy will wrap an existing instance. 
It will still behave in the same way as the normal instance, the only difference is that it will also be instrumented to track all the interactions with it.
In addition, it may mock behaviours of a spied instance. 

Mockito NotAMockException

This exception is one of the common exceptions we will likely encounter when misusing mocks or spies.
The Mockito when() method expects a mock or spy object as the argument.

ArgumentCaptor Instance

ArgumentCaptor instance is used together with verify, to capture the input method argument(s) while verifying a mocked instance's behaviour.  
@Captor is usde to inject a ArgumentCaptor Instance. 
