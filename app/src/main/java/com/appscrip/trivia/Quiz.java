package com.appscrip.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    Controllerdb db =new Controllerdb(this);
    EditText ed1;
    TextView tv1,tv2,tv3,enter;
    RadioButton a,b,c,d;
    Button bt;
    RadioGroup rg;
    int q,s;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle("Trivia App");

        ed1=(EditText)findViewById(R.id.name);
        tv1=(TextView)findViewById(R.id.ques);
        enter=(TextView)findViewById(R.id.Enter);

        tv2=(TextView)findViewById(R.id.response);
        tv3=(TextView)findViewById(R.id.score);
        rg=(RadioGroup)findViewById(R.id.optionGroup);
        a=(RadioButton)findViewById(R.id.option1);
        b=(RadioButton)findViewById(R.id.option2);
        c=(RadioButton)findViewById(R.id.option3);
        d=(RadioButton)findViewById(R.id.option4);


        bt=(Button)findViewById(R.id.next);
        q=0;
        s=0;
    }



    public void quiz(View v) {
        switch (q) {
            case 0: {
                enter.setVisibility(View.VISIBLE);
                enter.setText("1.What is your name?");
                ed1.getText().toString();
                ed1.setEnabled(false);

                rg.setVisibility(View.VISIBLE);
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                tv2.setText("");
                tv3.setText("");
                a.setEnabled(true);
                b.setEnabled(true);
                c.setEnabled(true);
                d.setEnabled(true);
                ed1.setEnabled(true);
                bt.setText("Next");
                s = 0;
                enter.setVisibility(View.GONE);
                tv1.setText("1.Who is the best cricketer in the world??");
                a.setText("Sachin Tendulkar");
                b.setText("Virat Kohli");
                c.setText("Adam Gilchirst");
                d.setText("Jacques Kallis");
                q = 1;
                break;
            }
            case 1: {
                enter.setVisibility(View.GONE);
                ed1.setEnabled(false);
                tv1.setText("2.What are the colors in the Indian national flag??");
                a.setText("White");
                b.setText("Yellow");
                c.setText("Orange");
                d.setText("Green");
//                e.setText("All of the above");

                if (b.isChecked()) {
                    tv2.setText("Right Answer");
                    s = s + 10;
                } else {
                    tv2.setText("Wrong Answer   B was Right Answer");
                }
                q = 2;
                a.setChecked(false);
                b.setChecked(false);
                c.setChecked(false);
                d.setChecked(false);
                break;
            }

            case 2: {
                enter.setVisibility(View.GONE);
                a.setEnabled(false);
                b.setEnabled(false);
                c.setEnabled(false);
                d.setEnabled(false);
                if (d.isChecked()) {
                    s = s + 10;
                    tv2.setText("Right Answer");
                } else {
                    tv2.setText("Wrong Answer  D was Right Answer");
                }
                tv3.setText(ed1.getText() + "'s final score is " + s);
                bt.setText("Restart");
                q = 0;
                s=0;
                break;
            }
        }
    }//end of function


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.next)
        {
            database=db.getWritableDatabase();
            database.execSQL("INSERT INTO UserDetails(Username)VALUES('"+ed1.getText()+"')" );
            Toast.makeText(this,"Data Inserted To Sqlite Database",Toast.LENGTH_LONG).show();
        }
        else  if(view.getId()==R.id.name)
        {
            Intent intent=new Intent(this,ShowdataListview.class);
            startActivity(intent);

        }
    }
}
