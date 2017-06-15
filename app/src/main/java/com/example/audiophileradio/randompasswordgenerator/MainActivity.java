package com.example.audiophileradio.randompasswordgenerator;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    private int input;
    private String[] output;
    private EditText editText_input,editText_uppercase,editText_lowercase,editText_numbers,editText_symbols;
    private TextView textView_output;
    private Button button;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4;

    char[] charNum = "0123456789".toCharArray();
    char[] charCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char[] charLower = "acdefghijklmnopqrstuvwxyz".toCharArray();
    char[] charSym = "~!@#$%^&*()_-".toCharArray();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_symbols = (EditText) findViewById(R.id.editText_symbols);
        editText_numbers = (EditText) findViewById(R.id.editText_numbers);
        editText_lowercase = (EditText) findViewById(R.id.editText_lowercase);
        editText_uppercase = (EditText) findViewById(R.id.editText_uppercase);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox_uppercase);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox_lowercase);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox_numbers);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox_symbols);

        editText_input = (EditText) findViewById(R.id.editText_input);
        textView_output = (TextView) findViewById(R.id.textView_output);
        button = (Button) findViewById(R.id.button);

        editText_symbols.setText("~!@#$%^&*()_-");
        editText_numbers.setText("0123456789");
        editText_lowercase.setText("acdefghijklmnopqrstuvwxyz");
        editText_uppercase.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        editText_uppercase.setEnabled(false);
        editText_lowercase.setEnabled(false);
        editText_numbers.setEnabled(false);
        editText_symbols.setEnabled(false);

        editText_input.requestFocus();

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    editText_uppercase.setEnabled(true);
                else
                    editText_uppercase.setEnabled(false);
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    editText_lowercase.setEnabled(true);
                else
                    editText_lowercase.setEnabled(false);
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    editText_numbers.setEnabled(true);
                else
                    editText_numbers.setEnabled(false);
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                    editText_symbols.setEnabled(true);
                else
                    editText_symbols.setEnabled(false);
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(editText_input.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"You must enter length!",Toast.LENGTH_SHORT).show();
                    return;
                }

                input = Integer.parseInt(editText_input.getText().toString());

                if(checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked())
                {
                    StringBuilder characters = new StringBuilder();

                    if(checkBox1.isChecked() && editText_uppercase.getVisibility() == View.VISIBLE)
                        characters.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                    if(checkBox2.isChecked() && editText_lowercase.getVisibility() == View.VISIBLE)
                        characters.append("acdefghijklmnopqrstuvwxyz");
                    if(checkBox3.isChecked() && editText_numbers.getVisibility() == View.VISIBLE)
                        characters.append("0123456789");
                    if(checkBox4.isChecked() && editText_symbols.getVisibility() == View.VISIBLE)
                        characters.append("~!@#$%^&*()_-");

                    String str = characters.toString();
                    char[] chars = str.toCharArray();

                    StringBuilder password = new StringBuilder();
                    Random random = new Random();

                    for(int i = 0; i <= input; i++)
                    {
                        char ci = chars[random.nextInt(chars.length)];
                        password.append(ci);
                        textView_output.setText(password);
                    }
                    Toast.makeText(MainActivity.this,"Password Generated!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"You must check at least one checkbox!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView_output.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setText(textView_output.getText().toString());

                Toast.makeText(MainActivity.this,"Copied to clipboard!",Toast.LENGTH_SHORT).show();

                return false;
            }
        });
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
