package com.example.kbk_19_isp_3__android_lab1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class MainChooseDialog extends Dialog {

    public TextView mTextColor;
    public ListView mListColor;
    public String[] mColors;
    public ArrayAdapter<String> mListColorAdapter;
    public RadioGroup mRadio;
    public Button mButtonCommit;

    private Resources mResources;
    private String mCurrentColor;
    private String mCurrentChecked;
    public MainDialogData mData;

    public MainChooseDialog(@NonNull Context context, Resources resources, MainDialogData data) {
        super(context);
        setContentView(R.layout.activity_main_choose_dialog);

        mResources = resources;
        mCurrentColor = "White";
        mCurrentChecked = "Line";
        mData = data;

        mTextColor = findViewById(R.id.mainDialog_textColor);
        setTextColor("White");
        mListColor = findViewById(R.id.mainDialog_listColor);
        mColors = new String[] { "White", "Red", "Blue", "Green"};
        mListColorAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, mColors);
        mListColor.setAdapter(mListColorAdapter);
        mRadio = findViewById(R.id.mainDialog_radioGroup);
        mButtonCommit = findViewById(R.id.mainDialog_buttonCommit);

        mListColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentColor = mColors[i];
                setTextColor(mCurrentColor);
            }
        });

        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.mainDialog_radioGroup_Line:
                        mCurrentChecked = "Line";
                        break;
                    case R.id.mainDialog_radioGroup_Bar:
                        mCurrentChecked = "Bar";
                        break;
                    default:
                        break;
                }
            }
        });

        mButtonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.mCurrentColor = mCurrentColor;
                mData.mCurrentChecked = mCurrentChecked;
                cancel();
            }
        });
    }

    void setTextColor(String s){
        mTextColor.setText(mResources.getString(R.string.main_dialog_textColor) + " " + s);
    }
}
