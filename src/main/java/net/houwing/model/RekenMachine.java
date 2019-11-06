package net.houwing.model;

import net.houwing.repository.History;
import net.houwing.service.Bereken;
import net.houwing.service.BerekenService;
import net.houwing.service.FormatteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class RekenMachine {

    private Scanner in = new Scanner(System.in);
    private History history = new History();
    private FormatteringService formatteringService = new FormatteringService();
    private Bereken bereken;

    @Autowired
    public RekenMachine(Bereken bereken) { this.bereken = bereken; }

    public void gebruikRekenMachine() {
        boolean running = true;
        //List<String> history = new ArrayList<>();
        while (running) {
            String inputUser ="start";
            String restantInput = "start";
            while (!restantInput.isEmpty()) {
                if (restantInput.equals("start")) {
                    System.out.println("Voer de formule in en sluit af met ENTER. Om te stoppen type EXIT");
                }else{
                    System.out.println("FOUTE INVOER. De waarden '" + restantInput + "' worden NIET ondersteund.");
                    System.out.println("Voer een formule in. Sluit af met een ENTER. Om te stoppen type EXIT.");
                }
                inputUser = in.nextLine().toLowerCase();
                // ToDo Maak een betere regular expression... bijvb... inputUser.matches(...) ??
                restantInput = inputUser.replaceAll("[0-9+*()\\-/.exit]", "");
                //
            }
            if (!inputUser.equals("exit")) {
                String resultaatFormule;
                history.addHistory(inputUser);
                List<String> formuleStrings = formatteringService.formuleFormattering(inputUser);
                resultaatFormule = bereken.berekenResultaat(formuleStrings);
                System.out.println("De uitkomst van de formule \""+inputUser+ "\" is : " + resultaatFormule);
            }else{
                running = false;
                System.out.println("De ingevoerde formules zijn : ");
                if (history.getHistory().isEmpty()) {
                    System.out.println("....Geen formules berekend....");
                }else{
                    for (String historyItem : history.getHistory()) {
                        System.out.println(historyItem);
                    }
                }
            }
        }
    }
}
