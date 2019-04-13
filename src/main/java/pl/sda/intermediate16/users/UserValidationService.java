package pl.sda.intermediate16.users;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UserValidationService {
    public Map<String, String> validate(UserRegistrationDTO dto){
        //metoda sprawdzająca poprawność danych z rejestrowania wpisanych przez użytkownika
        Map<String, String> errorMap = new HashMap<>();
        String firstName = dto.getFirstName(); //todo remove this messy code
        String lastName = dto.getLastName();
        String email = dto.getEMail();
        String zipCode = dto.getZipCode();
        String birthDate = dto.getBirthDate();
        String phone = dto.getPhone();
        String password = dto.getPassword();
        String city = dto.getCity();
        String pesel = dto.getPesel();
        String street = dto.getStreet();
        String country = dto.getCountry();

        if (firstName == null || !firstName.matches("[A-Z][a-z]{2,}")){
            errorMap.put("firstNameValRes", "Powinny być przynajmniej 3 znaki");
        }
        if (lastName == null || !lastName.matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]*)?$")){
            errorMap.put("lastNameValRes", "Przynajmniej 3 znaki oraz pierwsza duża, reszta małe \n" +
                    "\t+ dopuszczenie \"-\" i drugiego członu z dużej litery");
        }
        if (email == null || !email.matches("^[\\w\\.]{2,}@([a-z]{2,}\\.){1,2}[a-z]{2,}")){
            errorMap.put("emailValRes", "inny jest, mowie ci");
        }
        if (zipCode == null || !zipCode.matches("^[0-9]{2}-[0-9]{3}")) {
            errorMap.put("zipCodeValRes", "Kod pocztowy powinien mieć format 12-345.");
        }
        if (birthDate == null || !birthDate.matches("^(19|20)[0-9]{2}-((0[1-9])|(1[0-2]))-((0[1-9])|([12][0-9])|(3[01]))$")) {
            errorMap.put("birthDateValRes", "no chyba nie");
        }
        if (!StringUtils.defaultIfBlank(phone,"").trim().matches("(\\+48 )?([0-9]{9}|([0-9]{3}-){2}[0-9]{3})")) {
            //jeśli phone będzie null lub blank to dzieki StringUtil.defaultIfBlank
            // wrzucimy wartość domyslą którą sami wpisujemy
            errorMap.put("phoneValRes", "no chyba nie");
        }
        if (password == null || !password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d]).{10,20}")) {
            errorMap.put("passwordValRes", "no chyba nie");
        }
        if (StringUtils.isBlank(city)){
            errorMap.put("cityValRes","Wpisz miasto");
        }
        if (StringUtils.isBlank(country)){
            errorMap.put("countryValRes","Wpisz kantry");
        }
        if (StringUtils.isBlank(pesel) ||!pesel.matches("[0-9]{11}")){
            errorMap.put("peselValRes","pesel miał być");
        }
        if (StringUtils.isBlank(street)){
            errorMap.put("streetValRes","Wpisz ulice");
        }
        return errorMap;
    }
}
