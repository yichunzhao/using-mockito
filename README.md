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

Difference between Mock and Spy

When Mockito creates a mock, it does so from the Class of a Type, not from an actual instance. From the Class, Mockito creates a bar-boned instance.  
On the other hand, the spy will wrap an existing instance. 
It will still behave in the same way as the normal instance, the only difference is that it will also be instrumented to track all the interactions with it.
In addtion, it may mock behaviours of a spied instance. 

Mockito NotAMockException

This exception is one of the common exceptions we will likely encounter when misusing mocks or spies.
The Mockito when() method expects a mock or spy object as the argument.

ArgumentCaptor Instance
Using @Captor to inject a ArgumentCaptor Instance, with the same generic type as the target argument type. 
ArgumentCaptor instance is used together with verify mechanism, in order to capture the input method argument while 
verifying a mocked instance's behaviour.  