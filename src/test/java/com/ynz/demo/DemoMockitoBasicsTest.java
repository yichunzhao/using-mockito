package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * using @Mock annotation to inject a mocked instance without calling Mockito.mock
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

        assertEquals(0, mockedList.size());

        when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }
}