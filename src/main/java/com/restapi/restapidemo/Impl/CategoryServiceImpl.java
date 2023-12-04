package com.restapi.restapidemo.Impl;

import com.restapi.restapidemo.Dto.Category.ReqCreateCategory;
import com.restapi.restapidemo.Dto.Category.CategoryDto;
import com.restapi.restapidemo.Dto.Category.ResCategoryAction;
import com.restapi.restapidemo.Dto.Category.ResListCategory;
import com.restapi.restapidemo.Entity.Category;
import com.restapi.restapidemo.Exception.CannotDeleteException;
import com.restapi.restapidemo.Exception.DuplicateResourceException;
import com.restapi.restapidemo.Exception.ResourceNotFoundException;
import com.restapi.restapidemo.Repository.CategoryRepository;
import com.restapi.restapidemo.Service.CategoryService;
import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category id " + id + "is not found")
        );
    }

    @Override
    public CategoryDto saveCategory(ReqCreateCategory reqCate) {
        Optional<Category> checkCate = categoryRepository.findByName(reqCate.name());
        if (checkCate.isPresent()) {
            throw new DuplicateResourceException("Category is exist, please input another category name");
        }
        Category category = Category.builder()
                .description(reqCate.descriptions())
                .name(reqCate.name())
                .priority(reqCate.priority()).status(true).build();
        categoryRepository.save(category);
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getPriority(),
                category.getStatus());
    }

    @Override
    public List<ResCategoryAction> searchCate(String key) {
        List<Category> categories = categoryRepository.findByNameContaining(key);
        return categories
                .stream().map(category -> new ResCategoryAction(
                        category.getId(),
                        category.getName(),
                        category.getPriority(),
                        category.getStatus()
                )).toList();
    }

    @Override
    public List<ResListCategory> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category ->
                new ResListCategory(category.getId(),
                        category.getName(),
                        category.getPriority(),
                        category.getStatus())).toList();
    }

    @Override
    public ResCategoryAction deleteById(Integer id) {
        Category category = foundCategory(id);

        if (!category.getProducts().isEmpty()) {
            throw new CannotDeleteException("Cannot delete category as it contains products");
        }
        ResCategoryAction res = new ResCategoryAction(category.getId(),
                category.getName(),
                category.getPriority(),
                category.getStatus());
        categoryRepository.deleteById(id);
        return res;
    }

    @Override
    public ResCategoryAction getCateById(Integer id) {
        Category category = foundCategory(id);
        return new ResCategoryAction(category.getId(),
                category.getName(),
                category.getPriority(),
                category.getStatus());
    }

    @Override
    public CategoryDto updateCate(CategoryDto updatedCate) {
        Category category = Category.builder()
                .name(updatedCate.name())
                .status(updatedCate.status())
                .description(updatedCate.descriptions())
                .priority(updatedCate.priority())
                .build();
        categoryRepository.save(category);
        return new CategoryDto(category.getId(),
                category.getName(),
                category.getDescription(),
                category.getPriority(),
                category.getStatus());
    }

    private Category foundCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category id" + "is not found"));
    }
}