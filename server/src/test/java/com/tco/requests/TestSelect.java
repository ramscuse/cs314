package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSelect {
    @Test
    @DisplayName("ramscuse: creates class")
    public void testOneDistance() {
        Select test = new Select();
        assertTrue(test instanceof Select);
    }
}
