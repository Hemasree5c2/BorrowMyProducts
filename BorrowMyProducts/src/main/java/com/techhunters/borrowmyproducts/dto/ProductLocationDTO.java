package com.techhunters.borrowmyproducts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLocationDTO {

    private int productLocationId;

    private ProductDTO product;

    private double longitude;

    private double latitude;
}
