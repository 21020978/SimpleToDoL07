package sg.edu.rp.c346.id21020978.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvAddOrDltTask;
    EditText etTask;
    Button btnAdd, btnDlt, btnClr;
    Spinner spnTaskOrDlt;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAddOrDltTask = findViewById(R.id.tvAddOrRmvTask);
        etTask = findViewById(R.id.etAddTask);
        spnTaskOrDlt = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAddTask);
        btnDlt = findViewById(R.id.btnDeleteTask);
        btnClr = findViewById(R.id.btnClearTask);
        lvTask = findViewById(R.id.lvTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);

        spnTaskOrDlt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        tvAddOrDltTask.setText("Add new Task");
                        btnDlt.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        tvAddOrDltTask.setText("Type the index number of task in which you want to remove");
                        btnAdd.setEnabled(false);
                        btnDlt.setEnabled(true);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addTask = etTask.getText().toString();
                alTask.add(addTask);
                aaTask.notifyDataSetChanged();

            }
        });

        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(etTask.getText().toString()) ;
                 pos=pos-1;

                if (alTask.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "You don't have any task", Toast.LENGTH_LONG).show();
                } else if (pos > alTask.size()) {
                    Toast.makeText(MainActivity.this, "Incorrect index number", Toast.LENGTH_LONG).show();
                } else {
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                }

            }
        });

        btnClr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alTask.clear();
                aaTask.notifyDataSetChanged();

            }
        });

    }
}