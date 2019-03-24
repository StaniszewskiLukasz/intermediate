package pl.sda.intermediate16.playlists;

import pl.sda.intermediate16.Category;
import pl.sda.intermediate16.InMemoryCategoryDAO;

import java.util.ArrayList;
import java.util.List;

public class CategorySearchService {//ta klasa ma szukać kategorii z listy w InMemoryCategoryDTO

    public List<CategoryDTO> filterCategories(String searchText) {
        List<Category> categoryList = InMemoryCategoryDAO.getInstance().getCategoryList();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryDTOList.add(buildCategoryDTO(category));
        }
        for (CategoryDTO categoryDTO : categoryDTOList) {
            if (categoryDTO.getParentId() == null) {
                continue;
            }//todo mapa zamiast tego iterowania po liście w nieskończoność
            categoryDTO.setParentCat(
                    categoryDTOList.stream()
                            .filter(e -> e.getId().equals(categoryDTO.getParentId()))//id rodzica porównujemy z id dziecka a kolejność jest dobra i znacząca
                            .findFirst()
                            .orElse(null));
        }
        for (CategoryDTO categoryDTO : categoryDTOList) {
            Object categoryName=null;//to do wyjebania coś pominąłem wcześniej
            if(categoryName.equals(categoryDTO.getCategoryName())){// tu powinien być trim za pierwszym elementem a tco zmienić
                categoryDTO.getCategoryState().setOpen(true);
                categoryDTO.getCategoryState().setSelected(true);
                openParent(categoryDTO);
            }
        }
        return categoryDTOList;
    }

    private void openParent(CategoryDTO categoryDTO) {
        categoryDTO.getCategoryState().setOpen(true);
        if(categoryDTO.getParentId()==null){
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
