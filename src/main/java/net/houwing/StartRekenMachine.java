package net.houwing;

import net.houwing.model.RekenMachine;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartRekenMachine {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("net.houwing");
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        RekenMachine rekenMachine = context.getBean(RekenMachine.class);
        rekenMachine.gebruikRekenMachine();
        //context.getBean(Invoer.class).startCalculator();
//        //invoer.bereken();
    }
}
