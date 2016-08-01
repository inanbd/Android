package com.example.kaiza.sqlite_1;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText name;
    EditText cgpa;
    EditText id;
    Button add,viewAll,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


        name=(EditText) findViewById(R.id.editText);
        cgpa =(EditText) findViewById(R.id.editText2);
        id =(EditText)findViewById(R.id.editText3);

        add=(Button) findViewById(R.id.button);
        viewAll=(Button) findViewById(R.id.btnViewAll);
        update=(Button) findViewById(R.id.button2);

        Insert();
        ViewAll();
        UpdateData();

    }

    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated=myDb.UpdateData(id.getText().toString(),name.getText().toString(),cgpa.getText().toString());

            }
        });
    }

    public void ViewAll(){
        viewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor cursor = myDb.getAllData();

                        if(cursor.getCount()==0){
                            //show message (no data)

                            showStringMessage("Error","Nothing Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();

                        while (cursor.moveToNext()){
                            buffer.append("ID: "+cursor.getString(0)+"\n");
                            buffer.append("Name: "+cursor.getString(1)+"\n");
                            buffer.append("CGPA: "+cursor.getString(2)+"\n\n");
                        }
                        showStringMessage("Data",buffer.toString());

                    }
                }
        );
    }
    public void showStringMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void Insert(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = myDb.inserData(name.getText().toString(),cgpa.getText().toString());
                       // Toast.makeText(getApplicationContext(), name.getText().toString()+" "+cgpa.getText().toString(), Toast.LENGTH_LONG).show();
                        if(isInserted){
                            Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
}
