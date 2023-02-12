package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.library_vp.repository.CategoryRepository;
import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    private Category category;
    private List<Category> categories;

    @Before
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        categories = List.of(category);
    }

    @Test
    public void findCategoryByIdTest_Success() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryServiceImpl.findCategoryById(1L);

        assertNotNull(foundCategory);
        assertEquals(category.getId(), foundCategory.getId());
        assertEquals(category.getName(), foundCategory.getName());
    }

    @Test(expected = CategoryNotFoundException.class)
    public void findCategoryByIdTest_CategoryNotFoundException() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        categoryServiceImpl.findCategoryById(2L);
    }

    @Test
    public void findAllTest_Success() {
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> foundCategories = categoryServiceImpl.findAll();

        assertNotNull(foundCategories);
        assertEquals(foundCategories.size(), categories.size());
        assertEquals(categories, foundCategories);
    }

    @Test
    public void listCategoriesTest_Success() {
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> foundCategories = categoryServiceImpl.listCategories();

        assertNotNull(foundCategories);
        assertEquals(foundCategories.size(), categories.size());
        assertEquals(categories, foundCategories);
    }
}
