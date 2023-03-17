package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Spy on a real instance
 * <p>
 * This will allow us to call all the normal methods of the object while still tracking every interaction,
 * just as we would with a mock.
 * <p>
 * Stubbing on a spied instance, meaning that we may mock a spied instance behaviour just like on a mocked instance.
 */

@ExtendWith(MockitoExtension.class)
public class SpyRealInstanceTest {

    @Spy
    private List<String> spyList;

    public SpyRealInstanceTest() {
        spyList = new ArrayList<>();
    }

    @Test
    void whenSpyRealInstance_ThenMayObserveItsBehaviours() {
        spyList.add("one");
        spyList.add("two");

        assertEquals(2, spyList.size());
        verify(spyList, times(2)).add(anyString());
    }

    @Test
    void whenStubASpyInstance_ThenMockRealInstanceBehaviour() {
        assertEquals(0, spyList.size());
        spyList.add("three");
        spyList.add("four");
        assertEquals(2, spyList.size());
        when(spyList.size()).thenReturn(250);
        assertEquals(250, spyList.size());
    }
}
