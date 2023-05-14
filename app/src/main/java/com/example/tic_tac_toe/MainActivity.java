package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive =true;
    //Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meaning:
    // 0 - X
    // 1 - O
    // 2 - null
    int[][] winPositions = {{1,2,3},{4,5,6}, {7,8,9},
                            {1,4,7},{2,5,8},{3,6,9},
                            {1,5,9},{3,5,7}};
    public static int counter = 0;

    public void Playertap(View view){
        ImageView img= (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }// if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap
            counter++;

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false;
            }
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0) {
                img.setImageResource(R.drawable.cross2);
                activePlayer = 1;
                TextView status = findViewById(R.id.Status);
                status.setText("O's Turn - Tap to play");
            } else{
                img.setImageResource(R.drawable.zero1);
                activePlayer = 0;
                TextView status = findViewById(R.id.Status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
            if (counter==9){
                TextView status = findViewById(R.id.Status);
                status.setText("Match Draw");
            }

        }
        int flag = 0;
        // Check if any player has won

        for (int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]]!=2){
                flag = 1;
                // Somebody has won! -find out who!

                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }
                TextView status = findViewById(R.id.Status);
                status.setText(winnerStr);

            } else{
                flag= 0;
            }
        }
        if (counter > 9 && flag == 0) {
            TextView status = findViewById(R.id.Status);
            status.setText("Match Draw");
            gameReset(view);
    }

    }


    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        TextView status = findViewById(R.id.Status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
