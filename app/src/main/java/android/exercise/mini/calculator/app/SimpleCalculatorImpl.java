package android.exercise.mini.calculator.app;

import java.io.Serializable;

public class SimpleCalculatorImpl implements SimpleCalculator {
  public String curState="0";
  // todo: add fields as needed

  @Override
  public String output() {
    // todo: return output based on the current state
    return curState;
  }

  @Override
  public void insertDigit(int digit) {
    // todo: insert a digit
    if (digit>9 || digit<0){
      throw new RuntimeException();
    }
    if (curState.equals("0")) {
      curState = String.valueOf(digit);
    } else {
      curState = curState + digit;
    }
  }

  @Override
  public void insertPlus() {
    // todo: insert a plus
    curState=curState+"+";
  }

  @Override
  public void insertMinus() {
    // todo: insert a minus
    curState+="-";
  }

  @Override
  public void insertEquals() {
    // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
    //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"

    char minus='-';
    char plus='+';
    int indexMinus=curState.indexOf(minus);
    int indexPlus=curState.indexOf(plus);
    if (indexMinus!=-1 ){
      if (indexMinus==0){
        indexMinus=curState.substring(1).indexOf(minus)+1;
      }
      int firstNum=Integer.parseInt(curState.substring(0,indexMinus));
      int secondNum=Integer.parseInt(curState.substring(indexMinus+1));
      curState=String.valueOf(firstNum-secondNum);
      }
    else if (indexPlus!=-1){
      int firstNum=Integer.parseInt(curState.substring(0,indexPlus));
      int secondNum=Integer.parseInt(curState.substring(indexPlus+1));
      curState=String.valueOf(firstNum+secondNum);
    }

  }

  @Override
  public void deleteLast() {
    // todo: delete the last input (digit, plus or minus)
    //  e.g.
    //  if input was "12+3" and called `deleteLast()`, then delete the "3"
    //  if input was "12+" and called `deleteLast()`, then delete the "+"
    //  if no input was given, then there is nothing to do here
    if (curState.length()==1){
      curState="0";
    }
    else {
      curState = curState.substring(0, curState.length() - 1);
    }
  }

  @Override
  public void clear() {
    // todo: clear everything (same as no-input was never given)
    curState="0";
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    // todo: insert all data to the state, so in the future we can load from this state
    state.state=curState;
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    curState=casted.state;
    // todo: use the CalculatorState to load
  }

  private static class CalculatorState implements Serializable {
    String state;
    /*
    TODO: add fields to this class that will store the calculator state
    all fields must only be from the types:
    - primitives (e.g. int, boolean, etc)
    - String
    - ArrayList<> where the type is a primitive or a String
    - HashMap<> where the types are primitives or a String
     */
  }
}
