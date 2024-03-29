# using-mockito

## Mockito with Junit5

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

## Difference between Mock and Spy

When Mockito creates a mock, it does so from the Class of a Type, not from an actual instance, creates a bar-boned instance.  
On the other hand, the spy will wrap an existing instance. 
It will still behave in the same way as the normal instance, the only difference is that it will also be instrumented to track all the interactions with it.
In addition, it may mock behaviours of a spied instance. 

## Mockito NotAMockException

This exception is one of the common exceptions we will likely encounter when misusing mocks or spies.
The Mockito when() method expects a mock or spy object as the argument.

## When we should use thenReturn and when thenAnswer?

The simplest answer is – if you need fixed return value on method call then we should use thenReturn(…)
If you need to perform some operation or the value need to be computed at run time then we should use thenAnswer(…)
When we should use thenReturn and when thenAnswer?

Method thenReturn() needs a fixed object which will be returned when we call the method.
We can pass any type of object or value, the same value will be returned on method call.

````
Syntax: OngoingStubbing<T> thenReturn(T value);
````

Method thenAnswer needs the object of class implementing interface org.mockito.stubbing.Answer.
Answer is the functional interface having method answer(..)
The method answer() will be invoked when we call mocked method from mocked object.
We can use java 8 lambda feature for implementation of answer method.

````
Syntax: OngoingStubbing<T> thenAnswer(Answer<?> answer);
````

## ArgumentCaptor Instance

ArgumentCaptor is a utility class provided by the Mockito testing framework in Java. It allows you to capture and inspect the argument(s) that were passed to a method during a unit test.

In a unit test, you may want to verify that a method was called with a specific argument, or you may want to inspect the value of an argument that was passed to a method. The ArgumentCaptor class helps you do this by capturing the argument(s) that were passed to a method.

ArgumentCaptor instance is used together with verify, to capture the input method argument(s) while verifying a mocked instance's behaviour. 

Here's an example of how you can use ArgumentCaptor in a unit test:

```
// Create a mock object of a class that has a method that we want to test
SomeClass someObject = Mockito.mock(SomeClass.class);

// Create an ArgumentCaptor object for the type of argument that we want to capture
ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

// Call the method that we want to test, passing in the argument that we want to capture
someObject.someMethod("test");

// Use the ArgumentCaptor to capture the argument that was passed to the method
Mockito.verify(someObject).someMethod(argumentCaptor.capture());

// Assert that the captured argument is equal to the expected value
assertEquals("test", argumentCaptor.getValue());

```

In this example, we create a mock object of a class SomeClass that has a method someMethod which takes a String argument. We then create an ArgumentCaptor object for the String class, and call the someMethod method on the mock object, passing in the argument "test". We then use the verify method to check that the someMethod method was called with the expected argument, and finally use the getValue method of the ArgumentCaptor object to retrieve the captured argument and assert that it is equal to the expected value.

ArgumentCaptor can be very useful in unit tests when you need to verify or inspect the arguments passed to a method, and can help make your tests more precise and reliable.

### @Captor  

@Captor is an annotation provided by the Mockito testing framework in Java that is used in conjunction with ArgumentCaptor. It is used to create an ArgumentCaptor object for a given class and inject it into a test class field.

Using @Captor can simplify the code required to create and use an ArgumentCaptor object, and can make the test code more readable.

Here's an example of how you can use @Captor:

```
// Create a mock object of a class that has a method that we want to test
SomeClass someObject = Mockito.mock(SomeClass.class);

// Create a field to hold the ArgumentCaptor object
@Captor
private ArgumentCaptor<String> argumentCaptor;

@Before
public void setUp() {
    // Initialize the field with a new ArgumentCaptor object
    MockitoAnnotations.initMocks(this);
}

@Test
public void testSomeMethod() {
    // Call the method that we want to test, passing in the argument that we want to capture
    someObject.someMethod("test");

    // Use the ArgumentCaptor to capture the argument that was passed to the method
    Mockito.verify(someObject).someMethod(argumentCaptor.capture());

    // Assert that the captured argument is equal to the expected value
    assertEquals("test", argumentCaptor.getValue());
}
```
In this example, we create a mock object of a class SomeClass that has a method someMethod which takes a String argument. We then define a field argumentCaptor of type ArgumentCaptor<String> and annotate it with @Captor. This will create a new ArgumentCaptor object for the String class and inject it into the argumentCaptor field.

In the setUp method, we initialize the argumentCaptor field using the MockitoAnnotations.initMocks method.

In the testSomeMethod method, we call the someMethod method on the mock object, passing in the argument "test". We then use the verify method to check that the someMethod method was called with the expected argument, and finally use the getValue method of the ArgumentCaptor object to retrieve the captured argument and assert that it is equal to the expected value.

Using @Captor can help make your test code more concise and readable, and can be especially useful when you need to capture multiple arguments of different types.
