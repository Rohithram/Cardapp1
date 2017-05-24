package io.rohithram.mobapp1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * Created by rohithram on 22/5/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    Context context;
    public List<Integer> card_no;
    public List<Integer> dupcard_no;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_cardno;
        public Button remove;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_cardno = (TextView)itemView.findViewById(R.id.cv_tv_1);
            remove = (Button)itemView.findViewById(R.id.button);
            card = (CardView)itemView.findViewById(R.id.cv_1);


        }
    }


    public Adapter(Context context, List<Integer> card_no){
        this.context = context;
        this.card_no = card_no;
        this.dupcard_no=card_no;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.main1, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_cardno.setText("Cardno :" + card_no.get(position));

        if(card_no.get(position)%2==0){
        holder.itemView.setBackgroundColor(Color.parseColor("#e0f2f1"));}
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#f3e5f5"));


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_no.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, card_no.size());}
        });
    }

    @Override
    public int getItemCount() {
        return card_no.size();
    }

}
