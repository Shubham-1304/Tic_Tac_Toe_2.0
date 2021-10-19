package com.example.connect3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class tictactoeBoard extends View {
    private final int boardColor;
    private final int xColor;
    private final int oColor;
    private final int winningLineColor;
    private final Paint paint=new Paint();
    private int cellSize=getWidth()/3;
    Bitmap coin1;
    public tictactoeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.tictactoeBoard,0,0);
        coin1=BitmapFactory.decodeResource(getResources(),R.drawable.yellow);
        try{
            boardColor=a.getInteger(R.styleable.tictactoeBoard_boardColor,0);
            xColor=a.getInteger(R.styleable.tictactoeBoard_xColor,0);
            oColor=a.getInteger(R.styleable.tictactoeBoard_oColor,0);
            winningLineColor=a.getInteger(R.styleable.tictactoeBoard_winningLineColor,0);

        }
        finally {
            a.recycle();
        }
    }
    @Override
    protected void onMeasure(int width,int height){
        super.onMeasure(width,height);
        int dimension=Math.min(getMeasuredWidth(),getMeasuredHeight());
        cellSize=dimension/3;
        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){

        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawGameBoard(canvas);
        //canvas.drawBitmap(coin1,[1,2],null);

    }

    private void drawGameBoard(Canvas canvas){
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for (int c=1;c<3; c++){
            canvas.drawLine(cellSize*c,0,cellSize*c,canvas.getWidth(),paint);
        }
        for (int r=1;r<3; r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r,paint);
        }
    }
    private void drawCoin1(Canvas canvas,int row,int col){



    }
}
