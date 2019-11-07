package net.houwing.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FormatteringService {

    public List<String> formuleFormattering(String inputUser, Character[] checkFunctions) {
        List<String> formuleStrings = new ArrayList<>();
        //Character[] checkFunctions = {'s','c','t','e','p'};
        for (int t = 0; t < inputUser.length(); t++) {      //t is index van de tekens van de string inputUser.
            Character teken = inputUser.charAt(t);
            StringBuilder waardeString = new StringBuilder();
            int e = t+1;                                    //e is de eindindex om het laatste teken van de string te bepalen.
            if (!Character.isDigit(teken)) {
                if(Arrays.asList(checkFunctions).contains(teken)){
                    for (; e < inputUser.length() ; e++){       // bepaal de eind index
                        if (inputUser.charAt(e) == ')' | (String.valueOf(teken) + inputUser.charAt(e)).equals("pi")){       //bepaal of het einde van de functie is bereikt.
                            break;
                        }
                    }
                   for (; t <= e  ; t++){                       //Vul de waardeString met getal(len).
                        waardeString.append(inputUser.charAt(t));
                    }
                    t = e;                                  //Start de volgende iteratie met de eind index
                    formuleStrings.add(String.valueOf(waardeString));
                }
                if (teken == '/' || teken == '*' || teken == '+' || teken == '-' || teken == '(' || teken == ')') {
                    if (teken.equals('+') & t == 0 ) {
                        continue;
                    }
                    if (teken.equals('-')) {
                        boolean bepaalNeg = false;
                        if (t == 0) {                                               //Negatieve waardeString start op index 0
                            //System.out.println("Negatief waardeString op positie 1. Vervolgens de grootte van het neg getal bepalen...");
                            bepaalNeg = true;
                        } else if (!Character.isDigit(inputUser.charAt(t - 1))) {        //Negatieve waardeString start op index i
                            //System.out.println("Vorige item is GEEN waardeString.. dus een negatief getal. Vervolgens de grootte van het neg getal bepalen...");
                            bepaalNeg = true;
                        } else {
                            waardeString.append(teken);                //teken is een min teken, geen neg getal.
                            formuleStrings.add(String.valueOf(waardeString));
                        }
                        if (bepaalNeg){                                 //teken is het eerste teken van een neg getal.
                            t = vulFormuleStrings(inputUser, e, t, waardeString,formuleStrings);
                        }
                    }else{
                        waardeString.append(teken);             //waardeString is een teken maar geen min teken.
                        formuleStrings.add(String.valueOf(waardeString));
                    }
                }
            }else{                      //teken is een getal
                t = vulFormuleStrings(inputUser, e, t, waardeString,formuleStrings);
            }
        }
        return formuleStrings;
    }

    int vulFormuleStrings(String inputUser , int e , int t , StringBuilder waardeString , List<String> formuleStrings)  {
        for (; e < inputUser.length() ; e++){       // bepaal de eind index
            if (!Character.isDigit(inputUser.charAt(e)) & inputUser.charAt(e) !='.'){
                break;
            }
        }
        for (; t < e  ; t++){                       //Vul de waardeString met getal(len).
            waardeString.append(inputUser.charAt(t));
        }
        t = e-1;                                  //Start de volgende iteratie met de eind index
        formuleStrings.add(String.valueOf(waardeString));
        return t;                                   //return de laatste index waarde van de string.
    }





}
