package com.example.hakan.aesdeneme2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    String encoded;
    byte[] sifrelenmis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button enButton = (Button) findViewById(R.id.enbutton);
        final Button deButton = (Button) findViewById(R.id.debutton);
        final EditText input = (EditText) findViewById(R.id.Input);
        final EditText Raw = (EditText) findViewById(R.id.raw);
        final EditText output = (EditText) findViewById(R.id.originText);
        final EditText output2 = (EditText) findViewById(R.id.originText2);
        final Button anahtar = (Button) findViewById(R.id.key);

       // String message="This is just an example";
        Main encrypter = null;
        try {
            encrypter = new Main(new byte[16]);

        } catch (Exception e) {
            e.printStackTrace();
        }

      // byte[] encrypted = new byte[0];
        try {


            final Main finalEncrypter = encrypter;
            enButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        assert finalEncrypter != null;
                      //  Raw.setText(new String(finalEncrypter.encrypt((input.getText().toString().getBytes()))));
                         sifrelenmis=finalEncrypter.encrypt((input.getText().toString().getBytes()));
                        encoded= Base64.encodeToString(sifrelenmis, Base64.DEFAULT);
                        Raw.setText(new String(encoded));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println(encrypted.toString());
      byte[] decrypted = new byte[0];
        try {
         //  decrypted = encrypter.decrypt(sifrelenmis);
        } catch (Exception e) {
            e.printStackTrace();
        }
      //  final byte[] finalDecrypted = decrypted;

       // final byte[] finalDecrypted = decrypted;

        final Main finalEncrypter1 = encrypter;
        deButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert finalEncrypter1 != null;
                try {

                    byte[] decoded=Base64.decode(encoded,Base64.DEFAULT);
                   // output.setText(new String(finalEncrypter1.decrypt(sifrelenmis)));
                    // output.setText(new String(decoded));
                    output.setText(new String(finalEncrypter1.decrypt(decoded)));





                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
       // System.out.println(new String (decrypted, "UTF-8"));


        final Main finalEncrypter2 = encrypter;
        anahtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert finalEncrypter2 != null;
                output2.setText(finalEncrypter2.keystring);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}
