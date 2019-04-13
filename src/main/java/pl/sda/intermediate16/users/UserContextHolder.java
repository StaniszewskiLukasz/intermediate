package pl.sda.intermediate16.users;

import org.springframework.stereotype.Service;

@Service //powoduje ze to jest singletonem
public class UserContextHolder { //info o zalogowanym
    public static String email;

    public static void logUserIn(UserLoginDTO dto){
        email = dto.getEmail();
    }

    public String getUserLoggedIn(){
        return email;
    }
}
