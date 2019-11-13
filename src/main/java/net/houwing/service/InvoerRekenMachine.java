package net.houwing.service;

import net.houwing.repository.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class InvoerRekenMachine {

    private Scanner in = new Scanner(System.in);
    private History history;
    private String inputUser ;
    static String inputUserOrgineel;
    private FormatteringService formatteringService = new FormatteringService();  //toevoegen aan de constructor..
    private Bereken bereken;
    private static final String INPUT_REGULAR_EXPRESSION = "hist|exit|[0-9]|-|\\+|\\*|\\(|\\)|/|\\.|sin\\([0-9]?.?[0-9]\\)|cos\\([0-9]?.?[0-9]\\)|tan\\([0-9]?.?[0-9]\\)|sqrt\\([0-9]|pow\\(-?[0-9]?.?[0-9],-?[0-9]?.?[0-9]\\)|pi\\(\\)|euler\\(\\)";
    private static final String INVOER_TEKST = "Voer de formule in en sluit af met ENTER. Om te stoppen type EXIT";

    @Autowired
    InvoerRekenMachine(History history, Bereken bereken) {
        this.history = history;
        this.bereken = bereken;
    }

    public void gebruikRekenMachine() {
        boolean running = true;
        while (running) {
            isGevalideerdeInvoer();
            if (!inputUser.equals("exit")) {
                inputUserOrgineel = inputUser;
                if (!inputUser.contains("hist") | (inputUser.contains("hist") & !history.getHistory().isEmpty())) {
                    if (inputUser.contains("hist")) {
                        inputUser = inputUser.replaceAll("hist", Double.toString(history.getLastHistoryValue()));
                    }
                    List<String> formuleStrings = formatteringService.formuleFormattering(inputUser);
                    String resultaatFormule = bereken.berekenResultaat(formuleStrings, inputUser);
                    System.out.println("De uitkomst van de formule \"" + inputUserOrgineel + "\" is : " + resultaatFormule);
                }else if (inputUser.contains("hist") & history.getHistory().isEmpty()) {
                    System.out.println("Er is nog geen history. De formule kan niet worden uitgerekend ....");
                }
            }else{
                running = false;
                System.out.println("De ingevoerde formules zijn : ");
                if (history.getHistory().isEmpty()) {
                    System.out.println("....Geen formules berekend....");
                }else{
                    System.out.println("niet leeg dus...");
                    System.out.println("Ingevoerde formules : " + history.getHistory());    //Nog mooi opmaken...
                }
                System.out.print("Druk op ENTER om het scherm te sluiten .....");
                in.nextLine();
            }
        }
    }

    private boolean isGevalideerdeInvoer ( ) {

        String restantInput = "start";
        while (!restantInput.isEmpty()) {
            if (restantInput.equals("start")) {
                System.out.println(INVOER_TEKST);
            }else{
                System.out.println("Helaas een foute invoer. De waarde(n) '" + restantInput + "' worden NIET ondersteund zoals deze is ingevoerd.");
                System.out.println(INVOER_TEKST);
            }
            inputUser = in.nextLine().toLowerCase();
            restantInput = inputUser.replaceAll(INPUT_REGULAR_EXPRESSION, "");
        }
        return true;
    }
}
