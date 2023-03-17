package com.ynz.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * Using argument captor;
 */
@ExtendWith(MockitoExtension.class)
public class CaptorInstanceTest {
    class Person {
        public Person() {
        }
    }

    class Meeting {
        public void addPersons(Person... p) {
        }
    }

    @Mock
    private List<String> mockedList;

    @Captor
    private ArgumentCaptor<String> captor;

    @Mock
    private Meeting mockedMeeting;

    @Captor
    private ArgumentCaptor<Person[]> varArgumentCaptor;

    @Test
    void whenInjectArgumentCaptor_ThenShouldHaveIt() {
        assertNotNull(captor);
    }

    @Test
    void whenBindingCaptorWithMock_ThenCanMonitorArgument() {
        mockedList.add("one");

        verify(mockedList).add(captor.capture());
        assertEquals("one", captor.getValue());
    }

    @Test
    void whenInputVarArgumentsToMethod_ThenCaptureListOfThem() {
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        List<Person> expected = Arrays.asList(p1, p2, p3);
        mockedMeeting.addPersons(p1, p2, p3);

        verify(mockedMeeting).addPersons(varArgumentCaptor.capture());
        assertEquals(expected, varArgumentCaptor.getAllValues());
    }
}
