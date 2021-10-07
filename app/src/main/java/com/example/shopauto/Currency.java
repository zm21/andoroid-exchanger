package com.example.shopauto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Currency {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;


}
