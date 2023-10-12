package me.ruslan.task3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import me.ruslan.task3.math_eval.SyntaxTreeBuilder;
import me.ruslan.task3.math_eval.Tokenizer;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private String text = "";
    private String answer = "";
    private static final HashMap<Integer, String> chars = new HashMap<Integer, String>() {{
        put(R.id.button_digit_one, "1");
        put(R.id.button_digit_two, "2");
        put(R.id.button_digit_three, "3");
        put(R.id.button_digit_four, "4");
        put(R.id.button_digit_five, "5");
        put(R.id.button_digit_six, "6");
        put(R.id.button_digit_seven, "7");
        put(R.id.button_digit_eight, "8");
        put(R.id.button_digit_nine, "9");
        put(R.id.button_digit_zero, "0");
        put(R.id.button_plus, "+");
        put(R.id.button_subtraction, "-");
        put(R.id.button_multiplication, "*");
        put(R.id.button_division, "/");
        put(R.id.button_point, ".");
    }};

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.textResult);

        if(state != null && state.containsKey("text") && state.containsKey("answer")) {
            text = state.getString("text");
            answer = state.getString("answer");

            setTextOrZero();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable("text", text);
        state.putSerializable("answer", answer);
    }

    private void setTextOrZero() {
        String res = text.isEmpty() ? "0" : text;
        if(!text.isEmpty() && !answer.isEmpty())
            res += String.format(" = %s", answer);
        resultText.setText(res);
    }

    public void btnOnClick(View view) {
        if(chars.containsKey(view.getId())) {
            if(!answer.isEmpty()) {
                text = answer;
                answer = "";
            }
            text += chars.get(view.getId());
        } else {
            if(view.getId() == R.id.button_remove_all)
                text = answer = "";
            else if(view.getId() == R.id.button_remove_last && !text.isEmpty()) {
                text = text.substring(0, text.length() - 1);
                answer = "";
            } else if(view.getId() == R.id.button_equals && !text.isEmpty()) {
                try {
                    answer = new SyntaxTreeBuilder(new Tokenizer().tokenize(text)).build().eval().toPlainString();
                } catch (Exception e) {
                    Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        setTextOrZero();
    }
}