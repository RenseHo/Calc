package net.houwing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "net.houwing")
@PropertySource("classpath:language.properties")
public class AppConfig {

    @Bean
    public PrintStream printStream() {
        PrintStream out = System.out;
        return out;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}

//    @Bean
//    public Invoer invoer () {
//        return new Invoer();
//    }
//
//    @Bean
//    public Bereken bereken(){
//        return new Bereken() {
//            @Override
//            public List<String> setFormule(int pos1, int pos2, int pos3, List<String> formuleIn) {
//                return null;
//            }
//
//            @Override
//            public String berekenResultaat(List<String> inputUser) {
//                return null;
//            }
//        };
//    }

