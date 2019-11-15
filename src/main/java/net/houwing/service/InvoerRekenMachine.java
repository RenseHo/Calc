package net.houwing.service;

import net.houwing.repository.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Component
public class InvoerRekenMachine {

    //private Scanner in = new Scanner(System.in);
    private Scanner in;
    private PrintStream printStream;
    private History history;
    static String inputUserOrgineel;
    private FormatteringService formatteringService;
    private Bereken bereken;
    private static final String INPUT_REGULAR_EXPRESSION = "hist|pi|euler|exit|[0-9]|-|\\+|\\*|\\(|\\)|/|\\.|sin\\([0-9]?.?[0-9]\\)|cos\\([0-9]?.?[0-9]\\)|tan\\([0-9]?.?[0-9]\\)|sqrt\\([0-9]|pow\\(-?[0-9]?.?[0-9],-?[0-9]?.?[0-9]\\)";

    @Autowired
    InvoerRekenMachine(History history, Bereken bereken, FormatteringService formatteringService, Scanner scanner, PrintStream printStream) {
        this.history = history;
        this.bereken = bereken;
        this.formatteringService = formatteringService;
        this.in = scanner;
        this.printStream = printStream;
    }

    @Value("${tekst:Niet gevonden}")
    private String invoer;

    public void gebruikRekenMachine() {
        boolean running = true;
        while (running) {
            String inputUser = getGevalideerdeInvoer();
            if (!inputUser.equals("exit")) {
                inputUserOrgineel = inputUser;
                inputUser = inputUser
                        .replaceAll("pi", String.valueOf(Math.PI))
                        .replaceAll("euler", String.valueOf(Math.E));
                if (!inputUser.contains("hist") | (inputUser.contains("hist") & !history.getHistory().isEmpty())) {
                    if (inputUser.contains("hist")) {                   //if lus kan niet weg.
                        inputUser = inputUser.replaceAll("hist", Double.toString(history.getLastHistoryValue()));
                    }
                    List<String> formuleStrings = formatteringService.formuleFormattering(inputUser);
                    String resultaatFormule = bereken.berekenResultaat(formuleStrings, inputUser);
                    System.out.println("De uitkomst van de formule \"" + inputUserOrgineel + "\" is : " + resultaatFormule);
                } else if (inputUser.contains("hist") & history.getHistory().isEmpty()) {
                    System.out.println("Er is nog geen history. De formule kan niet worden uitgerekend ....");
                }
            } else {
                running = false;
                if (history.getHistory().isEmpty()) {

                    System.out.println("....Geen formules berekend....");
                } else {
                    System.out.println("Ingevoerde formules : " + history.getHistory());    //Nog mooi opmaken...
                }
                System.out.print("Druk op ENTER om het scherm te sluiten .....");
                in.nextLine();
            }
        }
    }

    private String getGevalideerdeInvoer() {

        String inputUser = "";
        String restantInput = "start";
        while (!restantInput.isEmpty()) {
            if (restantInput.equals("start")) {
                System.out.println(invoer);
            } else {
                printStream.println("Helaas een foute invoer. De waarde(n) '" + restantInput + "' worden NIET ondersteund zoals deze is ingevoerd.");
                printStream.println(invoer);
            }
            inputUser = in.nextLine().toLowerCase();
            restantInput = inputUser.replaceAll(INPUT_REGULAR_EXPRESSION, "");
        }
        return inputUser;
    }
}
