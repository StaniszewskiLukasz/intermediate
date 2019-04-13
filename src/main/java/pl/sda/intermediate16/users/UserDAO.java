package pl.sda.intermediate16.users;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDAO { //DATA ACCESS OBJECT - klasa dostepowa do zrodla danych
    private final String path = "C:/projects/users_data.txt";
    private List<User> userList = readUsers();

    private List<User> readUsers() {
        //odczytuje Usera z pliku
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveUser(User user) {
        userList.add(user);
        //wzorzec projektowy dekorator
        try (FileOutputStream fos = new FileOutputStream(path);
             //ten wie gdie ma zapsiać
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            //ten wie co ma zapisać
            oos.writeObject(userList);
            //ten wie gdzie zapisać
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
