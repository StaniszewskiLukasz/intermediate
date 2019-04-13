package pl.sda.intermediate16;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate16.categories.CategorySearchService;
import pl.sda.intermediate16.users.*;
import pl.sda.intermediate16.weather.WeatherService;

import java.util.Map;

@Controller
public class OnlyOneController { //ta klasa pozwala kontaktować się przeglądarce z naszą aplikacją

    //DEPENDENCY INJECTION
    UserDAO userDAO = new UserDAO(); //one to rule them all
    UserLoginService usl = new UserLoginService(userDAO);
    CategorySearchService categorySearchService = new CategorySearchService();
    UserValidationService userValidationService = new UserValidationService();
    UserRegistrationService userRegistrationService = new UserRegistrationService(userDAO);

    @RequestMapping("/")
    public String ok() {
        return "index";
    }

    //adnotacja
    @RequestMapping("/categories") //pod takim urlem dostępna jest strona z kategoriami
    public String categories(String searchText, Model model) {
        model.addAttribute("catsdata", categorySearchService.filterCategories(searchText));
        return "catspage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("form", new UserRegistrationDTO());
        model.addAttribute("countries", Countries.values());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //ta metoda (POST) obsluguje wyslanie danych z frontu
    public String retrieveRegisterForm(UserRegistrationDTO userRegistrationDTO, Model model) {
        Map<String, String> errorMap = userValidationService.validate(userRegistrationDTO);
        model.addAttribute("form", userRegistrationDTO);
        model.addAttribute("countries", Countries.values());
        if (errorMap.isEmpty()) {
            try {
                userRegistrationService.register(userRegistrationDTO);
            } catch (UserExistsException e) {
                model.addAttribute("userExistsExceptionMessage", e.getMessage());
                return "registerForm";
            }
        } else {
            model.addAllAttributes(errorMap);
            return "registerForm";
        }
        return "registerEffect";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserLoginDTO dto, Model model) {
        if (usl.login(dto)) {
            UserContextHolder.logUserIn(dto);
        }
        return "index";
   }

   @ResponseBody//to mowi, że będzie jsonem a nie nazwą htmla
   @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String showWeather(){
       WeatherService weatherService = new WeatherService(userDAO);
       return new Gson().toJson(weatherService.getWeather());
   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        UserContextHolder.logout();
        return "index";
   }

}