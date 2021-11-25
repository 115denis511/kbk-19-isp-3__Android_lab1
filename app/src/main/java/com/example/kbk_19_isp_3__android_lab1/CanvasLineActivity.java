package com.example.kbk_19_isp_3__android_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class CanvasLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawView dv = new DrawView(this);

        Bundle arguments = getIntent().getExtras();
        dv.setColor(arguments.getString("color"));
        dv.setType(arguments.getString("type"));

        setContentView(dv);
    }

    private class DrawView extends View {

        Paint brush;
        int hightX;
        int hightY;
        int lowX;
        int lowY;
        int barXSize;
        int barYSize;
        int markYMax;
        int[] graf;
        String color;
        String grafType;

        public DrawView(Context context) {
            super(context);

            brush = new Paint();
            markYMax = 100;
            graf = new int[]{10, 100, 30, 40, 70};
            color = "White";
            grafType = "Line";
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            hightX = 150;
            hightY = 100;
            lowX = size.x - 100;
            lowY = size.y - 331;
            barXSize = lowX - hightX;
            barYSize = lowY - hightY;

            canvas.drawColor(Color.BLACK);

            brush.setStrokeWidth(10);
            brush.setTextSize(40);
            brush.setColor(Color.WHITE);

            canvas.drawLine(hightX,hightY,hightX,lowY,brush);
            canvas.drawLine(lowX,lowY,hightX,lowY,brush);

            brush.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(Integer.toString(0), hightX - 30, lowY + 30, brush);

            int markCountY = barYSize / 150;
            float xY = (float)markYMax / (float)barYSize;
            for (int i = markCountY; i > 0; i--){
                drawMarkY(canvas, hightX, lowY - i * 150, Integer.toString(Math.round(xY * i * 150)));
            }

            brush.setTextAlign(Paint.Align.CENTER);
            int markCountX = graf.length + 1;
            float xX = (float)barXSize / (float)markCountX;
            for (int i = 1; i < markCountX; i++){
                drawMarkX(canvas, hightX + Math.round(i * xX), lowY, Integer.toString(i));
            }

            setBrushColor();
            if (grafType.equals("Line")){
                canvas.drawLine(hightX,lowY,hightX + Math.round(1 * xX), lowY - Math.round(graf[0] / xY), brush);
                for (int i = 1; i < graf.length; i++) {
                    canvas.drawLine(hightX + Math.round(i * xX), lowY - Math.round(graf[i - 1] / xY),
                            hightX + Math.round((i + 1) * xX), lowY - Math.round(graf[i] / xY), brush);
                }
            } else {
                for (int i = 1; i <= graf.length; i++) {
                    canvas.drawRect(hightX + Math.round(i * xX) - 20, lowY,
                            hightX + Math.round(i * xX) + 20, lowY - Math.round(graf[i - 1] / xY), brush);
                }
            }
        }

        void drawMarkY(Canvas canvas, int X, int Y, String text){
            canvas.drawLine(X - 20,Y,X + 20,Y,brush);
            canvas.drawText(text, X - 30, Y + 15, brush);
        }

        void drawMarkX(Canvas canvas, int X, int Y, String text){
            canvas.drawLine(X, Y - 20, X, Y + 20,brush);
            canvas.drawText(text, X, Y + 60, brush);
        }

        void setMarkYMax(int max){
            markYMax = max;
        }

        void setColor(String str){
            color = str;
        }

        void setType(String str){
            grafType = str;
        }

        void setBrushColor(){
            switch (color){
                case "White":
                    brush.setColor(Color.WHITE);
                    break;
                case "Red":
                    brush.setColor(Color.RED);
                    break;
                case "Blue":
                    brush.setColor(Color.BLUE);
                    break;
                case "Green":
                    brush.setColor(Color.GREEN);
                    break;
                default:
                    brush.setColor(Color.WHITE);
            }
        }
    }
}