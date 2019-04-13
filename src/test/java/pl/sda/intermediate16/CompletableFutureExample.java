package pl.sda.intermediate16;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.Function;

public class CompletableFutureExample {


    private Function<Long, String> addInfoToString = a -> a + "...";
    private Function<BigDecimal, String> priceToString = a -> a.toPlainString();
    private Function<String, String> photosToString = a -> a + "!";
    private Function<String, String> descrToString = a -> a + ".";

    @Test
    void oneByOne() {
        String addInfoInString = transform(downloadAdditionalInfo(), addInfoToString);
        String priceInString = transform(downloadPrice(), priceToString);
        String photosInString = transform(downloadPhotos(), photosToString);
        String descrInString = transform(downloadDescription(), descrToString);
        ProductForTest product = new ProductForTest(descrInString, priceInString, photosInString, addInfoInString);
        System.out.println(product);
    }

    @Test
    void threads() {
        Thread t1 = new Thread(()->transform(downloadAdditionalInfo(), addInfoToString));
        Thread t2 = new Thread(()->transform(downloadPrice(), priceToString));
        Thread t3 = new Thread(()->transform(downloadPhotos(), photosToString));
        Thread t4 = new Thread(()->transform(downloadDescription(), descrToString));

        System.out.println("nadawanie nazw");

        t1.start(); //uruchamiamy wątki
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join(); //oczekujemy na rezultaty
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private <T> String transform(T value, Function<T, String> function) {
        simulateDelay(2000);
        return function.apply(value);
    }


//    private String transform(String photos) { //LAME
//        return photos + "!!!";
//    }
//
//    private String transform(BigDecimal price) {
//        return price.toPlainString();
//    }
//
//    private String transform(Long addInfo) {
//        return addInfo+".";
//    }

//    private <T> String transform(T value) { //nearly nice
//        return value.toString();
//    }

    private String downloadDescription() {
        simulateDelay(3000);
        return "opis";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        return BigDecimal.valueOf(1000);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        return "zdjecia";
    }

    private Long downloadAdditionalInfo() {
        simulateDelay(3300);
        return 30L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Data
    @AllArgsConstructor
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalInfo;
    }


}
