package com.ynz.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @InjectMocks annotation to inject mock fields into the tested object automatically
 */

@ExtendWith(MockitoExtension.class)
public class UsingInjectMocksTest {

    @Mock
    private Address address;

    @InjectMocks
    private Person person = new Person();

    @Test
    void whenUsingInjectMocks_MockedInsideFieldAreInjected() {
        assertNotNull(person);
        assertNotNull(address);
        assertNotNull(person.getAddress());
    }

    @Test
    void whenClassInsideFieldMocked_ThenItCanBeInjected() {
        String expected = "Peter road";
        when(address.getStreet()).thenReturn(expected);

        assertEquals(expected, person.getAddress().getStreet());
    }

    class Address {
        private String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }
    }

    class Person {
        private Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

}
