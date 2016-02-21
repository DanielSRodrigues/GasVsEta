package br.com.unibratec.gasvseta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.edtEtanol)
    EditText edtEtanol;

    @Bind(R.id.edtGasolina)
    EditText edtGas;

    @Bind(R.id.txtBestChoice)
    TextView txtBestChoice;

    public static final String STATE_BESTCHOICE = "BESTCHOICE";
    public static final int CHOICE_ETA = 1;
    public static final int CHOICE_GAS = 2;
    public static final int CHOICE_NOTHING = 0;
    static int bestChoice = CHOICE_NOTHING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            bestChoice = savedInstanceState.getInt(STATE_BESTCHOICE);
            setBestChoiceMessage(bestChoice);
        }
    }

    @OnClick(R.id.btnCalc)
    public void calcular(View view) {
        if (edtGas.getText().toString().length() > 0 && edtEtanol.getText().toString().length() > 0) {
            double pGas = Double.parseDouble(edtGas.getText().toString());
            double pEta = Double.parseDouble(edtEtanol.getText().toString());
            if (pEta / pGas > 0.70) {
                bestChoice = CHOICE_GAS;
            } else {
                bestChoice = CHOICE_ETA;
            }
        }
        setBestChoiceMessage(bestChoice);
    }

    private void setBestChoiceMessage(int choice) {
        String msgBestChoice = getString(R.string.msg_bestchoice);
        if (bestChoice != CHOICE_NOTHING) {
            if (bestChoice == CHOICE_ETA) {
                msgBestChoice += getString(R.string.Etanol);
            } else {
                msgBestChoice += getString(R.string.Gas);
            }
        } else {
            msgBestChoice = getString(R.string.msg_camposobrigatorios);
        }
        txtBestChoice.setText(msgBestChoice);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_BESTCHOICE, bestChoice);
    }
}
