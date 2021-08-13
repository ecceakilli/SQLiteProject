 package com.eceakilli.sqlliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //------Veritabanı bağlantı işlemleri
        try {
            SQLiteDatabase database=this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR,age INT)");

            database.execSQL("INSERT INTO mucisians(name,age) VALUES('Ece',25)");
            database.execSQL("INSERT INTO mucisians(name,age) VALUES('XXXXX',25)");
            database.execSQL("UPDATE musicians SET age=10 WHERE name='Ece'");
            database.execSQL("DELETE FROM musicians WHERE id=2");

            Cursor cursor=database.rawQuery("SELECT * FROM musicians",null);

            int nameIndex=cursor.getColumnIndex("name");
            int ageIndex=cursor.getColumnIndex("age");
            int idIndex=cursor.getColumnIndex("id");

            while (cursor.moveToNext()){
                System.out.println("Name"+cursor.getString(nameIndex));
                System.out.println("Age"+cursor.getInt(ageIndex));

            }
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}