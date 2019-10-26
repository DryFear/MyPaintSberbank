package ru.unfortunately.school.mypaint;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import ru.unfortunately.school.mypaint.Adapters.ColorSpinnerAdapter;
import ru.unfortunately.school.mypaint.Adapters.DrawTypeSpinnerAdapter;
import ru.unfortunately.school.mypaint.Models.DrawTypes;
import ru.unfortunately.school.mypaint.Models.MyColors;

public class MainActivity extends AppCompatActivity {

    private Button mButtonClear;
    private Button mButtonScroll;
    private DrawView mDrawView;

    private Spinner mTypesSpinner;
    private Spinner mColorsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initSpinners();
        mButtonClear.setOnClickListener(this::onClearClick);
        mButtonScroll.setOnClickListener(this::onScrollClick);
        mDrawView.setColor(((MyColors)mColorsSpinner.getSelectedItem()).mColor);
        mDrawView.setDrawType((DrawTypes)(mTypesSpinner.getSelectedItem()));
    }

    private void initSpinners() {
        ColorSpinnerAdapter colorAdapter = new ColorSpinnerAdapter(Arrays.asList(MyColors.values()));
        mColorsSpinner.setAdapter(colorAdapter);
        mColorsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDrawView.setColor(((MyColors)mColorsSpinner.getItemAtPosition(position)).mColor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DrawTypeSpinnerAdapter drawTypesAdapter = new DrawTypeSpinnerAdapter(Arrays.asList(DrawTypes.values()));
        mTypesSpinner.setAdapter(drawTypesAdapter);
        mTypesSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDrawView.setDrawType((DrawTypes)(mTypesSpinner.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initViews() {
        mButtonClear = findViewById(R.id.btn_clear);
        mDrawView = findViewById(R.id.draw_view);
        mColorsSpinner = findViewById(R.id.color_spinner);
        mTypesSpinner = findViewById(R.id.draw_mode_spinner);
        mButtonScroll = findViewById(R.id.btn_scroll);
    }


    private void onScrollClick(View view) {
        mDrawView.switchScrollMode();
    }


    private void onClearClick(View view){
        mDrawView.clear();
    }
}
