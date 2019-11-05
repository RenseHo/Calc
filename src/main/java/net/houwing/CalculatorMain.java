package net.houwing;

import net.houwing.model.Invoer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalculatorMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("net.houwing");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Invoer invoer = context.getBean(Invoer.class);
        invoer.startCalculator();
        //invoer.bereken();
    }
}
