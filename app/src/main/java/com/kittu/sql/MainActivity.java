package com.kittu.sql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText etEmpName,etEmpId;
    Button btnAdd,btnView,btnUpdate,btnDelete;
    TextView tvResult;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmpName=(EditText)findViewById(R.id.etEmpName);
        etEmpId=(EditText)findViewById(R.id.etEmpId);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=(Button)findViewById(R.id.btnView);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
tvResult=(TextView)findViewById(R.id.tvResult);
        db=new DbHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpId.getText().toString();
                String name=etEmpName.getText().toString();

                if(id.length()==0)
                {
                    etEmpId.setError("Id is Empty");
                    etEmpId.requestFocus();
                    return;
                }
                else
                    if(name.length()==0)
                    {
                        etEmpName.setError("Name is Empty");
                        etEmpId.requestFocus();
                        return;
                    }
                Employee a=new Employee(Integer.parseInt(id),name);
                db.addEmployee(a);
                etEmpId.setText("");
                etEmpName.setText("");
                etEmpName.requestFocus();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               tvResult.setText("");
                ArrayList<Employee> a=new ArrayList<Employee>();

                a=db.viewEmployee();
                if(a.size()==0)
                {
                    tvResult.setText("No records");

                }
                else
                {

                    tvResult.setText("id\t Name");
                    for(Employee m:a)
                    {
                        tvResult.setText(tvResult.getText()+"\n"+m.getId()+"\t"+m.getName());
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpId.getText().toString();
                String name=etEmpName.getText().toString();

                if(id.length()==0)
                {
                    etEmpId.setError("Id is Empty");
                    etEmpId.requestFocus();
                    return;
                }
                else
                if(name.length()==0)
                {
                    etEmpName.setError("Name is Empty");
                    etEmpId.requestFocus();
                    return;
                }
                Employee a=new Employee(Integer.parseInt(id),name);
                db.updateEmployee(a);
                etEmpId.setText("");
                etEmpName.setText("");
                etEmpName.requestFocus();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=etEmpId.getText().toString();
                String name=etEmpName.getText().toString();

                if(id.length()==0)
                {
                    etEmpId.setError("Id is Empty");
                    etEmpId.requestFocus();
                    return;
                }
                Employee a=new Employee(Integer.parseInt(id),name);
                db.deleteEmployee(a);
                etEmpId.setText("");
                etEmpName.setText("");
                etEmpName.requestFocus();
            }
        });
    }
}
