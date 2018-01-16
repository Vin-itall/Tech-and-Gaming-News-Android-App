package com.example.atmc.techknowlogy_l;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import static com.example.atmc.techknowlogy_l.R.styleable.View;

public class BuildAPC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_apc);
     Button Calc;
     Calc=(Button)findViewById(R.id.button);
     final Spinner Spinnerx,Spinner2,Spinner4,Spinner3;


        Spinnerx = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("i5 7600");
        list.add("i5 7600k");
        list.add("i7 7700");
        list.add("i7 7700k");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinnerx.setAdapter(adapter);

        Spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2;
        List<String> list2;

        list2 = new ArrayList<String>();
        list2.add("MSI 1060");
        list2.add("Asus 1060");
        list2.add("MSI 1070");
        list2.add("Asus 1070");
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapter2);

        Spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3;
        List<String> list3;

        list3 = new ArrayList<String>();
        list3.add("Z170");
        list3.add("Z270");
        list3.add("HB");
        list3.add("H");
        adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner3.setAdapter(adapter3);

        Spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4;
        List<String> list4;

        list4 = new ArrayList<String>();
        list4.add("CorSair 8GB");
        list4.add("CorSair 16GB");
        list4.add("GSkill 8GB");
        list4.add("GSkill 16GB");
        adapter4 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner4.setAdapter(adapter4);
          Calc.setOnClickListener(new View.OnClickListener() {

                @Override
             public void onClick(View v) {
  String Processor=Spinnerx.getSelectedItem().toString();
  String GPU=Spinner2.getSelectedItem().toString();
  String Mobo=Spinner3.getSelectedItem().toString();
  String RAM=Spinner4.getSelectedItem().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(Processor);
                    DatabaseReference myRef2 = database.getReference(GPU);
                    DatabaseReference myRef3 = database.getReference(Mobo);
                    DatabaseReference myRef4 = database.getReference(RAM);




                myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                           final Long value1 = dataSnapshot.getValue(Long.class);
                            Log.d("Ok", "Value1 is: " + value1);
                            Globals.value1=value1;
                            printSum();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Ok", "Failed to read value.", error.toException());
                        }
                    });
                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                          final  Long value2 = dataSnapshot.getValue(Long.class);
                            Log.d("Ok", "Value2 is: " + value2);
                            Globals.value2=value2;
                            printSum();

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Ok", "Failed to read value.", error.toException());
                        }
                    } );
                    myRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                           final Long value3 = dataSnapshot.getValue(Long.class);
                            Log.d("Ok", "Value3 is: " + value3);
                            Globals.value3=value3;
                            printSum();

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Ok", "Failed to read value.", error.toException());
                        }});

                    myRef4.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                          final  Long value4 = dataSnapshot.getValue(Long.class);
                            Log.d("Ok", "Value4 is: " + value4);
                            Globals.value4=value4;
                            printSum();

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Ok", "Failed to read value.", error.toException());
                        }
                    } );


 }
});








    }


    private void printSum()
    {
        long sum = Globals.getSum();
        if(sum != -203464)
        {
            TextView costTextView = (TextView) findViewById(R.id.costTextView);
            costTextView.setText(sum+"");
        }
        else
        {
            TextView costTextView = (TextView) findViewById(R.id.costTextView);
            costTextView.setText("Calculating...");
        }
    }



}


class Globals
{
    static long value1=-203464;
    static long value2=-203464;
    static long value3=-203464;
    static long value4=-203464;


    static long getSum() {
        if (value1 == -203464 || value2 == -203464 || value3 == -203464 || value4 == -203464)
        {
            return -203464;
        }
        else
        {
            return value1+value2+value3+value4;
        }



    }


}