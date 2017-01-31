package com.acmhack.ucla.textscramblerskeleton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    Button translate;
    TextView method;
    TextView originalText;
    TextView changedText;
    ImageButton swap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up user interface and behavior
        input = (EditText)findViewById(R.id.inputText);
        output = (TextView)findViewById(R.id.output);
        translate = (Button)findViewById(R.id.ButtonTranslate);
        method = (TextView)findViewById(R.id.method);
        originalText = (TextView)findViewById(R.id.OriginalText);
        changedText = (TextView)findViewById(R.id.changedText);
        swap = (ImageButton)findViewById(R.id.ButtonSwap);


        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = input.getText().toString();
                if(method.getText().toString().equals("Encrypt") ){
                    output.setText(encrypt1(msg));
                    Log.d("info","using Encrypt!");
                }
                else
                {
                    output.setText(decrypt1(msg));
                    Log.d("info","using Decrypt!");
                    Log.d("text",method.getText().toString());
                }

            }
        });
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap_direction();

            }
        });

    }

    // Use this function to change from encyrpt to decrypt
    // Look at the image in the drawable folder named: "swap_example_not_for_project_usage"
    // for more clarity on how the ui should change if unsure what to do here.
    public void swap_direction(){
        if(method.getText().toString().equals("Encrypt"))
            method.setText("Decrypt");
        else
            method.setText("Encrypt");

        String temp = originalText.getText().toString();
        originalText.setText(changedText.getText().toString());
        changedText.setText(temp);
    }

    //Use these functions to implement a substitution cipher (https://en.wikipedia.org/wiki/Substitution_cipher)
    //Optional Substitutional Cipher, but you can use your own.
    //  plain text: ABCDEFGHIJKLMNOPQRSTUVWXYZ
    // cipher text: ZEBRASCDFGHIJKLMNOPQTUVWXY
    // example: "flee at once. we are discovered!" transforms into "SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!"
    public String encrypt1(String msg){
        String realMsg = msg.toUpperCase();
        String output ="";
        String [] text = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ","ZEBRASCDFGHIJKLMNOPQTUVWXY"};

        for(int i=0;i<realMsg.length();i++) {
            if(!Character.isLetter(realMsg.charAt(i)))
                output+=realMsg.charAt(i);
            else
                for (int k = 0; k < 26; k++)
                    if (realMsg.charAt(i) == text[0].charAt(k)) {
                        output += text[1].charAt(k);
                        break;
                    }
        }

        return output;


    }
    public String decrypt1(String msg){
        String realMsg = msg.toUpperCase();
        String output ="";
        String [] text = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ","ZEBRASCDFGHIJKLMNOPQTUVWXY"};

        for(int i=0;i<realMsg.length();i++) {
            if(!Character.isLetter(realMsg.charAt(i)))
                output += realMsg.charAt(i);
            else
                for (int k = 0; k < 26; k++)
                     if (realMsg.charAt(i) == text[1].charAt(k)) {
                        output += text[0].charAt(k);
                         break;
                     }
        }

        return output;
    }

    //Use these functions to implement a polyalphabetic cipher (https://en.wikipedia.org/wiki/Polyalphabetic_cipher)
    //This is similar to substitution but using multiple substitution alphabets
    //              plain text: ABCDEFGHIJKLMNOPQRSTUVWXYZ
    // even letter cipher text: ZEBRASCDFGHIJKLMNOPQTUVWXY
    //  odd letter cipher text: CDFGHIJKLMNOPQTUVWXYZEBRAS
    // example: "test" transforms into "yaxo"
    public String encrypt2(String msg){
        String a = msg.toUpperCase();
        char letter;
        String alphabet  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String [] cipher = {"CDFGHIJKLMNOPQTUVWXYZEBRAS","ZEBRASCDFGHIJKLMNOPQTUVWXY"};
        String output ="";
        for(int i = 0;i<a.length();i++)
        {
            if(!Character.isLetter(a.charAt(i)))
                output += a.charAt(i);
            else{
                for(int k =0; k<26;k++)
                    if(a.charAt(i) == alphabet.charAt(k)) {
                        output+=cipher[i%2].charAt(k);
                        break;
                    }
            }


        }
        return output;
    }
    public String decrypt2(String msg){
        String a = msg.toUpperCase();
        char letter;
        String alphabet  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String [] cipher = { "CDFGHIJKLMNOPQTUVWXYZEBRAS","ZEBRASCDFGHIJKLMNOPQTUVWXY"};
        String output ="";
        for(int i = 0;i<a.length();i++)
        {
            if(!Character.isLetter(a.charAt(i)))
                output += a.charAt(i);
            else{
                for(int k =0; k<26;k++)
                    if(a.charAt(i) == cipher[i%2].charAt(k)) {
                        output+= alphabet.charAt(k);
                        break;
                    }
            }
        }
        return output;
    }

    //Implement your own encryption
    //This doesn't have to be complicated, but for those who are interested in security
    //and want try something more advanced you could try creating AES encryption (https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)
    // which is the standard worldwide. You can also take a look at some of java and android's built-in encryption libraries for more ideas
    //Here is a link to the android developer docs: https://developer.android.com/reference/javax/crypto/package-summary.html
    public void encrypt3(){

    }
    public void decrypt3(){

    }
}
