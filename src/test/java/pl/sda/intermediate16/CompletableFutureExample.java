package pl.sda.intermediate16;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
        Thread t1 = new Thread(() -> transform(downloadAdditionalInfo(), addInfoToString));
        Thread t2 = new Thread(() -> transform(downloadPrice(), priceToString));
        Thread t3 = new Thread(() -> transform(downloadPhotos(), photosToString));
        Thread t4 = new Thread(() -> transform(downloadDescription(), descrToString));

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

    @Test
    void futures() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();
        System.out.println("Start");
        Future<String> descrFuture = executorService.submit(() -> downloadDescription());
        Future<BigDecimal> priceFuture = executorService.submit(() -> downloadPrice());
        Future<String> photosFuture = executorService.submit(() -> downloadPhotos());
        Future<Long> addInfoFuture = executorService.submit(() -> downloadAdditionalInfo());
        //jest sobie watek main i by samemu nie robić to wyrzuca futury i one trwają
        // a main na nie czeka i je pyta czy skończyly

        // długo trwający proces

        executorService.submit(() -> transform(descrFuture.get(), descrToString));
        executorService.submit(() -> transform(priceFuture.get(), priceToString));
        executorService.submit(() -> transform(photosFuture.get(), photosToString));
        executorService.submit(() -> transform(addInfoFuture.get(), addInfoToString));
        System.out.println("DOWÓD");
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }

    @Test
    void completableFuture() {
        CompletableFuture<String> descr1 = supplyAsync(() -> downloadDescription());
        CompletableFuture<String> descr2 = supplyAsync(() -> downloadDescription2());


        CompletableFuture<String> cf4 = descr1.applyToEitherAsync(descr2, e -> e)
                .thenApplyAsync(a -> transform(a, descrToString));


        CompletableFuture<String> cf2 = supplyAsync(() -> downloadPrice())
                .thenApplyAsync(a -> transform(a, priceToString));
        CompletableFuture<String> cf3 = supplyAsync(() -> downloadPhotos())
                .thenApplyAsync(a -> transform(a, photosToString));

        CompletableFuture<String> cf1 = supplyAsync(() -> downloadAdditionalInfo())
                .thenApplyAsync(a -> transform(a, addInfoToString));
        Stream.of(cf2, cf3, cf4, cf1).forEach(e -> e.join());

    }

    private <T> String transform(T value, Function<T, String> function) {
        simulateDelay(2000);
        System.out.println(Thread.currentThread().getName() + " TRANSFORMING: " + value);
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
        simulateDelay(4000);
        System.out.println(Thread.currentThread().getName() + " opis");
        return "opis";
    }

    private String downloadDescription2() {
        simulateDelay(3200);
        System.out.println(Thread.currentThread().getName() + " opis2");
        return "opis2";
    }

    private BigDecimal downloadPrice() {
        simulateDelay(2500);
        System.out.println(Thread.currentThread().getName() + " price");
        return BigDecimal.valueOf(1000);
    }

    private String downloadPhotos() {
        simulateDelay(3000);
        System.out.println(Thread.currentThread().getName() + " photos");
        return "zdjecia";
    }

    private Long downloadAdditionalInfo() {
        simulateDelay(3300);
        System.out.println(Thread.currentThread().getName() + " addInfo");
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
