package guru.springfamework.servicies.impl;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.servicies.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void testGetAllCategories() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3, categoryDTOS.size());


    }

    @Test
    public void testGetCategoryByName() {

        Category category = Category
                .builder()
                .name("Category")
                .id(1L)
                .build();

        when(categoryRepository.findByName(any())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName("Category");

        assertEquals(category.getName(), categoryDTO.getName());
        assertEquals(category.getId(), categoryDTO.getId());
    }
}