package com.example.kamesh.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Player extends AppCompatActivity {
    Button computer,player;
    public static int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        computer=findViewById(R.id.button6);
        player=findViewById(R.id.button7);
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comp=new Intent(Player.this,Game.class);
                value=0;
                sentValue();
                startActivity(comp);
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play=new Intent(Player.this,Game.class);
                value=1;
                sentValue();
                startActivity(play);
            }
        });
    }
    public static int sentValue()
    {
        return value;
    }

}
