package com.codegym.model;

import lombok.Data;

import java.util.List;

@Data
public class District {
    private String id;
    private String name;
    private List<Ward> wards;

    // Constructors, getters, setters...
}