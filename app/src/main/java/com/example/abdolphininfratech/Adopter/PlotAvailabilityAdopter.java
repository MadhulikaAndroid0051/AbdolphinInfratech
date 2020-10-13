package com.example.abdolphininfratech.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdolphininfratech.ModelClass.PlotAvailabilityModel;
import com.example.abdolphininfratech.ModelClass.PlotBookingModel;
import com.example.abdolphininfratech.R;

import java.util.ArrayList;
import java.util.List;

public class PlotAvailabilityAdopter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<PlotAvailabilityModel> plotAvailabilityModels;
    Context context;

    public PlotAvailabilityAdopter(List<PlotAvailabilityModel> plotAvailabilityModels, Context context) {
        this.plotAvailabilityModels = plotAvailabilityModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plotavailabitydetails_item, parent, false);
        return new PlotAvailabilityAdopter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlotAvailabilityAdopter.ViewHolder myholder =(PlotAvailabilityAdopter.ViewHolder) holder;
        final PlotAvailabilityModel plotAvailabilityModel=plotAvailabilityModels.get(position);

        myholder.tv_Plot_number.setText(plotAvailabilityModel.getTv_plot_Number());
        myholder.tv_block_name.setText(plotAvailabilityModel.getTv_Plot_Block());
        myholder.tv_sector_name.setText(plotAvailabilityModel.getTv_Plot_Sector());
        myholder.tv_plot_areaname.setText(plotAvailabilityModel.getTv_Plot_Area());

    }

    @Override
    public int getItemCount() {
        return plotAvailabilityModels.size();
    }

    public void filterList(ArrayList<PlotAvailabilityModel> filteredList) {

        plotAvailabilityModels=filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Plot_number,tv_block_name,tv_sector_name,tv_plot_areaname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Plot_number=itemView.findViewById(R.id.tv_plot_number);
            tv_block_name=itemView.findViewById(R.id.tv_block_name);
            tv_sector_name=itemView.findViewById(R.id.tv_sector_name);
            tv_plot_areaname=itemView.findViewById(R.id.tv_Plot_Area);
        }
    }
    }
