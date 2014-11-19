package com.alex.cepes.principal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alex.cepes.principal.model.Country;

import java.util.List;

/**
 * Created by cepes on 13/11/2014.
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private List<Country> countries;
    private int rowLayout;
    private Context mContext;

    public CountryAdapter(List<Country> countries, int rowLayout, Context mContext){
        this.countries = countries;
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Country country = countries.get(i);
        viewHolder.countryName.setText(country.name);
        viewHolder.countryName.setId(country.id);
        viewHolder.countryImage.setImageDrawable(mContext.getDrawable(country.getImageResourceId(mContext)));
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;
        public ImageView countryImage;


        public ViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.countryId);
            countryImage = (ImageView)itemView.findViewById(R.id.countryImage);


        }

    }

}
