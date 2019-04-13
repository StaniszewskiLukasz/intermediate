package pl.sda.intermediate16.users;

import org.apache.commons.codec.digest.DigestUtils;

public class UserLoginService {
    UserDAO userDAO;

    public UserLoginService(UserDAO userDAO) {
        //to jest sposób na to by UserDAO miał tylko jedna instancję a nie wiele na czym nam zależy
        //na początku za każdym razem gdy chcieliśmy wykorzystać metody jakiejś klasy tworzyliśmy instancję takiej klasy
        //w metodzie
        //ale to powoduje że albo musi być to pole statyczne by każd zmiana i działanie na takim obiekcie
        //powodowało zmianę wartośći tego pola/obiektu w innym wypadku mamy kilka obiektów tego typu a każdy może
        //mieć inne wartości
        //lepszym sposobem jest robienie instancji jakiejś klasy nie w metodzie ale w klasie, to zmniejsza ilość instancji
        //ale i taki jest ich więcej niż jedna
        //to rozwiązanie likwiduje problem
        this.userDAO = userDAO;
    }

    public boolean login(UserLoginDTO userDTO){
        return userDAO.getUserList().stream()
                .filter(u -> u.getLogin().equals(userDTO.getEmail()))
                .findAny()
                .map(u -> u.getPasswordHash()
                        .equals(DigestUtils.sha512Hex(userDTO.getPassword())))
                .orElse(false);
    }
}
