package pl.sda.intermediate16;

import java.util.concurrent.atomic.AtomicInteger;

public class Bank {
    private static Integer balance = 10000;
    public static AtomicInteger counter = new AtomicInteger(0);
    //atomic jest potrzebny by szynchronizować zmienną do kilku metod i wątków
    //atomic jeśli dostanie w tej samej chwili kilka żądań zmiany wartości o żadnej nie zapomni, Intger może zapomnieć

    public synchronized static void withdraw(Integer howMuch){
        //metody są zsynchronizowane żeby tylko jeden wątek korzystał z jednej metody jednocześnie
        // bo inaczej klika wątków korzysta z tego samego zasobu i następuje błąd w obliczeniach
        balance = balance - howMuch;
        System.out.println(Thread.currentThread().getName() + " stan konta po wypłacie: " + balance);
    }
    public synchronized static void deposit(Integer howMuch){
        balance = balance + howMuch;
        counter.getAndIncrement();
//        synchronized (Bank.class) { blok synchronized pozwala na działanie na Integerze
//            counter = ++counter;
//        }
        System.out.println(Thread.currentThread().getName() + " stan konta po wpłacie: " + balance);
    }
}