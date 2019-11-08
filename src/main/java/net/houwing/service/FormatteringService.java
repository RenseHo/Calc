package net.houwing.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FormatteringService {

    public List<String> formuleFormattering(String inputUser, Character[] checkFunctions) {
        List<String> formuleStrings = new ArrayList<>();
        Map<String, Integer> functieTabel = new HashMap<>();
        functieTabel.put("sin",1);
        functieTabel.put("cos",1);
        functieTabel.put("tan",1);
        functieTabel.put("sqrt",1);
        functieTabel.put("pow",2);
        functieTabel.put("exp",2);
        functieTabel.put("pi",0);
        functieTabel.put("euler",0);

        for (int indexTeken = 0; indexTeken < inputUser.length(); indexTeken++) {      //indexTeken is index van de tekens van de string inputUser.
            Character teken = inputUser.charAt(indexTeken);
            StringBuilder functieNaam = new StringBuilder();
            StringBuilder parameters = new StringBuilder();
            StringBuilder waardeString = new StringBuilder();
            int aantalParameters = -1;
            int indexEind = indexTeken+1;                                    //e is de eindindex om het laatste teken van de string te bepalen.
            if (!Character.isDigit(teken)) {
                if(Arrays.asList(checkFunctions).contains(teken)){
                    List<String> functieArray = new ArrayList<>();
                    for (; indexEind < inputUser.length(); indexEind++) {       // bepaal de functie
                        if (inputUser.charAt(indexEind) == '(' ) {       //bepaal of het einde van de functie is bereikt.
                            break;
                        }
                    }
                    int f = indexTeken;
                    for (; f < indexEind; f++) {                       //Vul de functienaam variable.
                        functieNaam.append(inputUser.charAt(f));
                    }                 //Start de volgende iteratie met de eind index
                    for (String key : functieTabel.keySet()) {
                        if (functieNaam.toString().equals(key)) {
                            aantalParameters = functieTabel.get(key);
                            break;
                        }
                    }
                    functieArray.add(String.valueOf(functieNaam));
                    if (aantalParameters > 0) {
                        indexEind++;
                        for (; indexEind < inputUser.length(); indexEind++) {       // vul de parameters string
                            if (inputUser.charAt(indexEind) != ')') {
                                parameters.append(inputUser.charAt(indexEind));         //209,150
                            } else {
                                break;
                            }
                        }
                        indexTeken = indexEind;
                        String[] params = parameters.toString().split(",");
                        for (String param : params) {
                            functieArray.add(String.valueOf(param));
                        }
                    }else{
                        indexTeken = indexEind + 1;
                    }
                        double param1;
                        double param2;
                        String functie = functieArray.get(0);
                        Double functieUitkomst = null;
                        try {
                            switch (functie) {
                                case "sin":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    functieUitkomst =  Math.sin(param1);
                                    break;
                                case "cos":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    functieUitkomst =  Math.cos(param1);
                                    break;
                                case "tan":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    functieUitkomst =  Math.tan(param1);
                                    break;
                                case "sqrt":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    functieUitkomst =  Math.sqrt(param1);
                                    break;
                                case "pow":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    param2 = Double.parseDouble(functieArray.get(2));
                                    functieUitkomst =  Math.pow(param1,param2);
                                    break;
                                case "exp":
                                    param1 = Double.parseDouble(functieArray.get(1));
                                    functieUitkomst =  Math.exp(param1);
                                    break;
                                case "pi":
                                    functieUitkomst =  Math.PI;
                                    break;
                                case "euler":
                                    functieUitkomst =  Math.E;
                                    break;
                            }
                        } catch (NumberFormatException ex) {
                            System.out.println("Gaat iets fout in het bepalen van het resultaat van de formule..");
                        }
                        if (functieUitkomst != null){
                            formuleStrings.add(String.valueOf(functieUitkomst));
                        }
                    //}
                }
                if (teken == '/' || teken == '*' || teken == '+' || teken == '-' || teken == '(' || teken == ')') {
                    if (teken.equals('+') & indexTeken == 0 ) {
                        continue;
                    }
                    if (teken.equals('-')) {
                        boolean bepaalNeg = false;
                        if (indexTeken == 0) {                                               //Negatieve waardeString start op index 0
                            bepaalNeg = true;
                        } else if (!Character.isDigit(inputUser.charAt(indexTeken - 1))) {        //Negatieve waardeString start op index i
                            bepaalNeg = true;
                        } else {
                            waardeString.append(teken);                //teken is een min teken, geen neg getal.
                            formuleStrings.add(String.valueOf(waardeString));
                        }
                        if (bepaalNeg){                                 //teken is het eerste teken van een neg getal.
                            indexTeken = vulFormuleStrings(inputUser, indexEind, indexTeken, waardeString,formuleStrings);
                        }
                    }else{
                        waardeString.append(teken);             //waardeString is een teken maar geen min teken.
                        formuleStrings.add(String.valueOf(waardeString));
                    }
                }
            }else{                                                          //teken is een getal
                indexTeken = vulFormuleStrings(inputUser, indexEind, indexTeken, waardeString,formuleStrings);
            }
        }
        return formuleStrings;
    }

    private int vulFormuleStrings(String inputUser , int indexEind , int indexTeken , StringBuilder waardeString , List<String> formuleStrings)  {
        for (; indexEind < inputUser.length() ; indexEind++){       // bepaal de eind index
            if (!Character.isDigit(inputUser.charAt(indexEind)) & inputUser.charAt(indexEind) !='.'){
                break;
            }
        }
        for (; indexTeken < indexEind  ; indexTeken++){                       //Vul de waardeString met getal(len).
            waardeString.append(inputUser.charAt(indexTeken));
        }
        indexTeken = indexEind-1;                                  //Start de volgende iteratie met de eind index
        formuleStrings.add(String.valueOf(waardeString));
        return indexTeken;                                   //return de laatste index waarde van de string.
    }





}
