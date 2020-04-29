package com.techhunters.borrowmyproducts.dto;

import java.util.List;
import java.util.Set;

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
public class CategoryDTO {

    private int categoryId;

    private String categoryName;

    private String image;


}