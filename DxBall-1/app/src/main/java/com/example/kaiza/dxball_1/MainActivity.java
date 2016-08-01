package com.example.kaiza.dxball_1;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    public static int score;
    public static boolean gameStarted;

    Database db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameStarted=false;

        db = new Database(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(gameStarted==true){
            gameStarted=false;
            Intent i=new Intent(this,InsertName.class);
            startActivity(i);
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:

                Intent i=new Intent(MainActivity.this,StartGame.class);
                startActivity(i);

                break;
            case R.id.lvlDesign:
                Intent j=new Intent(MainActivity.this,LevelDesign.class);
                startActivity(j);

                break;
            case R.id.score:

                Cursor cursor=db.getTop5Scorer();

                if(cursor.getCount()==0){
                    //show message (no data)

                    showStringMessage("Error","Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext()){
                    buffer.append("Name: "+cursor.getString(1)+"\n");
                    buffer.append("Score: "+cursor.getString(2)+"\n\n");
                }
                showStringMessage("Data",buffer.toString());
                break;

        }

    }

    public void showStringMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}