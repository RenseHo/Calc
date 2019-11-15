package probeersels;

import java.util.*;

class Probeer2 {
    public static void main(String args[]) {
        List<String> formuleStrings = new ArrayList<>();
        Map<String, Integer> functieTabel = new HashMap<>();
        functieTabel.put("sin", 1);
        functieTabel.put("cos", 1);
        functieTabel.put("tan", 1);
        functieTabel.put("sqrt", 1);
        functieTabel.put("pow", 2);
        functieTabel.put("exp", 2);
        functieTabel.put("pi", 0);
        functieTabel.put("euler", 0);

//        for (String key : functieTabel.keySet())
//            System.out.println(key + " - " + functieTabel.get(key));
//        System.out.println();

        String inputUser = "exp(209,152)+9";
        for (int t = 0; t < inputUser.length(); t++) {      //t is index van de tekens van de string inputUser.
            Character teken = inputUser.charAt(t);
            StringBuilder functieNaam = new StringBuilder();
            StringBuilder parameters = new StringBuilder();
            int aantalParameters = -1;
            StringBuilder waardeString = new StringBuilder();
            int e = t + 1;                                    //e is de eindindex om het laatste teken van de string te bepalen.
            if (!Character.isDigit(teken)) {
                for (; e < inputUser.length(); e++) {       // bepaal de functie
                    if (inputUser.charAt(e) == '(' ) {       //bepaal of het einde van de functie is bereikt.
                        break;
                    }
                }
                int f = t;
                for (; f < e; f++) {                       //Vul de functienaam variable.
                    functieNaam.append(inputUser.charAt(f));
                }                 //Start de volgende iteratie met de eind index
                System.out.println("Functienaam is " + functieNaam);
                for (String key : functieTabel.keySet()) {
                    if (functieNaam.toString().equals(key)) {
                        aantalParameters = functieTabel.get(key);
                        System.out.println("Functie naam bestaat : " + key + " met parameters : " + aantalParameters);
                        break;
                    }
                }
                formuleStrings.add(String.valueOf(functieNaam));
                if (aantalParameters > 0) {
                    e++;
                    for (; e < inputUser.length(); e++) {       // vul de parameters string
                        if (inputUser.charAt(e) != ')') {
                            parameters.append(inputUser.charAt(e));         //209,150
                        } else {
                            break;
                        }
                    }
                    String[] params = parameters.toString().split("\\,");
                    for (int r = 0; r < params.length; r++) {
                        formuleStrings.add(String.valueOf(params[r]));
                    }
                }

            }
        }
    }
}

// (String.valueOf(teken) + inputUser.charAt(e)).equals("pi")
