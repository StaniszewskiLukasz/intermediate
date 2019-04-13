package pl.sda.intermediate16.users;

import lombok.Data;

@Data
//to jest zestaw getterów setterów i toString itp.
public class UserRegistrationDTO { //DATA TRANSFER OBJECT - słuzy do przesylania danych
    //tu są te same pola co w User
    private String firstName;
    private String lastName;
    private String eMail;
    private String birthDate; //TODO LocalDate zamiast Stringa
    private String pesel;
    private String phone;
    private String password;
    private String city;
    private String country;
    private String street;
    private String zipCode;
    private boolean preferEmails;
    //tu nie przyjdzie coś klasy UserAddress bo to sa dane z frontendu od użytkownika
}
