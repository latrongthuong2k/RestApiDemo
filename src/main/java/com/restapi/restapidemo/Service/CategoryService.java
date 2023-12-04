package com.restapi.restapidemo.Service;


import com.restapi.restapidemo.Dto.Category.ReqCreateCategory;
import com.restapi.restapidemo.Dto.Category.CategoryDto;
import com.restapi.restapidemo.Dto.Category.ResCategoryAction;
import com.restapi.restapidemo.Dto.Category.ResListCategory;
import com.restapi.restapidemo.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findById(Integer id);
    CategoryDto saveCategory(ReqCreateCategory reqCreateCategory);

    List<ResCategoryAction> searchCate (String key);
    List<ResListCategory> getAllCategory();
    ResCategoryAction deleteById(Integer id);

    ResCategoryAction getCateById(Integer id);

    CategoryDto updateCate(CategoryDto updatedCate);
}
