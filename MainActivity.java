package io.rohithram.mobapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    RecyclerView rv_list;
    public  String [] Choices = {"Display All","Display Odd Cardno","Display even Cardno"};
    List<Integer> card_no ;
    List<Integer>dupcard_no;
     Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Choices);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setLayoutManager(new LinearLayoutManager(this));


       card_no  =new ArrayList<>();
       dupcard_no=new ArrayList<>();


        for(int i=0;i<100;i++) {
            card_no.add(i+1);
        }

        customAdapter = new Adapter(this,card_no);
        rv_list.setAdapter(customAdapter);

        for(int i=0;i<card_no.size();i++){
            dupcard_no.add(card_no.get(i));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if(position==0){
            card_no.clear();
            card_no.addAll(dupcard_no);
            customAdapter.notifyDataSetChanged();

        }
        else if(position==1){
            for(int j=0;j<card_no.size();j++){
                if (card_no.get(j) % 2 == 0) {
                    card_no.remove(j);
                    customAdapter.notifyItemRemoved(j);
                    customAdapter.notifyItemRangeChanged(j,card_no.size());
                    customAdapter.notifyDataSetChanged();
                }

            }


        }
        else if(position==2){
            for( int j=0;j<card_no.size();j++){
                if(((card_no.get(j))%2)!=0){
                    card_no.remove(j);
                    customAdapter.notifyItemRemoved(j);
                    customAdapter.notifyItemRangeChanged(j,card_no.size());
                    customAdapter.notifyDataSetChanged();
                }

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
