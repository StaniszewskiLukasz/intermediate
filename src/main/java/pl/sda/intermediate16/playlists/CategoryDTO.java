package pl.sda.intermediate16.playlists;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO { //to są nasze kategorie rozszezrone do innego celu niż category
    private Integer id;
    private Integer parentId;//jest potrzebny do drzewka by wiedzieć że powieść jest książka, a lekstura jest powieścią która jest książką
    private String categoryName;
    private CategoryState categoryState;
    private CategoryDTO parentCat; // pole do stworzenia referencji do stworzenia tego obiektu, potrezbne by można było utworzyć statusy
}
