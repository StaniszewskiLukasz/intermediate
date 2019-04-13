package pl.sda.intermediate16.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    //Serializacja to wbudowany mechanizm zapisywania obiektów
    //Dzięki temu mechanizmowi można na przykład przesyłać obiekty przez sieć. Obiekt,
    // który stworzyliśmy na jednym komputerze (wewnątrz pamięci jednej wirtualnej maszyny Java)
    // może być zserializowany, przesłany przez sieć i zdeserializowany na drugim komputerze
    // tworząc nową instancję obiektu (wewnątrz pamięci drugiej wirtualnej maszyny Javy).
    private static final long serialVersionUID = -381001129481477463L;

    private String name;
    private String surname;
    private String login;
    private String birthdate; //TODO LocalDate zamiast Stringa
    private String pesel;
    private String phone;
    private String passwordHash;
    private UserAddress userAddress;
}
