package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * using @Mock annotation to inject a mocked instance without calling Mockito.mock
 *
 * using Mockito.inOrder to test methods are invoked in an order; otherwise, it throws
 * org.mockito.exceptions.verification.VerificationInOrderFailure:
 */

@ExtendWith(MockitoExtension.class)
class DemoMockitoBasicsTest {

    @Mock
    private List<String> mockedList;

    @Test
    void testIfEnvIsRead() {
        assertNotNull(mockedList);
    }

    @Test
    void whenUsingMockAnnotation_ThenMockedInstanceInjected() {
        mockedList.size();
        verify(mockedList).size();

        mockedList.add("one");
        verify(mockedList).add("one");
        verify(mockedList, times(1));

        assertEquals(0, mockedList.size());

        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    void whenMockitoInOrderMockInstance_ThenCanCheckingMethodInvokedInOrder(){
        mockedList.size();
        mockedList.add("what");
        mockedList.clear();

        InOrder inOrder = inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add(anyString());
        inOrder.verify(mockedList).clear();
    }

    @Test
    void whenVerifyNoInteractionHavingHappened_ThenUsing(){
        mockedList.add("who");

        verify(mockedList,never()).contains(anyString());
    }

}