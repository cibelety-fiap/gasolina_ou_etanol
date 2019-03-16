package br.com.fiap.gasolinaouetanol;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;



public class MainActivity extends AppCompatActivity {

    private TextView gasPriceTextView;

    private SeekBar gasSeekBar;

    private TextView ethanolPriceTextView;

    private SeekBar ethanolSeekBar;

    private TextInputEditText answerTextInputEditText;

    private ImageView resultImageView;

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private double gasPrice = 0.0;

    private double ethanolPrice = 0.0;

    public class SeekbarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            if (seekBar.getId() == R.id.gasSeekBar) {
                gasPrice = progress / 100d;
                gasPriceTextView.setText(currencyFormat.format(gasPrice));
            } else if (seekBar.getId() == R.id.ethanolSeekBar) {
                ethanolPrice = progress / 100d;
                ethanolPriceTextView.setText(currencyFormat.format(ethanolPrice));
            }
            calcularMelhorCombustivel();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private void calcularMelhorCombustivel() {
        if ((ethanolPrice/gasPrice) >= 0.7) {
            answerTextInputEditText.setText(R.string.gasoline);
            resultImageView.setImageResource(R.drawable.barril_petroleo);
        } else {
            answerTextInputEditText.setText(R.string.ethanol);
            resultImageView.setImageResource(R.drawable.cana_acucar);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gasPriceTextView = findViewById(R.id.gasPriceTextView);
        gasSeekBar = findViewById(R.id.gasSeekBar);
        ethanolPriceTextView = findViewById(R.id.ethanolPriceTextView);
        ethanolSeekBar = findViewById(R.id.ethanolSeekBar);
        answerTextInputEditText = findViewById(R.id.answerTextInputEditText);
        resultImageView = findViewById(R.id.resultImageView);

        gasPriceTextView.setText(currencyFormat.format(0));
        ethanolPriceTextView.setText(currencyFormat.format(0));

        gasSeekBar.setOnSeekBarChangeListener(new SeekbarChangeListener());
        ethanolSeekBar.setOnSeekBarChangeListener(new SeekbarChangeListener());

    }
}
