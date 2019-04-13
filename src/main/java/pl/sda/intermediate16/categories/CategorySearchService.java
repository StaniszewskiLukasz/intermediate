package pl.sda.intermediate16.categories;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchService {
    //ta klasa ma szukać kategorii z listy w InMemoryCategoryDTO tych, które wybierze użytkownik
    //użytskownik na stronie wpisuje nazwę kategorii np. lektury albo na nie klika i ta klasa szuka
    //czy i co mamy mu wyświetlić

    public List<CategoryDTO> filterCategories(String categoryName) {
        List<Category> categoryList = InMemoryCategoryDAO.getInstance().getCategoryList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDTOList.add(buildCategoryDTO(category));
            //przepisujemy category na categoryDto
            //po co tu przepisujemy kategorię z listy do listy???
        }

        for (CategoryDTO categoryDTO : categoryDTOList) {
            if (categoryDTO.getParentId() == null) {
                continue;
                //jeśli categoria nie ma rodzica to kontynuujemy czyli przerywamy działanie pętli ale nie metody
            }
            //fixme mapa zamiast streama
            categoryDTO.setParentCat(
                    categoryDTOList.stream()
                            .filter(c -> c.getId().equals(categoryDTO.getParentId()))
                            //id rodzica porównujemy z id dziecka a kolejność jest dobra i znacząca
                            .findFirst()
                            .orElse(null)
            );
        }

        for (CategoryDTO categoryDTO : categoryDTOList) {
            //ustawiamy czy kategoria ma być zaznaczona/rozwinięta
            if (categoryName!=null && categoryName.trim().equals(categoryDTO.getCategoryName())) {
                //dodane 'categoryName!=null'
                //tu porównujemy cateogrię którą wybrał użytkownik i jeśli ona nie jest nullem i
                //jej nazwa zgadza się z nazwą kategori z listy categoryDTO to:
                categoryDTO.getCategoryState().setOpen(true);
                //ustawiamy booleana kategorii na otwartą
                categoryDTO.getCategoryState().setSelected(true);
                //ustawiamy booleana kategorii na wybraną
                openParent(categoryDTO.getParentCat());
                //i pobieramy id kategorii rodzica
            }
        }

        return categoryDTOList;
    }

    private void openParent(CategoryDTO categoryDTO) {
        //otwieramy wszystkie nadrzędne kategorie
        categoryDTO.getCategoryState().setOpen(true);
        if (categoryDTO.getParentId() == null) {
            return;
        }
        openParent(categoryDTO.getParentCat());
    }

    private CategoryDTO buildCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.getCategoryName().trim());
        categoryDTO.setId(category.getId());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setCategoryState(new CategoryState());
        return categoryDTO;
    }
}
