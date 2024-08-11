package com.codegym.model;

import lombok.Data;

import java.util.List;

@Data
public class City {
    private String id;
    private String name;
    private List<District> districts;

    public int binarySearch(List<District> districts,int l,int r,String x){
         if(r >= l){
             int mid = l + (r-l)/2;
             if(districts.get(mid).getId().equals(x))
                   return mid;
             if(districts.get(mid).getId().compareTo(x) < 0) {
                 return binarySearch(districts,l,mid-1,x);
             }
             return binarySearch(districts,mid+1,r,x);
         }
         return -1;
    }
    public int checkDistrict(String id){
           int l = 0;
           int r = districts.size()-1;
           int index = binarySearch(this.getDistricts(),l,r,id);
           return index;
    }
    // Constructors, getters, setters...
}
