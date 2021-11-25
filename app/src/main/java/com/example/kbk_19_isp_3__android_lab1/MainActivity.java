package com.example.kbk_19_isp_3__android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button mButtonInputData;
    public Button mButtonChoose;
    public Button mButtonLine;
    public Button mButtonBar;
    public Button mButtonQuit;

    public MainDialogData mData;
    public MainChooseDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = new MainDialogData();
        mDialog = new MainChooseDialog(getContext(), getResources(), mData);

        mButtonInputData = findViewById(R.id.main_buttonInputData);
        mButtonChoose = findViewById(R.id.main_buttonChoose);
        mButtonLine = findViewById(R.id.main_buttonLine);
        mButtonBar = findViewById(R.id.main_buttonBar);
        mButtonQuit = findViewById(R.id.main_buttonQuit);

        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialogResult();
            }
        });

        mButtonInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeToast(mData.mCurrentColor);
            }
        });

        mButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();
            }
        });

        mButtonLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CanvasLineActivity.class);
                intent.putExtra("color", mData.mCurrentColor);
                intent.putExtra("type", "Line");
                startActivity(intent);
            }
        });

        mButtonBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CanvasLineActivity.class);
                intent.putExtra("color", mData.mCurrentColor);
                intent.putExtra("type", "Bar");
                startActivity(intent);
            }
        });

        mButtonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }

    public void makeToast(String str) {
        Toast toast = Toast.makeText(this, str,Toast.LENGTH_LONG);
        toast.show();
    }

    public Context getContext(){
        return this;
    }

    public void dialogResult(){
        if (mData.mCurrentChecked.equals("Line")){
            mButtonLine.setEnabled(true);
            mButtonBar.setEnabled(false);
        } else if (mData.mCurrentChecked.equals("Bar")){
            mButtonLine.setEnabled(false);
            mButtonBar.setEnabled(true);
        }
    }
}