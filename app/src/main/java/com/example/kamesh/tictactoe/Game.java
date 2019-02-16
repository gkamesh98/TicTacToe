package com.example.kamesh.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.kamesh.tictactoe.Player.sentValue;

public class Game extends AppCompatActivity {
    TextView blocks[]=new TextView[10];
    TextView move[] = new TextView[3];
    TextView moves;
    Player play;
    int value;
    Random rand;
    String player[]=new String[2];
    String chance;
    int fill;
    int x;
    int o;
    int step;
    ArrayList<Integer>storex= new ArrayList<>();
    ArrayList<Integer>storeo= new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        blocks[1] = findViewById(R.id.textview1);
        blocks[2] = findViewById(R.id.textview2);
        blocks[3] = findViewById(R.id.textview3);
        blocks[4] = findViewById(R.id.textview4);
        blocks[5] = findViewById(R.id.textview5);
        blocks[6] = findViewById(R.id.textview6);
        blocks[7] = findViewById(R.id.textview7);
        blocks[8] = findViewById(R.id.textview8);
        blocks[9] = findViewById(R.id.textview9);
        move[1] = findViewById(R.id.move1);
        move[2] = findViewById(R.id.move2);
        moves = findViewById(R.id.chance);
        play = new Player();
        value = sentValue();
        rand = new Random();
        fill = 0;
        chance = "O";
        step = 1;
        if(value==0) {
            int v = rand.nextInt(2);
            if (v == 0) {
                move[1].setTextColor(Color.parseColor("Red"));
                move[2].setTextColor(Color.parseColor("Blue"));
                player[0] = "X";
                player[1] = "O";
            } else {
                move[2].setTextColor(Color.parseColor("Red"));
                move[1].setTextColor(Color.parseColor("Blue"));
                player[0] = "O";
                player[1] = "X";
            }
            move[1].setText("Com " + player[0]);
            move[2].setText("Player " + player[1]);
            if(player[0].equals("X"))
            {
                computer(blocks, step);
            }
            for (int i = 1; i < 10; i++) {
                final int finalI = i;
                blocks[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blocks[finalI].getText()==""||moves.getText()=="Its is a draw"||moves.getText() != "player won the game" || moves.getText() != "Computer won the game") {
                            if (moves.getText() != "player won the game" && moves.getText() != "Computer won the game" && moves.getText() != "Its is a draw" || moves.getText() == "player move") {
                                Go(finalI, blocks);
                                if (moves.getText() != "player won the game" && moves.getText() != "Computer won the game" && moves.getText() != "Its is a draw" || moves.getText() == "player move") {
                                    if (player[0].equals("X"))
                                        computer(blocks, step);
                                    else
                                        computer(blocks, step);
                                }
                            } else {
                               // Intent player2 = new Intent(Game.this, Player.class);
                                //startActivity(player2);
                                finish();
                            }
                        }
                    }
                });

            }
        }
        else if(value==1)
        {
            moves.setText("player1 move");
            move[1].setTextColor(Color.parseColor("Red"));
            move[2].setTextColor(Color.parseColor("Blue"));
            player[0] = "X";
            player[1] = "O";
            move[1].setText("player1 " + player[0]);
            move[2].setText("Player2 " + player[1]);
            for(int i=1;i<10;i++)
            {
                final int finalI = i;
                blocks[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blocks[finalI].getText()==""||moves.getText()=="Its is a draw") {
                            if (moves.getText() == "player1 move" || moves.getText() == "player2 move") {
                                Go(finalI, blocks);
                            } else {
                               // Intent player2 = new Intent(Game.this, Player.class);
                                //startActivity(player2);
                                finish();
                            }
                        }
                    }
                });
            }
        }
    }

    public void computer(TextView block[], int step)
    {
        if(step==1) {
            if (block[8].getText() == "" && block[5].getText() == "") {
                Random rand = new Random();
                int v[] = new int[]{8, 5};
                Go(v[rand.nextInt(2)], block);
            } else if (block[8].getText() == "") {
                Go(8, block);
            } else if (block[5].getText() == "") {
                Go(5, block);
            }
        }
        else if(step==2)
        {
            if(block[5].getText()=="")
            {
                Go(5,block);
            }
            else if(block[8].getText()=="")
            {
                Go(8,block);
            }
        }
        else if(step==3)
        {
            if(block[2].getText()=="")
            {
                Go(2, block);
            }
            else
            {
                Go(6, block);
            }
        }
        else if(step==4)
        {
            if(check(storex)!=0)
            {
                Go(check(storex), block);
            }
            else if(check(storeo)!=0) {
                Go(check(storeo), block);
            }
            else
            {
                Go(moveRandom(),block);
            }
        }
        else
        {
            if(step%2==0) {
                if (check(storeo)!=0) {
                    Go(check(storeo), block);
                } else if (check(storex)!=0) {
                    Go(check(storex), block);
                } else {
                    Go(moveRandom(),block);
                }
            }
            else
            {
                if (check(storex)!=0) {
                    Go(check(storex), block);
                } else if (check(storeo)!=0) {
                    Go(check(storeo), block);
                } else {
                    Go(moveRandom(),block);
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    public void Go(int check, TextView[] block)
    {

        String chance;
        if(step%2==1)
        {
            chance ="X";
        }
        else
        {
            chance ="O";
        }
        if (chance.equals("X")) {
            if (block[check].getText() == "") {
                block[check].setTextColor(Color.parseColor("Red"));
                block[check].setText("X");
                x = x + check;
                storex.add(check);
            }
        }
        else {
            if(blocks[check].getText() == "") {
                blocks[check].setTextColor(Color.parseColor("blue"));
                blocks[check].setText("O");
                o = o + check;
                storeo.add(check);
            }
        }
        if(winner(storex,storeo)!=0){
            if(value==0) {
                if (winner(storex, storeo) == 1) {
                    if (player[0].equals("X")) {
                        moves.setText("Computer won the game");
                    } else {
                        moves.setText("player won the game");

                    }
                } else if (winner(storex, storeo) == 2) {
                    if (player[0].equals("O")) {
                        moves.setText("Computer won the game");
                    } else {
                        moves.setText("player won the game");
                    }
                }
            }
            else if(value==1)
            {
                if (winner(storex,storeo)==1) {
                    moves.setText("Player1 won the game");
                }
                else if (winner(storex,storeo)==2){
                    moves.setText("Player2 won the game");
                }
            }

        }
        else if ((x + o != 45 || step != 9)&&value==0) {
            moves.setText("player move");
        }
        else if((x+o !=45|| step!=9)&&value==1)
        {
            if(moves.getText()=="player1 move")
            {
                moves.setText("player2 move");
            }
            else
            {
                moves.setText("player1 move");
            }
        }
        else
        {
            moves.setText("Its is a draw");
        }
        step++;
    }
    public int check(ArrayList<Integer> value)
    {
        for(int i=0;i<value.size();i++)
        {
            for(int j=i+1;j<value.size();j++) {
                if (value.get(i) + value.get(j) < 15 && value.get(i) + value.get(j) > 5) {
                    if (blocks[15 - (value.get(i) + value.get(j))].getText() == "") {
                        return (15 - (value.get(i) + value.get(j)));
                    }
                }
            }
        }
        return 0;
    }
    public  int winner(ArrayList<Integer> valuex,ArrayList<Integer> valueo)
    {
        for(int i=0;i<valuex.size();i++)
        {
            for(int j=i+1;j<valuex.size();j++)
            {
                for(int k=j+1;k<valuex.size();k++)
                {
                    if(valuex.get(i)+valuex.get(j)+valuex.get(k)==15)
                    {
                        return 1;
                    }
                }

            }
        }
        for(int i=0;i<valuex.size();i++)
        {
            for(int j=i+1;j<valueo.size();j++)
            {
                for(int k=j+1;k<valueo.size();k++)
                {
                    if(valueo.get(i)+valueo.get(j)+valueo.get(k)==15)
                    {
                        return 2;
                    }
                }

            }
        }
        return 0;
    }
    public int moveRandom()
    {
        Random rand=new Random();

        int i=rand.nextInt(9);
        while (blocks[i+1].getText() != "" && i<10) {
            i=rand.nextInt(9);
        }
        return i+1;
    }
}
