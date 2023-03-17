package com.ynz.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Answer specifies an action that is executed and a return value that is returned when you interact with the mock.
 */
@ExtendWith(MockitoExtension.class)
public class UseMockAnswerTest {

    @Mock
    private List<String> list;

    @Test
    void whenThenAnswerMoreThanFixedValue() {
        when(list.get(0)).thenAnswer(invocation -> LocalDate.now().toString());

        String currentDate = list.get(0);
        assertEquals(LocalDate.now().toString(), currentDate);
    }

    @Test
    void whenDoAnswer() {
        doAnswer(invocation -> {
            String arg = invocation.getArgument(0);
            assertEquals("answer", arg);
            return false;
        }).when(list).add(anyString());

        list.add("answer");
    }

    @Test
    void writeAnswerInAnonymousClass() {
        when(list.get(0)).thenAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                return LocalDate.now().toString();
            }
        });

        String currentDate = list.get(0);
        assertEquals(LocalDate.now().toString(), currentDate);
    }
}
