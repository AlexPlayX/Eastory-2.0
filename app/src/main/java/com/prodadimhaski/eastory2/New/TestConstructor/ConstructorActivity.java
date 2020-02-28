package com.prodadimhaski.eastory2.New.TestConstructor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.DataAdapter;
import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.FullListConstructor;
import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.Question;
import com.prodadimhaski.eastory2.R;

public class ConstructorActivity extends AppCompatActivity implements Language, TypeOfTest {
    String[] periods;
    Question[] questions;
    Spinner periodsSpinner;

    RecyclerView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructor);

        if (change.getLanguage().equals("ru")) {
            periods = new String[]{getResources().getString(R.string.antiquity_ru),
                    getResources().getString(R.string.medival_ru),
                    getResources().getString(R.string.new_ru),
                    getResources().getString(R.string.newtime_ru),
                    getResources().getString(R.string.soviets_ru),
                    getResources().getString(R.string.newesttime_ru)};
        } else periods = new String[]{getResources().getString(R.string.antiquity_by),
                getResources().getString(R.string.medival_by),
                getResources().getString(R.string.new_by),
                getResources().getString(R.string.newtime_by),
                getResources().getString(R.string.soviets_by),
                getResources().getString(R.string.newesttime_by)};

        periodsSpinner = findViewById(R.id.periods);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodsSpinner.setAdapter(adapter);

        questionView = findViewById(R.id.questionList);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FullListConstructor fullListConstructor = new FullListConstructor(getApplicationContext());
                questions = fullListConstructor.createFullList(TYPEOFTTEST[periodsSpinner.getSelectedItemPosition()]);
                /*ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, questions.getQuestion());
                questionView.setAdapter(listViewAdapter);
                questionView.deferNotifyDataSetChanged();*/
                DataAdapter dataAdapter = new DataAdapter(getApplicationContext(),questions);
                questionView.setAdapter(dataAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}