package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @InjectMock inject a Mock instance inside a Spy instance automatically.
 */
@ExtendWith(MockitoExtension.class)
public class InjectMockIntoSpy {

    @Mock
    private Map<String, String> wordMMeaningMap;

    @Spy
    @InjectMocks
    MyDictionary dictionary = new MyDictionary(); //spy a real instance

    @Test
    void whenInjectMockIntoSpyInstance_ItReturnNonNullInstance() {
        assertNotNull(dictionary.getWordMMeaningMap());
    }

    @Test
    void whenMockingInteractionOnTheMockField_ItExposesFromSpyInstance() {
        String expected = "a plant";
        when(wordMMeaningMap.get("tree")).thenReturn(expected);
        String actual = dictionary.getWordMMeaningMap().get("tree");
        assertEquals(expected, actual);
    }

    class MyDictionary {
        private Map<String, String> wordMMeaningMap;
        public Map<String, String> getWordMMeaningMap() {
            return wordMMeaningMap;
        }
    }
}
