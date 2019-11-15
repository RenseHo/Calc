package probeersels;

import java.util.ArrayList;
import java.util.List;

public class Probeer3 {
    public static List<String> calculate(int pos1, int pos2, int pos3, List<String> formuleIn){
        String getal1 = formuleIn.get(pos1);
        String operator = formuleIn.get(pos2);
        String getal2 = formuleIn.get(pos3);
        int resultaat = 0;
        switch (operator) {
            case "*":
                System.out.println("  Een *");
                resultaat =  Integer.parseInt(getal1) * Integer.parseInt(getal2);
                break;
            case "/":
                System.out.println("  Een /");
                resultaat =  Integer.parseInt(getal1) / Integer.parseInt(getal2);
                break;
            case "+":
                System.out.println("  Een +");
                resultaat =  Integer.parseInt(getal1) + Integer.parseInt(getal2);
                break;
            case"-":
                System.out.println("  Een -");
                resultaat =  Integer.parseInt(getal1) - Integer.parseInt(getal2);
                break;
        }

        formuleIn.set(pos1-1,Integer.toString(resultaat));
        formuleIn.remove(pos3+1);
        formuleIn.remove(pos3);
        formuleIn.remove(pos2);
        formuleIn.remove(pos1);
        return formuleIn;
    }


    public static void main(String[] args) {
        int found=0;
        int positie1=0;
        int positie2=0;
        int positie3=0;
        List<String> formule = new ArrayList();
        formule.add(new String("11"));
        formule.add("+");
        formule.add(new String("40"));
        formule.add("*");
        formule.add("(");
        formule.add(new String("5"));
        formule.add("-");
        formule.add(new String("2"));
        formule.add(")");
        //formule.add("*");
        formule.add(new String("6"));
        formule.add("/");
        formule.add(new String("2"));
        for (String item:formule){
            System.out.print(item);
        }
        Boolean running = true;
        while (running) {
            /*
            * Vind eerste ), bereken de waarde tussen () en
            * zet het resltaat terug in de formule array;
            */
            found = formule.indexOf(")");
            if(found > -1) {
                positie1=found-3;
                positie2=found-2;
                positie3=found-1;
                formule = calculate(positie1,positie2,positie3,formule);
                for (String item:formule){
                    System.out.print(item);
                }
            }
            /*
            * Vind eerstvolgende * of /. Bereken het resultaat wat bij deze operator hoort en
            * zet het resltaat terug in de formule array;
            */

//            while ((formule.indexOf("*") > -1) || (formule.indexOf("/") > -1)) {
//                positie2 = -2;
//                if(formule.indexOf("*") == -1) positie2 = formule.indexOf("/") ;
//                if(formule.indexOf("/") == -1) positie2 = formule.indexOf("*") ;
//                if(positie2 == -2){
//                    if(formule.indexOf("*") < formule.indexOf("/")){
//                        positie2 = formule.indexOf("*");
//                        //System.out.println("*");
//                    }else{
//                        positie2 = formule.indexOf("/");
//                        //System.out.println("/");
//                    }
//                }
//                System.out.println(positie2);
//            }

            running = false;
        }
    }
}
