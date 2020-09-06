package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2 is empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean fullBoard = false;

    int[][] winningPositions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {


            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow_piece);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_piece);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            String winner = "";
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2) {
                    gameActive = false;


                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else if (activePlayer == 0) {
                        winner = "Red";
                    }


                    winnerTextView.setText(winner + " has won");
                    playAgainButton.animate().alpha(1).setDuration(1000);
                    winnerTextView.animate().alpha(1).setDuration(1000);
                } else {
                    fullBoard = true;
                    for (int i = 1; i < gameState.length; i++) {
                        if (gameState[i] == 2) {
                            fullBoard = false;

                        }



                    }
                    if(fullBoard){
                        winnerTextView.setText("There is no winner");
                        playAgainButton.animate().alpha(1).setDuration(1000);
                        winnerTextView.animate().alpha(1).setDuration(1000);
                    }
                }


            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.animate().alpha(0).setDuration(1000);
        winnerTextView.animate().alpha(0).setDuration(1000);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);


        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }


        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}