package pl.sda.intermediate16;

public class OurRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " lambda runnable");
        //ctrl + shift + V historia schowka, możemy tam znaleźć ostatnie wklejki
    }
}
