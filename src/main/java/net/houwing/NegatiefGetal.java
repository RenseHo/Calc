package net.houwing;

import java.util.ArrayList;
import java.util.List;

public class NegatiefGetal {

    public static void main(String[] args) {
        List<Character> formule = new ArrayList();
        formule.add(new Character('-'));
        formule.add(new Character('1'));
        formule.add(new Character('2'));
        formule.add(new Character('+'));
        formule.add(new Character('3'));
        //formule.add(new Character('*'));
        formule.add(new Character('('));
        formule.add(new Character('-'));
        formule.add(new Character('5'));
        formule.add(new Character('+'));
        formule.add(new Character('2'));
        formule.add(new Character(')'));
        formule.add(new Character('/'));
        formule.add(new Character('2'));
        for (Character item:formule){
            System.out.print(item);
        }
        //System.out.println();
        for (int counter = 0; counter < formule.size(); counter++) {
            Character item;
            item = formule.get(counter);
            if (item.equals('-')){
                System.out.println();
                System.out.println("Een min te pakken op positie : "+counter);
                if(counter == 0){
                    System.out.println("Negtief getal op positie 1..");
                }else if(!Character.isDigit(formule.get(counter-1))) {
                    System.out.println("Vorige item is GEEN getal.. dus een negatief getal");
                }
            }
        }
    }
}
