package pl.sda.intermediate16.categories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Integer id;
    private Integer parentId;
    //jest potrzebny do drzewka by wiedzieć że powieść jest książka, a lekstura jest powieścią która jest książką
    private String categoryName;
    private CategoryState categoryState;
    private CategoryDTO parentCat;
    // pole do stworzenia referencji do stworzenia tego obiektu, potrezbne by można było utworzyć statusy

    public String getText(){
        //biblioteka frontendowa wymaga takiej metody
        return categoryName;
    }

    public String getParent(){
        //biblioteka frontendowa wymaga takiej metody
        return parentId == null ? "#" : parentId.toString();
    }

    public CategoryState getState(){
        //biblioteka frontendowa wymaga takiej metody
        return categoryState;
    }

}
