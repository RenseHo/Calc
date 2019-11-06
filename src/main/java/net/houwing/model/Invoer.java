package net.houwing.model;

import net.houwing.service.Bereken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Invoer {

    private Scanner in = new Scanner(System.in);
    private Bereken bereken;

    @Autowired
    Invoer(Bereken bereken) {
        this.bereken = bereken;
    }


    public void startCalculator() {
        boolean running = true;
        List<String> history = new ArrayList<>();
        while (running) {
            String inputUser ="start";
            String restInput = "start";
            while (!restInput.isEmpty()) {
                if (restInput.equals("start")) {
                    System.out.println("Voer de formule in en sluit af met ENTER. Om te stoppen type EXIT");
                }else{
                    System.out.println("FOUTE INVOER. De waarden '" + restInput + "' worden NIET ondersteund.");
                    System.out.println("Voer een formule in. Sluit af met een ENTER. Om te stoppen type EXIT.");
                }
                inputUser = in.nextLine().toLowerCase();
                // ToDo Maak een betere regular expression... bijvb... inputUser.matches(...) ??
                restInput = inputUser.replaceAll("[0-9+*()\\-/.exit]", "");
                //
            }
            if (!inputUser.equals("exit")) {
                String result;
                history.add(inputUser);
                List<String> formuleStrings = setFormuleArray(inputUser);
                result = calculateResult(formuleStrings);
                System.out.println("De uitkomst IS : " + result);
            }else{
                running = false;
                System.out.println("De ingevoerde formules zijn : ");
                if (history.isEmpty()) {
                    System.out.println("....Geen formules berekend....");
                }else{
                    for (String item : history) {
                        System.out.println(item);
                    }
                }
            }
        }
    }

    List<String> setFormuleArray(String input) {
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

    String calculateResult(List<String> inputUser){
        int found;
        int positie1;
        int positie2;
        int positie3;
        String uitKomst;
        List<String> formule = inputUser;
        System.out.print("Tussen waarde : ");
        for (String item:formule){
            System.out.print(item);
        }
        System.out.println();
                /*
                 * Vind eerste (, bereken de waarde tussen () en
                 * zet het resltaat terug in de formule array;
                 */
        found = formule.indexOf(")");
        if(found > -1) {
            positie1=found-3;
            positie2=found-2;
            positie3=found-1;
            formule = bereken.setFormule(positie1,positie2,positie3,formule);
            formule.remove(positie2);
            formule.remove(positie1-1);
            System.out.print("Tussen waarde : ");
            for (String item:formule){
                System.out.print(item);
            }
            System.out.println();
        }
                /*
                 * Vind eerstvolgende * of /. Bereken het resultaat wat bij deze operator hoort en
                 * zet het resltaat terug in de formule array;
                 */
        while ((formule.indexOf("*") > -1) || (formule.indexOf("/") > -1)) {
            positie2 = -2;
            if(formule.indexOf("*") == -1) positie2 = formule.indexOf("/") ;
            if(formule.indexOf("/") == -1) positie2 = formule.indexOf("*") ;
            if(positie2 == -2){
                if(formule.indexOf("*") < formule.indexOf("/")){
                    positie2 = formule.indexOf("*");
                }else{
                    positie2 = formule.indexOf("/");
                }
            }
            positie1=positie2-1;
            positie3=positie2+1;
            formule = bereken.setFormule(positie1,positie2,positie3,formule);
            System.out.print("Tussen waarde : ");
            for (String item:formule){
                System.out.print(item);
            }
            System.out.println();
        }

        while ((formule.indexOf("+") > -1) || (formule.indexOf("-") > -1)) {
            positie2 = -2;
            if(formule.indexOf("+") == -1) positie2 = formule.indexOf("-") ;
            if(formule.indexOf("-") == -1) positie2 = formule.indexOf("+") ;
            if(positie2 == -2){
                if(formule.indexOf("+") < formule.indexOf("-")){
                    positie2 = formule.indexOf("+");
                    //System.out.println("+");
                }else{
                    positie2 = formule.indexOf("-");
                    //System.out.println("-");
                }
            }
            positie1=positie2-1;
            positie3=positie2+1;
            formule = bereken.setFormule(positie1,positie2,positie3,formule);
            System.out.print("Tussen waarde : ");
            for (String item:formule){
                System.out.print(item);
            }
            System.out.println();
        }
        uitKomst = formule.get(0);
        System.out.println("Resultaat : "+uitKomst);
        return uitKomst;
    }
}
