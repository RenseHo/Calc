package net.houwing.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BerekenService implements Bereken {

    public List<String> setFormule(int pos1, int pos2, int pos3, List<String> formuleIn){
        //ToDo als userInput begint met * of / komt er een exception
        String eersteGetal = formuleIn.get(pos1);
        String operator = formuleIn.get(pos2);
        String tweedeGetal
                = formuleIn.get(pos3);
        double result3 = 0;
        try {
            switch (operator) {
                case "*":
                    result3 =  Double.parseDouble(eersteGetal) * Double.parseDouble(tweedeGetal);
                    break;
                case "/":
                    result3 =  Double.parseDouble(eersteGetal) / Double.parseDouble(tweedeGetal);
                    break;
                case "+":
                    result3 =  Double.parseDouble(eersteGetal) + Double.parseDouble(tweedeGetal);
                    break;
                case"-":
                    result3 =  Double.parseDouble(eersteGetal) - Double.parseDouble(tweedeGetal);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Voer een juiste formule in!!!");
        }
        formuleIn.set(pos1,Double.toString(result3));
        formuleIn.remove(pos3);
        formuleIn.remove(pos2);
        return formuleIn;
    }

    public List<String> berekenFuncties(List<String> formuleStrings, Character[] checkFunctions) {
        //int found;
        //List<String> formule = inputUser;
        boolean found = false;
        //found = inputUser.indexOf(")");

        for (int i = 0; i < formuleStrings.size(); i++) {
            String userString = formuleStrings.get(i);
            char toCheck = userString.charAt(0);
            for (Character functieItem : checkFunctions) {
                found = functieItem.equals(toCheck);
                break;
            }
            if (found){
                StringBuilder functie= new StringBuilder();
                functie.append(userString.charAt(0));
                System.out.println("User String is "+userString);
                for (int k = 1; k < userString.length(); k++)
                    if (userString.charAt(k) != '('){
                        functie.append(userString.charAt(k));
                    }else{
                       //ToDo TOT Hier... Kun je ook een naam aanroepen van een methode?
                    }
                }
        }
        return formuleStrings;
    }

    public String berekenResultaat(List<String> inputUser){
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
        * Bereken eerst de functies.
        * en dan de rest
         */


        /*
         * Vind eerste (, bereken de waarde tussen () en
         * zet het resltaat terug in de formule array;
         */
        found = formule.indexOf(")");
        if(found > -1) {
            positie1=found-3;
            positie2=found-2;
            positie3=found-1;
            formule = setFormule(positie1,positie2,positie3,formule);
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
            formule = setFormule(positie1,positie2,positie3,formule);
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
            formule = setFormule(positie1,positie2,positie3,formule);
            System.out.print("Tussen waarde : ");
            for (String item:formule){
                System.out.print(item);
            }
            System.out.println();
        }
        uitKomst = formule.get(0);
        Double numberDouble = Double.parseDouble(uitKomst);
        if (Math.ceil(numberDouble) == Math.floor(numberDouble)){
            uitKomst = uitKomst.substring(0,(uitKomst.indexOf('.')));
        };
        return uitKomst;
    }

}
