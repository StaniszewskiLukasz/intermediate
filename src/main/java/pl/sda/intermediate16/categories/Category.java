package pl.sda.intermediate16.categories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {// to są nasze kategorie które różnią się użyciem w stosunku do DTO

    private Integer id;
    private Integer parentId;
    private String categoryName;

}
