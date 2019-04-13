package pl.sda.intermediate16;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate16.users.User;
import pl.sda.intermediate16.users.UserLoginDTO;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Lambdas {

    private List<String> animals =
            Arrays.asList("cat", "dog", "mouse", "rat", "pig   ", "rabbit", "hamster", "  ", " parrot", null);

    List<String> someList = new ArrayList<>();

    @Test
    void lambdasOne() {

        List<String> transformedAnimals = animals.stream()
                .filter(a -> StringUtils.isNotBlank(a))
                .map(a -> a.trim())
                .collect(Collectors.toList());

        for (int i = 0; i < transformedAnimals.size(); i++) {
            if (i < transformedAnimals.size() - 1) {
                System.out.print(transformedAnimals.get(i) + ", ");
            } else {
                System.out.println(transformedAnimals.get(i) + ".");
            }
        }

        System.out.println(animals.stream()
                .filter(a -> StringUtils.isNotBlank(a))
                .map(a -> a.trim())
                .collect(Collectors.joining(", ", "", ".")));
    }


    Runnable runnable = () -> System.out.println("runnable");

    @FunctionalInterface
    public interface SuperChecker {
        boolean check(Integer a);
    }

    @Test
    void checkers() {
        SuperChecker anonymousSuperChecker = new SuperChecker() {
            @Override
            public boolean check(Integer a) {
                return a % 2 == 0;
            }
        };
        SuperChecker lambdaSuperChecker = a -> a % 2 == 0;
        System.out.println(lambdaSuperChecker.check(3));
    }

    @FunctionalInterface
    public interface MyBiComparator<T, E> {
        Integer compare(T firstValue, E secondValue);
    }

    @Test
    void comparatorTest() {
        Integer firstValue = 3;
        String secondValue = "4";

        MyBiComparator<Integer, String> comparator = (a, b) -> a.compareTo(Integer.valueOf(b));
        MyBiComparator<User, UserLoginDTO> emailEomparator = (a, b) -> a.getLogin().compareTo(b.getEmail());

        User user = new User();
        user.setLogin("aaaa@aaa.wp");
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("bbb@bbb.wp");

        Integer compare = emailEomparator.compare(user, userLoginDTO);
        System.out.println(compare);

    }

    @FunctionalInterface
    public interface MathOperation<T, E> {
         Double operation(T firstValue, E secondValue);
    }

    @Test
    void adding() {
        MathOperation<Integer, Long> add =
                (f, s) -> Double.valueOf(f) + Double.valueOf(s);

        System.out.println(add.operation(4, 5L));

        MathOperation<Float, Integer> subtract = (a, b) -> Double.valueOf(a) - Double.valueOf(b);


        System.out.println(subtract.operation(2.3f, 2));

        BiFunction<Float, Integer, Double> biFunction = (a, b) -> Double.valueOf(a) - Double.valueOf(b);
        System.out.println(biFunction.apply(2.3f,2));


    }
}
