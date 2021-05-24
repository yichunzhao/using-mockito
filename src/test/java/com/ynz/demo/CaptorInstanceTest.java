package com.ynz.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * Using argument captor;
 */
@ExtendWith(MockitoExtension.class)
public class CaptorInstanceTest {

    @Mock
    private List<String> mockedList;

    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    void whenInjectArgumentCaptor_ThenShouldHaveIt(){
        assertNotNull(captor);
    }

    @Test
    void whenBindingCaptorWithMock_ThenCanMonitorArgument(){
        mockedList.add("one");

        verify(mockedList).add(captor.capture());
        assertEquals("one", captor.getValue());
    }

}
