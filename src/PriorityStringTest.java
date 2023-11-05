import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityStringTest {
    PriorityString test;

    @Test
    void isMoreImportant() {
    }

    @Test
    void getValue() {
    }

    @BeforeEach
    void setUp() {
        test = new PriorityString("apple");
        System.out.println(test.getValue());
        System.out.println(test.isMoreImportant(new PriorityString("banana")));
        System.out.println(test.isMoreImportant(new PriorityString("acid")));
        System.out.println(test.isMoreImportant(new PriorityString("apples")));
    }

}