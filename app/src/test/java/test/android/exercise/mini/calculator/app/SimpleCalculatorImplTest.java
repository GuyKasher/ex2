package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

    @Test
    public void when_noInputGiven_then_outputShouldBe0() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        assertEquals("0", calculatorUnderTest.output());
    }

    @Test
    public void when_inputIsPlus_then_outputShouldBe0Plus() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertPlus();
        assertEquals("0+", calculatorUnderTest.output());
    }


    @Test
    public void when_inputIsMinus_then_outputShouldBeCorrect() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertMinus();
        String expected = "0-"; // TODO: decide the expected output when having a single minus
        assertEquals(expected, calculatorUnderTest.output());
    }

    @Test
    public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        try {
            calculatorUnderTest.insertDigit(357);
            fail("should throw an exception and not reach this line");
        } catch (RuntimeException e) {
            // good :)
        }
    }


    @Test
    public void when_callingDeleteLast_then_lastOutputShouldBeDeleted() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.deleteLast();
        assertEquals("56", calculatorUnderTest.output());
        // todo: implement test
    }

    @Test
    public void when_callingClear_then_outputShouldBeCleared() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());
        // todo: implement test
    }

    @Test
    public void when_savingState_should_loadThatStateCorrectly() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        // give some input
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);

        // save current state
        Serializable savedState = calculatorUnderTest.saveState();
        assertNotNull(savedState);

        // call `clear` and make sure calculator cleared
        calculatorUnderTest.clear();
        assertEquals("0", calculatorUnderTest.output());

        // load the saved state and make sure state was loaded correctly
        calculatorUnderTest.loadState(savedState);
        assertEquals("5+7", calculatorUnderTest.output());
    }

    @Test
    public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertDigit(5);
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(7);
        Serializable savedState = firstCalculator.saveState();
        secondCalculator.loadState(savedState);
        assertEquals("5+7", secondCalculator.output());


        // TODO: implement the test based on this method's name.
        //  you can get inspiration from the test method `when_savingState_should_loadThatStateCorrectly()`
    }

    @Test
    public void when_callingDeleteLastInTheMiddle_then_getTheCorrectValue() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.deleteLast();
        calculatorUnderTest.insertDigit(3);
        assertEquals("567+3", calculatorUnderTest.output());

    }

    @Test
    public void when_callingClearFewTimes_then_getTheLastInputBeforeClear() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.clear();
        calculatorUnderTest.insertDigit(2);
        calculatorUnderTest.insertDigit(3);
        assertEquals("23", calculatorUnderTest.output());
    }

    @Test
    public void when_givenLongInputWithMoreThenOneEquals_then_getTheCorrectAnswer() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertPlus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        assertEquals("15", calculatorUnderTest.output());
    }

    @Test
    public void when_givenLongInputWithMinusResults_then_getTheCorrectAnswer() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        calculatorUnderTest.insertEquals();
        assertEquals("-11", calculatorUnderTest.output());
    }

    @Test
    public void when_givenEqualInTheMiddelAndNotInTheEnd_then_getTheCorrectAnswer() {
        SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
        calculatorUnderTest.insertDigit(5);
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(6);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(7);
        calculatorUnderTest.insertEquals();
        calculatorUnderTest.insertMinus();
        calculatorUnderTest.insertDigit(3);
        assertEquals("-8-3", calculatorUnderTest.output());
    }

    @Test
    public void when_givenTwoCalculatorsAndSaveAndLoadBetweenThemWhileOtherActions_then_stateLoadAndSaveCorrectly() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertDigit(5);
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(7);
        Serializable savedState = firstCalculator.saveState();
        firstCalculator.insertEquals();
        secondCalculator.loadState(savedState);
        assertEquals("5+7", secondCalculator.output());
    }

    @Test
    public void when_givenOperatorAndANumberThenEquals_then_calculateZeroOperatorNumber() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(5);
        firstCalculator.insertEquals();
        assertEquals("5", firstCalculator.output());
    }

    @Test
    public void when_loadStateTwiceAfterClear_then_getTheCorrectValue() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(5);
        Serializable savedState = firstCalculator.saveState();
        firstCalculator.clear();
        firstCalculator.insertDigit(2);
        firstCalculator.insertPlus();
        firstCalculator.clear();
        firstCalculator.loadState(savedState);
        assertEquals("0+5", firstCalculator.output());
    }

    @Test
    public void when_doingDeleteFewTimesAndThenCalculate_then_getTheCorrectValue() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertPlus();
        firstCalculator.insertDigit(5);
        firstCalculator.insertDigit(5);
        firstCalculator.deleteLast();
        firstCalculator.insertDigit(4);
        firstCalculator.deleteLast();
        firstCalculator.insertDigit(2);
        firstCalculator.insertEquals();
        assertEquals("52", firstCalculator.output());
    }

    @Test
    public void when_deleteAfterOneInset_then_TheOutputReturnToZero() {
        SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
        firstCalculator.insertDigit(5);
        firstCalculator.deleteLast();
        assertEquals("0", firstCalculator.output());
    }

    // TODO:
    //  the existing tests are not enough since they only test simple use-cases with small inputs.
    //  write at least 10 methods to test correct behavior with complicated inputs or use-cases.
    //  examples:
    //  - given input "5+7-13<DeleteLast>25", expected output is "5+17-125"
    //  - given input "9<Clear>12<Clear>8-7=", expected output is "1"
    //  - given input "8-7=+4=-1=", expected output is "4"
    //  - given input "999-888-222=-333", expected output is "-111-333"
    //  - with 2 calculators, give them different inputs, then save state on first calculator and load the state into second calculator, make sure state loaded well
    //  etc etc.
    //  feel free to be creative in your tests!
}