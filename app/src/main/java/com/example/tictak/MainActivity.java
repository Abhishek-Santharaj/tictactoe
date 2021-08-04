package com.example.tictak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activatePlayer = 0;
    boolean gameIsActive = true;

    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{6,4,2}};

    // 0 = yellow, 1= red, 2 = unplayed
    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedCounter] == 2 && gameIsActive) {

            gamestate[tappedCounter] = activatePlayer;

            counter.setTranslationY(-1000f);

            if (activatePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activatePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);
                activatePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] winningPosition : winningPositions){

                if(gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
                        && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                        && gamestate[winningPosition[0]] != 2){

                    System.out.println(gamestate[winningPosition[0]]);

                    // someone has won

                    gameIsActive = false;
                    String winner = "Red";

                    if(gamestate[winningPosition[0]] == 0){

                        winner = "Yellow";

                    }


                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }else {
                    boolean gameIsOver = true;
                    for(int counterstate : gamestate){

                        if(counterstate == 2) gameIsOver = true;

                    }
                    if (gameIsOver){

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);


                    }

                }
            }
        }

    }

    public void playAgain(View view){

        gameIsActive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

         activatePlayer = 0;

         for (int i=0; i <gamestate.length; i++){

             gamestate[i] = 2;
         }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

         for(int i =0; i<gridLayout.getChildCount(); i++){

             ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
         }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

