package com.example.n1_programaaodedispositivosmoveis_yasminemarcello;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewTerrain;
    private EditText editTextDistance;
    private EditText editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewTerrain = findViewById(R.id.imageViewTerrain);
        editTextDistance = findViewById(R.id.editTextDistance);
        editTextTime = findViewById(R.id.editTextTime);

        RadioGroup radioGroupTerrain = findViewById(R.id.radioGroupTerrain);
        radioGroupTerrain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                switch (radioButton.getText().toString()) {
                    case "Asfalto":
                        imageViewTerrain.setImageResource(R.drawable.asfalto);
                        break;
                    case "Trilha":
                        imageViewTerrain.setImageResource(R.drawable.trilha);
                        break;
                    case "Pista":
                        imageViewTerrain.setImageResource(R.drawable.pista);
                        break;
                    case "Esteira":
                        imageViewTerrain.setImageResource(R.drawable.esteira);
                        break;
                    case "Outro":
                        // Lógica para outro tipo de terreno, se necessário
                        break;
                }
            }
        });

        Switch switchOfficialRun = findViewById(R.id.switchOfficialRun);
        switchOfficialRun.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Lógica para corrida oficial ativada
                    Toast.makeText(MainActivity.this, "Corrida oficial ativada", Toast.LENGTH_SHORT).show();
                    showOfficialRunAlertDialog();
                } else {
                    // Lógica para corrida oficial desativada
                    Toast.makeText(MainActivity.this, "Corrida oficial desativada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para salvar as informações inseridas pelo usuário
                String distancia = editTextDistance.getText().toString();
                String tempo = editTextTime.getText().toString();
                String terrenoSelecionado = getTerrenoSelecionado();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Informações Salvas");
                builder.setMessage("Distância: " + distancia + " km\nTempo: " + tempo + " minutos\nTerreno: " + terrenoSelecionado);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para limpar os campos de texto
                editTextDistance.setText("");
                editTextTime.setText("");
            }
        });
    }

    private void showOfficialRunAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação de Corrida Oficial");
        builder.setMessage("Esta corrida foi oficial?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Se o usuário confirmar que foi uma corrida oficial, você pode adicionar o código necessário aqui.
                Toast.makeText(MainActivity.this, "Corrida oficial confirmada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Corrida não oficial confirmada", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    // Método para obter o terreno selecionado
    private String getTerrenoSelecionado() {
        RadioGroup radioGroupTerrain = findViewById(R.id.radioGroupTerrain);
        int radioButtonId = radioGroupTerrain.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        return radioButton.getText().toString();
    }
}
