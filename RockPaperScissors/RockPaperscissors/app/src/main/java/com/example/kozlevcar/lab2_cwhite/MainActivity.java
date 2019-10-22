
// Description:
// Build the rock paper scissor game 


package com.example.kozlevcar.lab2_cwhite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    // Java object references
    private ImageView ivComputer;
    private ImageView ivPlayer;
    private TextView tvScore;
    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;

    // random number generator for computer
    private Random generator;

    // constants for the game
    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;

    // game variables
    private int computer; // 0,1 or 2 for RPS
    private int player; // 0,1 or 2 for RPS
    private int playerScore = 0;
    private int computerScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // create Java object for imageViews
        ivComputer = findViewById(R.id.ivComputer);
        ivPlayer = findViewById(R.id.ivPlayer);

        // create Java object for Buttons
        btnRock = (Button)findViewById(R.id.btnRock);
        btnPaper = (Button)findViewById(R.id.btnPaper);
        btnScissors = (Button)findViewById(R.id.btnScissors);

        // create Java object for the score textView
        tvScore = (TextView)findViewById(R.id.tvScore);

        // create the random generator using current time as seed
        // If no seed, we will always get the same random pattern
        Calendar date = Calendar.getInstance();
        generator = new Random(date.getTimeInMillis());

        // listener for Rock button
        btnRock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // set the rock image to the player imageView
                ivPlayer.setImageResource(R.drawable.rock);

                // set the player variable to ROCK
                player = ROCK;

                // call the method to let the computer choose
                computerChoose();

                // call the method to check for the winner
                winner();

            }
        });

// listener for the Paper button
        btnPaper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // set the rock image to the player imageView
                ivPlayer.setImageResource(R.drawable.paper);

                // set the player variable to ROCK
                player = PAPER;

                // call the method to let the computer choose
                computerChoose();

                // call the method to check for the winner
                winner();
            }
        });


// listener for the Paper button
        btnScissors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // set the rock image to the player imageView
                ivPlayer.setImageResource(R.drawable.scissors);

                // set the player variable to ROCK
                player = SCISSORS;

                // call the method to let the computer choose
                computerChoose();

                // call the method to check for the winner
                winner();
            }
        });




    }

    private void computerChoose()
    {
        // generate a random value for the computer
        // nextInt will generate values 0,1 or 2 for Rock, Paper or Scissors
        computer = generator.nextInt(3);

        // set the computers image based on random generated value
        if(computer == ROCK)
        {
            ivComputer.setImageResource(R.drawable.rock);
        }
        if(computer == PAPER)
        {
            ivComputer.setImageResource(R.drawable.paper);
        }
        if(computer == SCISSORS)
        {
            ivComputer.setImageResource(R.drawable.scissors);
        }

    }

    // determine the winner
    private void winner()
    {
        // player has Rock
        if(player == ROCK)
        {
            if(computer == ROCK)
            {
                // its a tie
                tvScore.setText("It's a Tie!");
            }
            if(computer == PAPER)
            {
                // computer wins
                ++computerScore;
                tvScore.setText("You Lose: " + playerScore + " to " + computerScore);
            }
            if(computer == SCISSORS)
            {
                // player wins
                ++playerScore;
                tvScore.setText("You Win: " + playerScore + " to " + computerScore);
            }
        }

        // player has Paper
        if(player == PAPER)
        {
            if(computer == PAPER)
            {
                // its a tie
                tvScore.setText("It's a Tie!");
            }
            if(computer == SCISSORS)
            {
                // computer wins
                ++computerScore;
                tvScore.setText("You Lose: " + playerScore + " to " + computerScore);
            }
            if(computer == ROCK)
            {
                // player wins
                ++playerScore;
                tvScore.setText("You Win: " + playerScore + " to " + computerScore);
            }
        }

        // player has Scissors
        if(player == SCISSORS)
        {
            if(computer == SCISSORS)
            {
                // its a tie
                tvScore.setText("It's a Tie!");
            }
            if(computer == ROCK)
            {
                // computer wins
                ++computerScore;
                tvScore.setText("You Lose: " + playerScore + " to " + computerScore);
            }
            if(computer == PAPER)
            {
                // player wins
                ++playerScore;
                tvScore.setText("You Win: " + playerScore + " to " + computerScore);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
