package com.example.irving.judge;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;


public class SecondaryActivity extends ActionBarActivity {



    private ImageView shadow;
    //private ImageView chars;



    //private AnimationDrawable leftNumAnimation;

    //private ImageView leftNumCharsContainer;
    //private AnimationDrawable leftNumAnimationContainer;


    private AnimationDrawable myAnimation; //important
    private ImageView leftNumView;
//    private ImageView rightNum;
    private boolean allowButtonPress = true;
    private boolean gameStarted = false;
    private int leftScore = 0;
    private int rightScore = 0;
    private int playTo = 10;
    private TextView texttt;
    private ImageView charsContainer;
    private AnimationDrawable animationContainer;
    //private int input = -1; //(for managing button presses, 1 - 4)
    private Random rand = new Random();
    private int leftNum;
    private int rightNum;
    /*private ImageButton leftboopcopy;
    private ImageButton leftwhoacopy;
    private ImageButton rightboopcopy;
    private ImageButton rightwhoacopy;*/



    public void startGame()
    {
        leftScore = 0;
        rightScore = 0;
        startRound();
    }

    public void startRound()
    {
        allowButtonPress = false; //so they dont hit the buttons during the countdown

        if(leftScore == playTo || rightScore == playTo) //game over
        {
            gameStarted = false;
            //allowButtonPress = true; //to allow them to start another game... also check game started on each button press? idk


        }
        else{ //if playTo is not yet reached, that is, the game is still in session
            charsContainer.setBackgroundResource(R.drawable.animation);
            animationContainer = (AnimationDrawable) charsContainer.getBackground();
            animationContainer.start(); //this should work?

            checkIfAnimationIsDoneAndAlsoDisableButtonPressesUntilThatTime(animationContainer); //plays to the end of windup animation, aka frame 9, where the signs go up, then stops
            leftNum = rand.nextInt((8) + 1) + 1;
            rightNum = rand.nextInt((8) + 1) + 1;

            //leftNum = 9; //TEMP 4 TESTING
            //rightNum = 9; //TEMP 4 TESTING

            //texttt.setText(Integer.toString(leftNum) + " " + Integer.toString(rightNum)); //this is for debugging

            //so we're stuck here until someone presses a button and throws us into whoWon

            // V V V V V

        }


    }

    public void whoWon(String caller) {

        if (caller == "leftboop")
        {
            if(leftNum >= rightNum) //left sees he's of equal or greater toughness and drops the hammer
            {
                //left wins, give him a point
                leftScore ++;
                texttt.setText(Integer.toString(leftScore) + "   " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "LEFT WINS!", Toast.LENGTH_SHORT).show();


                //play the appropriate animation (LEFTSUCCESSFULBOOP)
                charsContainer.setBackgroundResource(R.drawable.leftsuccessfulboop);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
                //texttt.setText(Boolean.toString(allowButtonPress));
                //allowButtonPress = false; //humans probably can't click fast enough to hit it during true.. this should be fine



            }
            else //left was weaker but still went for the boop
            {
                //right wins, give him a point
                rightScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "RIGHT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (LEFTFAILEDBOOP)
                charsContainer.setBackgroundResource(R.drawable.leftfailedboop);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
        }
        else if(caller == "leftwhoa")
        {
            if(leftNum < rightNum) //left saw he was weaker and backed out. good call
            {
                //left wins, give him a point
                leftScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "LEFT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (LEFTSUCCESSFULWHOA)
                charsContainer.setBackgroundResource(R.drawable.leftsuccessfulwhoa);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
            else //left backed out even though he was of equal or greater toughness. c'mon guy...
            {
                //right wins, give him a point
                rightScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "RIGHT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (LEFTFAILEDWHOA)
                charsContainer.setBackgroundResource(R.drawable.leftfailedwhoa);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
        }
        else if (caller == "rightboop")
        {
            if(rightNum >= leftNum) //right sees he's of equal or greater toughness and drops the hammer
            {
                //right wins, give him a point
                rightScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "RIGHT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (RIGHTSUCCESSFULBOOP)
                charsContainer.setBackgroundResource(R.drawable.rightsuccessfulboop);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
            else //right was weaker but still went for the boop
            {
                //left wins, give him a point
                leftScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "LEFT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (RIGHTFAILEDBOOP)
                charsContainer.setBackgroundResource(R.drawable.rightfailedboop);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
        }
        else if (caller == "rightwhoa")
        {
            if(rightNum < leftNum) //right saw he was weaker and backed out. good call
            {
                //right wins, give him a point
                rightScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "RIGHT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (RIGHTSUCCESSFULWHOA)
                charsContainer.setBackgroundResource(R.drawable.rightsuccessfulwhoa);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
            else //right backed out even though he was of equal or greater toughness. c'mon guy...
            {
                //left wins, give him a point
                leftScore++;
                texttt.setText(Integer.toString(leftScore) + " " + Integer.toString(rightScore));
                //Toast.makeText(getApplicationContext(), "LEFT WINS!", Toast.LENGTH_SHORT).show();
                //play the appropriate animation (RIGHTFAILEDWHOA)
                charsContainer.setBackgroundResource(R.drawable.rightfailedwhoa);
                animationContainer = (AnimationDrawable) charsContainer.getBackground();
                animationContainer.start();

                checkIfSpecial(animationContainer);
            }
        }
    }


    //ALSO it calls the startRound() once this win/loss animation is over
    private void checkIfSpecial(AnimationDrawable anim){ //so this one does what the long name one does BUT this one doesn't let you press buttons after.
        allowButtonPress = false;

        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                //if (a.getCurrent() != a.getFrame(9)) { //goes all the way to frame 9, raised signs
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){ //this version just looks for last frame of animation
                    checkIfSpecial(a); //recurse here (lol that was a funny bug)
                }
                else{
                    //Toast.makeText(getApplicationContext(), "ANIMATION DONE*!", Toast.LENGTH_SHORT).show();
                    a.stop();
                    allowButtonPress = false; //setting it to false again... really false i guess
                    startRound();
                }
            }
        }, timeBetweenChecks);
    }

    private void checkIfAnimationIsDoneAndAlsoDisableButtonPressesUntilThatTime(AnimationDrawable anim){
        allowButtonPress = false;



        final ImageView leftNumView = (ImageView) findViewById(R.id.leftNumView);
        leftNumView.setVisibility(View.INVISIBLE); //so we can load stuff
        if(leftNum == 1) {
            leftNumView.setImageResource(R.drawable.left1);
        }
        if(leftNum == 2) {
            leftNumView.setImageResource(R.drawable.left2);
        }
        if(leftNum == 3) {
            leftNumView.setImageResource(R.drawable.left3);
        }
        if(leftNum == 4) {
            leftNumView.setImageResource(R.drawable.left4);
        }
        if(leftNum == 5) {
            leftNumView.setImageResource(R.drawable.left5);
        }
        if(leftNum == 6) {
            leftNumView.setImageResource(R.drawable.left6);
        }
        if(leftNum == 7) {
            leftNumView.setImageResource(R.drawable.left7);
        }
        if(leftNum == 8) {
            leftNumView.setImageResource(R.drawable.left8);
        }
        if(leftNum == 9) {
            leftNumView.setImageResource(R.drawable.left9);
        }

        final ImageView rightNumView = (ImageView) findViewById(R.id.rightNumView);
        rightNumView.setVisibility(View.INVISIBLE);

        if(rightNum == 1) {
            rightNumView.setImageResource(R.drawable.right1);
        }
        if(rightNum == 2) {
            rightNumView.setImageResource(R.drawable.right2);
        }
        if(rightNum == 3) {
            rightNumView.setImageResource(R.drawable.right3);
        }
        if(rightNum == 4) {
            rightNumView.setImageResource(R.drawable.right4);
        }
        if(rightNum == 5) {
            rightNumView.setImageResource(R.drawable.right5);
        }
        if(rightNum == 6) {
            rightNumView.setImageResource(R.drawable.right6);
        }
        if(rightNum == 7) {
            rightNumView.setImageResource(R.drawable.right7);
        }
        if(rightNum == 8) {
            rightNumView.setImageResource(R.drawable.right8);
        }
        if(rightNum == 9) {
            rightNumView.setImageResource(R.drawable.right9);
        }


        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){

                //leftNumCharsContainer.setBackgroundResource(R.drawable.left1);


                //if (a.getCurrent() != a.getFrame(9)) { //goes all the way to frame 9, raised signs
                /*if(a.getCurrent() == a.getFrame(a.getNumberOfFrames() - 3)){
                    leftNumCharsContainer.setBackgroundResource(R.drawable.left1); //because lag?
                }*/

                    if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){ //this version just looks for last frame of animation
                        checkIfAnimationIsDoneAndAlsoDisableButtonPressesUntilThatTime(a); //recurse here
                }
                else{
                    //Toast.makeText(getApplicationContext(), "ANIMATION DONE!", Toast.LENGTH_SHORT).show();
                        leftNumView.setVisibility(View.VISIBLE);
                        rightNumView.setVisibility(View.VISIBLE);
                        a.stop();

                        //leftNumView.setImageResource(R.drawable.left1);
//                        leftNumView.setVisibility(View.VISIBLE);


                    allowButtonPress = true; //windup is done, you can use buttons now
                }
            }
        }, timeBetweenChecks);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        playTo = Integer.parseInt(message);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        final TextView text=(TextView)findViewById(R.id.textVieww);
        texttt = text;

        //text.setText(messagefromintent);
        final ImageButton leftboop = (ImageButton) findViewById(R.id.button1);
        leftboop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(!gameStarted)
                {
                    gameStarted = true;
                    startGame();
                }
                if(allowButtonPress == true){
                //text.setText("leftboop >:D");
                    whoWon("leftboop");
                }

                else{
                    //text.setText("leftboop disallowed");
                }
            }
        });
        final ImageButton leftwhoa = (ImageButton) findViewById(R.id.button2);
        leftwhoa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(!gameStarted)
                {
                    gameStarted = true;
                    startGame();
                }
                if(allowButtonPress == true){
                    //text.setText("leftwhoa :0");
                    whoWon("leftwhoa");}
                else{
                    //text.setText("leftwhoa disallowed");
                }
            }
        });
        final ImageButton rightboop = (ImageButton) findViewById(R.id.button3);
        rightboop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(!gameStarted)
                {
                    gameStarted = true;
                    startGame();
                }
                if(allowButtonPress == true){
                    //text.setText("rightboop >:D");}
                    whoWon("rightboop");}
                else{
                    //text.setText("rightboop disallowed");
                }
            }
        });
        final ImageButton rightwhoa = (ImageButton) findViewById(R.id.button4);
        rightwhoa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(!gameStarted)
                {
                    gameStarted = true;
                    startGame();
                }
                if(allowButtonPress == true){
                    //text.setText("rightwhoa :0");}
                    whoWon("rightwhoa");}
                else{
                    //text.setText("rightwhoa disallowed");
                }
            }
        });


        /*ImageView chars = (ImageView) findViewById(R.id.charsView);
        ImageView shadow = (ImageView) findViewById(R.id.shadowView);
        ImageView leftNum = (ImageView) findViewById(R.id.leftNumView);
        ImageView rightNum = (ImageView) findViewById(R.id.rightNumView);*/


        //chars.setImageResource(R.drawable.a); //leave it as blank 4 now





        ImageView charsView = (ImageView) findViewById(R.id.charsView);
        charsView.setBackgroundResource(R.drawable.animation);
        myAnimation = (AnimationDrawable) charsView.getBackground();

        charsContainer = charsView;
        animationContainer = myAnimation;

//        ImageView leftNumView = (ImageView) findViewById(R.id.leftNumView);
//        leftNumView.setImageResource(R.drawable.left1); //TOTALLY WORKING YOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO asd asd








        //myAnimation = (AnimationDrawable) shadow.getBackground(); //SOMETHING YOU CAN DO
        //myAnimation.start();

        //startGame(); //*********IMPORTANT BRING THIS BACK**********

        /*asyncStuff async = new asyncStuff();
        async.execute();*/
    }

    /*public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            myAnimation.start();

            return true;
        }
        return super.onTouchEvent(event);
    }*/

//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            myAnimation.start();
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_secondary, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*    private class asyncStuff extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                startGame();


                texttt.setText("FOR REALz");
            }
            catch (Exception uhoh) {
                Log.e("string", "stuck in async", uhoh);
            }
            return null;
        }
    }*/


}
