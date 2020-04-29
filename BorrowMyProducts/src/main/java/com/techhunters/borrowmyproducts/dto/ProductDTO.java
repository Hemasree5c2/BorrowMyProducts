package com.techhunters.borrowmyproducts.dto;

import java.util.List;

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
public class ProductDTO {


    private int productId;

    private UserDTO user;

    private CategoryDTO category;

    private String productName;

    private String productDescription;

    private int productCost;

    private String productStatus;

    private String image;

    private ProductLocationDTO productLocation;

}
