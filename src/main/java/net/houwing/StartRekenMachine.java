package net.houwing;

import net.houwing.service.InvoerRekenMachine;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartRekenMachine {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("net.houwing");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        InvoerRekenMachine invoerRekenMachine = context.getBean(InvoerRekenMachine.class);
        invoerRekenMachine.gebruikRekenMachine();
        //context.getBean(Invoer.class).startCalculator();
//        //invoer.bereken();
    }
}
