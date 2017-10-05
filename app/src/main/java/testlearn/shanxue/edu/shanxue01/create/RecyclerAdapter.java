package testlearn.shanxue.edu.shanxue01.create;


/**
 * Created by Shade on 5/9/2016.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import testlearn.shanxue.edu.shanxue01.R;
import testlearn.shanxue.edu.shanxue01.models.MomoModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private List<MomoModel> momoModels = new ArrayList<>();

    public RecyclerAdapter() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public View root;
        public TextView itemID;
        public TextView itemMomo;
        public TextView itemHint;


        public ViewHolder(final View itemView) {
            super(itemView);
            itemID = (TextView) itemView.findViewById(R.id.item_id);
            itemMomo = (TextView)itemView.findViewById(R.id.item_momo);
            itemHint = (TextView)itemView.findViewById(R.id.item_hint);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    int position = getAdapterPosition();

                    Snackbar.make(v, "deleted on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }
            });
        }




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_momo, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        viewHolder.itemTitle.setText(momoModels.get(i).getMomo_ID());
        viewHolder.itemID.setText( String.valueOf(momoModels.get(i).getMomo_ID()));
        viewHolder.itemMomo.setText(momoModels.get(i).getMomo_text());
        viewHolder.itemHint.setText(momoModels.get(i).getMomo_hintMain());

    }

    @Override
    public int getItemCount()
    {
        return momoModels.size();
    }

    public void changeColor(){



    }
}