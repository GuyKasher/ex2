package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }
    TextView digit0=findViewById(R.id.button0);
    TextView digit1=findViewById(R.id.button1);
    TextView digit2=findViewById(R.id.button2);
    TextView digit3=findViewById(R.id.button3);
    TextView digit4=findViewById(R.id.button4);
    TextView digit5=findViewById(R.id.button5);
    TextView digit6=findViewById(R.id.button6);
    TextView digit7=findViewById(R.id.button7);
    TextView digit8=findViewById(R.id.button8);
    TextView digit9=findViewById(R.id.button9);
    TextView clear=findViewById(R.id.buttonClear);
    TextView minus=findViewById(R.id.buttonMinus);
    TextView plus=findViewById(R.id.buttonPlus);
    TextView equals=findViewById(R.id.buttonEquals);
    View backSpace=findViewById(R.id.buttonBackSpace);
    TextView mainText=findViewById(R.id.textViewCalculatorOutput);


//    if (savedInstanceState.getSerializable("curState")!=null){
    mainText.setText(calculator.output());
//    }

    setClickListener(digit0,0,mainText);
    setClickListener(digit1,1,mainText);
    setClickListener(digit2,2,mainText);
    setClickListener(digit3,3,mainText);
    setClickListener(digit4,4,mainText);
    setClickListener(digit5,5,mainText);
    setClickListener(digit6,6,mainText);
    setClickListener(digit7,7,mainText);
    setClickListener(digit8,8,mainText);
    setClickListener(digit9,9,mainText);

    minus.setOnClickListener(v -> {
      calculator.insertMinus();
      mainText.setText(calculator.output());
    });
    clear.setOnClickListener(v -> {
      calculator.clear();
      mainText.setText(calculator.output());
    });

    backSpace.setOnClickListener(v -> {
      calculator.deleteLast();
      mainText.setText(calculator.output());
    });

    plus.setOnClickListener(v -> {
      calculator.insertPlus();
      mainText.setText(calculator.output());
    });

    equals.setOnClickListener(v -> {
      calculator.insertEquals();
      mainText.setText(calculator.output());
    });





    /*
    TODO:
    - find all views
    - initial update main text-view based on calculator's output
    - set click listeners on all buttons to operate on the calculator and refresh main text-view
     */
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("curState",calculator.saveState());
    // todo: save calculator state into the bundle
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Serializable curState=savedInstanceState.getSerializable("curState");
    calculator.loadState(curState);
    // todo: restore calculator state from the bundle, refresh main text-view from calculator's output
  }
  private void setClickListener(TextView view,int digit,TextView mainText) {
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculator.insertDigit(digit);
        mainText.setText(calculator.output());

      }

    });
  }
}