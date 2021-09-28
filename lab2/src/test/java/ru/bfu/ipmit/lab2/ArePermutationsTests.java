package ru.bfu.ipmit.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArePermutationsTests {

    @Test
    void shouldReturnTrueIfStringsArePermutations() {
        String firstString = "ab1";
        String secondString = "b1a";

        assertTrue(Lab2.arePermutations(firstString, secondString));

        firstString = "qwerty";
        secondString = "qwert";
        assertFalse(Lab2.arePermutations(firstString, secondString));

        firstString = "aaaaaaaaaaaabbbbbbbbbbb";
        secondString = "aaaaaaaaaaaabbbbbbbbbbb";
        assertTrue(Lab2.arePermutations(firstString, secondString));

        assertTrue(Lab2.arePermutations("", ""));
    }

}
