package com.example.salonapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salonapp.DataServer.importJson;
import com.example.salonapp.Model.CategModel;
import com.example.salonapp.Model.GroupsTable;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {

    private Context context;
    private static List<GroupsTable> itemsList;
    importJson importData;

    public CategoryListAdapter(Context context, List<GroupsTable> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
        importData=new importJson(context);
    }

    public CategoryListAdapter() {

    }

    public void setItemsList(List<GroupsTable> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }



    private class ViewHolder {
        TextView itemName , price;
        ImageView itemPic;
        LinearLayout catLinear;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder = new ViewHolder();
        view = View.inflate(context, R.layout.category_row, null);


        if ( i%3 == 0) {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .8f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(400);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);

        } else if ( i%2 == 0) {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .5f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(700);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);

        } else {
            ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.INFINITE, .2f, ScaleAnimation.RELATIVE_TO_SELF, .8f);
//            scale.setStartOffset(900);
            scale.setDuration(200);
            scale.setInterpolator(new OvershootInterpolator());
            view.startAnimation(scale);
        }






        holder.itemName = (TextView) view.findViewById(R.id.itemName);
//        holder.itemPic = (ImageView) view.findViewById(R.id.item_pic);


        holder.itemName.setText(itemsList.get(i).getNAME());
//        holder.itemPic.setImageDrawable(context.getResources().getDrawable(itemsList.get(i).getPic()));


        holder.catLinear=view.findViewById(R.id.catLinear);
        holder.catLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AppendActivity appendActivity =(AppendActivity) context;
//                appendActivity.refreshCat();

                importData.getServiceByGroup(itemsList.get(i).getCODE());

            }
        });
        return view;
    }


}
