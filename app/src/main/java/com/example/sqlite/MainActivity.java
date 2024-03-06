package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private StringBuffer myBuffer;
    private Context context;
    private ListView maListViewClients;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maListViewClients = findViewById(R.id.listViewClients);
        imageView = findViewById(R.id.imageView);


        SQLiteBancaDati maSQLdb = new SQLiteBancaDati(this);
        Cursor myCursor2 = maSQLdb.leggiTabella();


        ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map;
//        map = new HashMap<String,String>();
//        map.put("titre", "Mario");
//        map.put("description", "Rejouez les aventures du plus célèbre des plombiers");
//        map.put("img",String.valueOf(R.drawable.marioIcon));
//        listItem.add(map);

        if (myCursor2.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "La tabella é vuota.", Toast.LENGTH_SHORT).show();
        } else {
            myBuffer = new StringBuffer();
            myCursor2.moveToFirst();
            while (!myCursor2.isAfterLast()) {
                map = new HashMap<String,String>();
                map.put("id", String.valueOf(myCursor2.getInt(0)));
                map.put("cognome", myCursor2.getString(1));
                map.put("nome", myCursor2.getString(2));
                map.put("eta", String.valueOf(myCursor2.getInt(3)));
                map.put("img",String.valueOf(R.drawable.avatar));
                listItem.add(map);
//                myBuffer.append("id: ").append(myCursor2.getInt(0)).append("\n\r\r");
//                myBuffer.append("Cognome: ").append(myCursor2.getString(1)).append("\n\r\r");
//                myBuffer.append("Nome: ").append(myCursor2.getString(2)).append("\n\r\r");
//                myBuffer.append("Eta: ").append(myCursor2.getInt(3)).append("\n");
//                myBuffer.append("___________\n");
                myCursor2.moveToNext();
                SimpleAdapter monAdapter = new SimpleAdapter(this.getBaseContext(),listItem,R.layout.affichage_item, new String[] {"img", "id","cognome","nome", "eta"}, new int[] {R.id.imageView, R.id.id, R.id.cognome, R.id.nome, R.id.eta});
                maListViewClients.setAdapter(monAdapter);
            }
            myCursor2.close();

            //Log.d("Buffer", String.valueOf(myBuffer));
        }


        //maSQLdb.insertionCLIENTS();
        maSQLdb.insertionCLIENTS("Pacciani", "Pietro", 65);
        maSQLdb.insertionCLIENTS("Calzelunghe", "Pippi", 19);
        maSQLdb.insertionCLIENTS("Pinco", "Pallino", 36);
        maSQLdb.insertionCLIENTS("Olindo", "Rosa", 57);
        maSQLdb.insertionCLIENTS("Tizio", "Caio", 28);
        maSQLdb.insertionCLIENTS("Misseri", "Michele", 71);
        maSQLdb.insertionCLIENTS("Aggiorna", "Mento", 43);

        maSQLdb.close();
        //infoBancaDati(SQLiteBancaDati.NOM_TABLE, myBuffer);
    }

    public void infoBancaDati(String titolo, StringBuffer messaggio) {
        AlertDialog aD = new AlertDialog.Builder(this).create();
        aD.setTitle(titolo);
        aD.setMessage(messaggio);
        aD.setButton(AlertDialog.BUTTON_POSITIVE, "Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onRestart();
            }
        });
        aD.setButton(AlertDialog.BUTTON_NEUTRAL, "QUIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        aD.show();
    }


}
