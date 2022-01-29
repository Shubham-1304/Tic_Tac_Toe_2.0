package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.lang.reflect.Array;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Board extends AppCompatActivity {
    int alternative;
    Button play;
    TextView text;
    int n=0;
    int w=0;
    int[] state={2,2,2,2,2,2,2,2,2};
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    Integer[] tagPostion={0,0,0,0,0,0,0,0,0};
    int[] pallete={R.color.col1,R.color.col2,R.color.col3,R.color.col4,R.color.col5,R.color.col6,};
    int coinPicked=0;
    int tag;
    int coinCount=0;
    String name1;
    String name2;

    ImageView coin;
    int tagNo=0;
    public void coinchose(View view){
        coin=(ImageView) view;

        //coin.getDrawable().toString()
        tag=Integer.parseInt(coin.getTag().toString());
        if ((tagNo%2==0 && Integer.parseInt(coin.getTag().toString())%2==0) || (tagNo%2!=0 && Integer.parseInt(coin.getTag().toString())%2!=0)) {
            Toast.makeText(this, coin.getTag().toString(), Toast.LENGTH_SHORT).show();
            coinPicked = 0;
        }
        else {
            //tagNo=Integer.parseInt(coin.getTag().toString());
            coinPicked = 1;
        }
    }

    public void box8(View view) {
        try {
            CircularImageView c8 = (CircularImageView) view;
            //ImageView c8 = (ImageView) view;
            int tapped = Integer.parseInt(c8.getTag().toString());
            //state[tapped] = n;

            if (state[tapped] == 2 && w == 0) {
                if (n == 0 && state[tapped] == 2 && coinPicked==1 ) {
                    tagNo=Integer.parseInt(coin.getTag().toString());
                    c8.setImageResource(R.drawable.red);
                    state[tapped] = n;
                    n = 1;
                    coinPicked=0;
                    coinCount+=1;
                    tagPostion[tapped]=tag;
                    coin.setVisibility(View.INVISIBLE);
                } else if (n == 1 && state[tapped] == 2 && coinPicked==1 ) {
                    tagNo=Integer.parseInt(coin.getTag().toString());
                    c8.setImageResource(R.drawable.yellow);
                    state[tapped] = n;
                    n = 0;
                    coinPicked=0;
                    tagPostion[tapped]=tag;
                    coinCount+=1;
                    coin.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(this, "Choose correct coin", Toast.LENGTH_SHORT).show();
                }
                int ct = 0;
                String winner = "";

                for (int[] wins : win) {
                    if (state[wins[0]] == state[wins[1]] && state[wins[1]] == state[wins[2]] && state[wins[0]] == 0 && w == 0) {
                        winner = "Red";
                        text.setText(name1 + " has won ");
                        play.setText("PLAY AGAIN");
                        text.setVisibility(View.VISIBLE);
                        play.setVisibility(View.VISIBLE);
                        w = 1;
                        Random ran = new Random();
                        int x = ran.nextInt(pallete.length);
                        //text.setTextColor(this.getResources().getColor(pallete[x]));
                        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
                        konfettiView.build()
                                .addColors( Color.MAGENTA,Color.RED)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                                .addSizes(new Size(12, 5f))
                                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                .streamFor(300, 5000L);

                        break;
                    } else if (state[wins[0]] == state[wins[1]] && state[wins[1]] == state[wins[2]] && state[wins[0]] == 1 && w == 0) {
                        winner = "Yellow";
                        text.setText(name2 + " has won ");
                        play.setText("PLAY AGAIN");
                        text.setVisibility(View.VISIBLE);
                        play.setVisibility(View.VISIBLE);
                        w = 1;
                        Random ran = new Random();
                        int x = ran.nextInt(pallete.length);
                        //text.setTextColor(this.getResources().getColor(pallete[x]));
                        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
                        konfettiView.build()
                                .addColors(Color.YELLOW)
                                .setDirection(90.0,90.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                                .addSizes(new Size(12, 5f))
                                .setPosition( konfettiView.getWidth() + 50f,-50f, 0f, 1200f)

                                .streamFor(300, 5000L);

                        break;
                    } else if (state[wins[0]] == 2 || state[wins[1]] == 2 || state[wins[2]] == 2)
                        ct += 1;
                }
                if ((ct == 0 && w == 0) || coinCount==12 ) {
                    winner = "No one";
                    text.setText(winner + " has won ");
                    text.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);
                }
                c8.setTranslationY(-1000);
                c8.animate().translationYBy(1000).setDuration(500);
            }else if (state[tapped]!=2 ){
                if ((int) Array.get(tagPostion,tapped)%2==0 && tag>(int)Array.get(tagPostion,tapped) && (tag-(int)Array.get(tagPostion,tapped))>=3 && (tag-(int)Array.get(tagPostion,tapped))%2!=0) {
                    Toast.makeText(this, "coin override", Toast.LENGTH_SHORT).show();
                    tagNo=Integer.parseInt(coin.getTag().toString());
                    c8.setImageResource(R.drawable.red);
                    state[tapped] = n;
                    n = 1;
                    coinPicked = 0;
                    coinCount+=1;
                    tagPostion[tapped] = tag;
                    coin.setVisibility(View.INVISIBLE);

                }
                else if((int)Array.get(tagPostion,tapped)%2!=0 && tag>(int)Array.get(tagPostion,tapped) && (tag-(int)Array.get(tagPostion,tapped))>=1 && (tag-(int)Array.get(tagPostion,tapped))%2!=0) {
                    c8.setImageResource(R.drawable.yellow);
                    tagNo=Integer.parseInt(coin.getTag().toString());
                    state[tapped] = n;
                    n = 0;
                    coinPicked = 0;
                    coinCount+=1;
                    tagPostion[tapped] = tag;
                    coin.setVisibility(View.INVISIBLE);

                }
                else{
                    Toast.makeText(this, "Choose correct coin", Toast.LENGTH_SHORT).show();
                }
                int ct = 0;
                String winner = "";

                for (int[] wins : win) {
                    if (state[wins[0]] == state[wins[1]] && state[wins[1]] == state[wins[2]] && state[wins[0]] == 0 && w == 0) {
                        winner = "Red";
                        text.setText(name1  + " has won ");
                        play.setText("PLAY AGAIN");
                        text.setVisibility(View.VISIBLE);
                        play.setVisibility(View.VISIBLE);
                        w = 1;
                        Random ran = new Random();
                        int x = ran.nextInt(pallete.length);
                        text.setTextColor(this.getResources().getColor(pallete[x]));
                        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
                        konfettiView.build()
                                .addColors( Color.MAGENTA,Color.RED)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                                .addSizes(new Size(12, 5f))
                                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                .streamFor(300, 5000L);


                        break;
                    } else if (state[wins[0]] == state[wins[1]] && state[wins[1]] == state[wins[2]] && state[wins[0]] == 1 && w == 0) {
                        winner = "Yellow";
                        text.setText(name2  + " has won ");
                        play.setText("PLAY AGAIN");
                        text.setVisibility(View.VISIBLE);
                        play.setVisibility(View.VISIBLE);
                        w = 1;
                        Random ran = new Random();
                        int x = ran.nextInt(pallete.length);
                        text.setTextColor(this.getResources().getColor(pallete[x]));
                        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
                        konfettiView.build()
                                .addColors(Color.YELLOW)
                                .setDirection(60.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                                .addSizes(new Size(12, 5f))
                                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                                .streamFor(300, 5000L);

                        break;
                    } else if (state[wins[0]] == 2 || state[wins[1]] == 2 || state[wins[2]] == 2)
                        ct += 1;
                }
                if ((ct == 0 && w == 0) || coinCount==12) {
                    winner = "No one";
                    text.setText(winner + " has won ");
                    text.setVisibility(View.VISIBLE);
                    play.setVisibility(View.VISIBLE);
                }
                c8.setTranslationY(-1000);
                c8.animate().translationYBy(1000).setDuration(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void again(View view){
        alternative++;
        //Toast.makeText(this, alternative, Toast.LENGTH_SHORT).show();
        /*KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                        .setDirection(0.0, 359.0)
                        .setSpeed(1f, 5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(2000L)
                        .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                        .streamFor(300, 5000L);*/
        play=(Button)findViewById(R.id.button);
        text=(TextView)findViewById(R.id.textView3);
        play.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView c8=(ImageView)grid.getChildAt(i);
            c8.setImageDrawable(null);
        }
        for (int i=0;i<state.length;i++){
            state[i]=2;
        }
        n=0;
        w=0;
        tagNo=0;
        if (alternative%2==0) {
            tagNo=0;
            n=0;
            Toast.makeText(getApplicationContext(), String.valueOf(alternative), Toast.LENGTH_SHORT).show();
        }
        else {
            tagNo = 1;
            n=1;
            Toast.makeText(getApplicationContext(), String.valueOf(alternative), Toast.LENGTH_SHORT).show();
        }
        for (int i=0;i<tagPostion.length;i++){
            tagPostion[i]=0;
        }

        for (Integer i=11;i<=16;i++) {
            int resID = getResources().getIdentifier("pc"+i.toString(), "id", getPackageName());
            ImageView p1 = findViewById(resID);
            p1.setVisibility(View.VISIBLE);
        }
        for (Integer i=21;i<=26;i++) {
            int resID = getResources().getIdentifier("pc"+i.toString(), "id", getPackageName());
            ImageView p1 = findViewById(resID);
            p1.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Intent intent=getIntent();
        name1=intent.getStringExtra("team1");
        name2=intent.getStringExtra("team2");
        alternative=0;

        play=(Button)findViewById(R.id.button);
        text=(TextView)findViewById(R.id.textView3);
        play.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);

        GridLayout grid=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView c8=(ImageView)grid.getChildAt(i);
            c8.setImageDrawable(null);
        }
        for (int i=0;i<state.length;i++){
            state[i]=2;
        }
        n=0;
        w=0;
        tagNo=0;
        coinCount=0;
        for (int i=0;i<tagPostion.length;i++){
            tagPostion[i]=0;
        }

        for (Integer i=11;i<=16;i++) {
            int resID = getResources().getIdentifier("pc"+i.toString(), "id", getPackageName());
            ImageView p1 = findViewById(resID);
            p1.setVisibility(View.VISIBLE);
        }
        for (Integer i=21;i<=26;i++) {
            int resID = getResources().getIdentifier("pc"+i.toString(), "id", getPackageName());
            ImageView p1 = findViewById(resID);
            p1.setVisibility(View.VISIBLE);
        }


    }
}