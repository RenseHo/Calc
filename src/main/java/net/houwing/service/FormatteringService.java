package net.houwing.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormatteringService {

    public List<String> formuleFormattering(String input) {
        List<String> formuleStrings = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            Character teken = input.charAt(i);
            StringBuilder waardeString = new StringBuilder();
            int j = i+1;
            if (!Character.isDigit(teken)) {
                if (teken == '%' || teken == '/' || teken == '*' || teken == '+' || teken == '-' || teken == '(' || teken == ')') {
                    if (teken.equals('-')) {
                        boolean bepaalNeg = false;
                        if (i == 0) {                                               //Negatieve waardeString start op index 0 dus i
                            System.out.println("Negatief waardeString op positie 1. Vervolgens de grootte van het neg geal bepalen...");
                            bepaalNeg = true;
                        } else if (!Character.isDigit(input.charAt(i - 1))) {        //Negatieve waardeString start op index i
                            System.out.println("Vorige item is GEEN waardeString.. dus een negatief getal. Vervolgens de grootte van het neg geal bepalen...");
                            bepaalNeg = true;
                        } else {
                            waardeString.append(input.charAt(i));                //teken is een min teken
                        }
                        if (bepaalNeg){
                            for (; j < input.length() ; j++){      // bepaal de eind index
                                if (!Character.isDigit(input.charAt(j))){
                                    j = j -1;
                                    break;
                                }
                            }
                            for (; i <= j  ; i++){     //Vul de waardeString met een min teken + getal(len).
                                waardeString.append(input.charAt(i));
                            }
                            i = j;                      //Start de volgende iteratie met de eind index
                        }
                        formuleStrings.add(String.valueOf(waardeString));
                    }else{                  //waardeString is een teken maar geen min teken.
                        waardeString.append(teken);
                        formuleStrings.add(String.valueOf(waardeString));
                    }
                }
            }else{                      //teken is een getal
                for (; j < input.length() ; j++){      // bepaal de eind index van het getal
                    if (!Character.isDigit(input.charAt(j))){
                        break;
                    }
                }
                for (; i < j  ; i++){     //Vul de waardeString met het volledige getal.
                    waardeString.append(input.charAt(i));
                }
                i = j - 1;                      //Start de volgende iteratie met de eind index
                formuleStrings.add(String.valueOf(waardeString));
            }
        }
        return formuleStrings;
    }

}
