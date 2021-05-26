package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * mocking a method that throws an Exception.
 * <p>
 * method return value: using syntax when(mock.behaviour).thenThrows(new Exception)
 * method return void: using syntax doThrow(new Exception).when(mockedInstance).behaviour()
 */
@ExtendWith(MockitoExtension.class)
public class MockingExceptionsTest {

    @Mock
    private List<String> mockedList;

    @Test
    void afterBridgingJunitAndMockito_ThenMockedInstanceInjected() {
        assertNotNull(mockedList);
    }

    @Test
    void whenMethodReturnValue_ThenThrowException() {
        when(mockedList.get(0)).thenThrow(new NullPointerException());
        assertThrows(Exception.class, () -> mockedList.get(0));
    }

    @Test
    void whenMethodReturnVoid_ThenDoThrowException() {
        //when(mockedList.clear()).thenThrow(new IllegalArgumentException());
        doThrow(new RuntimeException()).when(mockedList).clear();
        assertThrows(RuntimeException.class, () -> mockedList.clear());
    }

}
