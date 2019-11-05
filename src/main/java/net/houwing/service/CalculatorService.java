package net.houwing.service;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CalculatorService implements Calculator {

    @Override
    public List<String> setFormule(int pos1, int pos2, int pos3, List<String> formuleIn){
        String getal1 = formuleIn.get(pos1);
        String operator = formuleIn.get(pos2);
        String getal2 = formuleIn.get(pos3);
        int result3 = 0;
        try {
            switch (operator) {
                case "*":
                    result3 =  Integer.parseInt(getal1) * Integer.parseInt(getal2);
                    break;
                case "/":
                    result3 =  Integer.parseInt(getal1) / Integer.parseInt(getal2);
                    break;
                case "+":
                    result3 =  Integer.parseInt(getal1) + Integer.parseInt(getal2);
                    break;
                case"-":
                    result3 =  Integer.parseInt(getal1) - Integer.parseInt(getal2);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Voer een juiste formule in!!!");
        }
        formuleIn.set(pos1,Integer.toString(result3));
        formuleIn.remove(pos3);
        formuleIn.remove(pos2);
        return formuleIn;
    }
}
