package com.kittu.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 08-10-2017.
 */

public class DbHandler extends SQLiteOpenHelper{


Context context;
    SQLiteDatabase db;

    DbHandler(Context context)
    {

        super(context,"empdb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table emp(id Integer primary key,name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addEmployee(Employee e) {
        ContentValues cv = new ContentValues();
        cv.put("id", e.getId());
        cv.put("name", e.getName());
        long rid = db.insert("emp", null, cv);
        if (rid < 0) {
            Toast.makeText(context, "Insert Issue", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "1 Record Inserted", Toast.LENGTH_SHORT).show();

        }
    }
        public ArrayList<Employee> viewEmployee() {
            Cursor c=db.query("emp",null,null,null,null,null,null);
            c.moveToFirst();

            ArrayList<Employee> e = new ArrayList<>();
            if(c.getCount()>0)
            {

                do{
                    String id=c.getString(0);
                    String name=c.getString(1);
                    Employee a =new Employee(Integer.parseInt(id),name);
                    e.add(a);
                }while (c.moveToNext());

            }
            return e;

        }
    public void updateEmployee(Employee e)
    {
      ContentValues cv=new ContentValues();
        cv.put("id",e.getId());
        cv.put("name",e.getName());
        long nor=db.update("emp",cv,"id="+e.getId(),null);
        Toast.makeText(context,nor+"Records Updated",Toast.LENGTH_SHORT).show();

    }

    public void deleteEmployee(Employee e)
    {
        String where="id="+e.getId();
        long nor=db.delete("emp",where,null);
      Toast.makeText(context,nor+"Records Deleted",Toast.LENGTH_SHORT).show();
    }

    }
