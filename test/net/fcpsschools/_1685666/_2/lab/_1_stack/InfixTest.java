package net.fcpsschools._1685666._2.lab._1_stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author ApolloZhu, Pd. 1
 */
class InfixTest {
    static void infixToPostfix(String infix, String expected) {
        assertEquals(expected, Infix.toPostfix(infix));
    }

    @Test
    void testBasic() {
        // FIXME: Different style
        infixToPostfix("3+4-5+6",
                "3 4 + 5 - 6 +");
        infixToPostfix("(3+4)*5",
                "3 4 + 5 *");
        infixToPostfix("3*4+5*6",
                "3 4 * 5 6 * +");
    }

    @Test
    void testBinary() {
        infixToPostfix("3+4*5",
                "3 4 5 * +");
        infixToPostfix("3*4+5",
                "3 4 * 5 +");
        infixToPostfix("(4+5)-5*3",
                "4 5 + 5 3 * -");
        infixToPostfix("(3+4)*(5+6)",
                "3 4 + 5 6 + *");
        infixToPostfix("(3*(4+5)-2)/5",
                "3 4 5 + * 2 - 5 /");
        // FIXME: Different style than expected
        infixToPostfix("8+1*2-9/3",
                "8 1 2 * + 9 3 / -");
    }

    @Test
    void testUnmatched() {
        assertThrows(IllegalArgumentException.class,
                () -> Infix.toPostfix("((5+70)))"));
        infixToPostfix("((3+4*(50+6",
                "3 4 50 6 + * +");
        infixToPostfix("(52+7.0", "52 7.0 +");
        assertThrows(NumberFormatException.class,
                () -> Infix.toPostfix("7.2.55"));
    }

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> Infix.toPostfix("1+"));
        assertThrows(IllegalArgumentException.class,
                () -> Infix.toPostfix("*1"));
        assertThrows(IllegalArgumentException.class,
                () -> Infix.toPostfix("(1+)+2"));
        assertThrows(IllegalArgumentException.class,
                () -> Infix.toPostfix("(/1)!2"));
    }

    // FIXME: Extra space
    @Test
    void testNegate() {
        infixToPostfix(" - 3+4*-5",
                "-3 4 -5 * +");
        infixToPostfix("-(3+4)",
                "3 4 + -");
        infixToPostfix("+(-(-3+4))",
                "-3 4 + - +");
    }

    @Test
    void testComplex() {
        // FIXME: Missing first operand for binary operator: ^
        infixToPostfix("cos(( 5 % -3) !^ 3*pi)",
                "5 -3 % ! 3 ^ pi * cos");
        infixToPostfix("tan(2*3^(3+2-3.123)/12.2)+sin(pi)+cos(2)*sqrt(144)",
                "2 3 3 2 3.123 - + ^ * 12.2 / tan pi sin 2 cos 144 sqrt * + +");
    }
}