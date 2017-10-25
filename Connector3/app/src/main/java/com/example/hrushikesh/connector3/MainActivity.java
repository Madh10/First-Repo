package com.example.hrushikesh.connector3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int currentPlayer=0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningStates = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int flag = 0;
    boolean isActive = true;

    public void dropIn(View view) {

        TextView winner = (TextView) findViewById(R.id.winner);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        //layout.setVisibility(view.INVISIBLE);
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && isActive == true) {

            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = currentPlayer;

            if (currentPlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                currentPlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                currentPlayer = 0;
            }

            //System.out.println(gameState[tappedCounter]);

            counter.animate().translationYBy(1000f).setDuration(300);
            //currentPlayer++;

            for (int[] winningState: winningStates) {
                if (gameState[winningState[0]]==gameState[winningState[1]] && gameState[winningState[1]]==gameState[winningState[2]] && gameState[winningState[1]]==1 && gameState[winningState[0]]!=2) {
                    winner.setText("Red Won!");
                    isActive = false;
                    layout.setAlpha(1f);
                    layout.setVisibility(view.VISIBLE);
                    layout.animate().scaleX(1f).scaleY(1f).setDuration(1000);
                }
                else if (gameState[winningState[0]]==gameState[winningState[1]] && gameState[winningState[1]]==gameState[winningState[2]] && gameState[winningState[1]]==0 && gameState[winningState[0]]!=2) {
                    winner.setText("Yellow Won!");
                    isActive = false;
                    layout.setAlpha(1f);
                    layout.setVisibility(view.VISIBLE);
                    layout.animate().scaleX(1f).scaleY(1f).setDuration(1000);
                }
                else {
                    boolean IsOver = true;
                    for (int counterState: gameState) {
                        if(counterState == 2) {
                            IsOver = false;
                        }
                    }
                    if (IsOver) {
                        winner.setText("Draw!");
                        isActive = false;
                        layout.setAlpha(1f);
                        layout.setVisibility(view.VISIBLE);
                        layout.animate().scaleX(1f).scaleY(1f).setDuration(1000);
                    }

                }
            }

        }
    }

    public void playAgain(View view) {

        isActive = true;
        currentPlayer=0;

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.animate().scaleX(0f).scaleY(0f).setDuration(1000);
        //layout.setVisibility(view.INVISIBLE);

        for (int j = 0; j<gameState.length; j++) {
            gameState[j] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int j=0; j < gridLayout.getChildCount(); j++)
            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.setScaleY(0f);
        layout.setScaleX(0f);
    }
}
