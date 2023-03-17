package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

/**
 * Void methods can be used with Mockito’s doNothing(), doThrow(), and doAnswer() methods,
 * making mocking and verifying intuitive.
 * <p>
 * However, doNothing() is Mockito's default behavior for void methods.
 * <p>
 * One reason to override the default behavior with doNothing() is to capture arguments.
 * <p>
 * A method may perform more complex behavior than merely adding or setting value.
 * <p>
 * For these situations, we can use Mockito’s Answer to add the behavior we need:
 * <p>
 * answer define a series actions as invoking on a mock instance; simulating a call on a dummy instance, and returns
 * a complex behaviour.
 * <p>
 * Partial mocks are an option too. Mockito's doCallRealMethod() can be used for void methods:
 */

@ExtendWith(MockitoExtension.class)
public class MockVoidMethodsTest {

    @Mock
    private List<String> stringList;

    @Captor
    private ArgumentCaptor<String> valueCaptor;

    @Captor
    private ArgumentCaptor<Integer> valueCaptorInteger;

    @Test
    void whenAddElementToIndexPosition_MethodInvokedOnce() {
        doNothing().when(stringList).add(isA(Integer.class), isA(String.class));
        stringList.add(10, "my list");
        verify(stringList, times(1)).add(10, "my list");
    }

    @Test
    void invokingVoidMethod_MockitoDefaultBehaviorIsDoNothing() {
        stringList.add(0, "");
        verify(stringList, times(1)).add(0, "");
    }

    @Test
    void voidMethodThrowException_UsingDoThrow() {
        Mockito.doThrow(RuntimeException.class).when(stringList).add(isA(Integer.class), isNull());
        assertThrows(RuntimeException.class, () -> stringList.add(isA(Integer.class), isNull()));
    }

    @Test
    void captureVoidMethodInputArgument() {
        doNothing().when(stringList).add(isA(Integer.class), valueCaptor.capture());
        stringList.add(0, "capture");
        assertEquals("capture", valueCaptor.getValue());
    }

    @Test
    void captureTwoArguments() {
        doNothing().when(stringList).add(valueCaptorInteger.capture(), valueCaptor.capture());
        stringList.add(1, "capture");
        assertEquals(1, valueCaptorInteger.getValue());
        assertEquals("capture", valueCaptor.getValue());
    }

    @Test
    void answeringCallToVoid() {
        doAnswer(invocation -> {
                    Object arg0 = invocation.getArgument(0);
                    Object arg1 = invocation.getArgument(1);

                    assertEquals(3, arg0);
                    assertEquals("answer me", arg1);
                    return null;
                }
        ).when(stringList).add(isA(Integer.class), isA(String.class));

        stringList.add(3, "answer me");
    }

    @Test
    void doNothingMockingVoidMethod() {
        doNothing().when(stringList).add(isA(Integer.class), isA(String.class));
        stringList.add(10, "something");
        verify(stringList, times(1)).add(10, "something");
    }

}
