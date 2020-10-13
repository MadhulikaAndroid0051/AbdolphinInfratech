package com.example.abdolphininfratech.ModelClass;

public class PlotAvailabilityModel {

    private String tv_plot_Number;
    private String tv_Plot_Area;
    private String tv_Plot_Sector;
    private String tv_Plot_Block;


    public PlotAvailabilityModel(String tv_plot_Number, String tv_Plot_Area, String tv_Plot_Sector, String tv_Plot_Block) {
        this.tv_plot_Number = tv_plot_Number;
        this.tv_Plot_Area = tv_Plot_Area;
        this.tv_Plot_Sector = tv_Plot_Sector;
        this.tv_Plot_Block = tv_Plot_Block;
    }

    public String getTv_plot_Number() {
        return tv_plot_Number;
    }

    public String getTv_Plot_Area() {
        return tv_Plot_Area;
    }

    public String getTv_Plot_Sector() {
        return tv_Plot_Sector;
    }

    public String getTv_Plot_Block() {
        return tv_Plot_Block;
    }

    public void setTv_plot_Number(String tv_plot_Number) {
        this.tv_plot_Number = tv_plot_Number;
    }

    public void setTv_Plot_Area(String tv_Plot_Area) {
        this.tv_Plot_Area = tv_Plot_Area;
    }

    public void setTv_Plot_Sector(String tv_Plot_Sector) {
        this.tv_Plot_Sector = tv_Plot_Sector;
    }

    public void setTv_Plot_Block(String tv_Plot_Block) {
        this.tv_Plot_Block = tv_Plot_Block;
    }
}
