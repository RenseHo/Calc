package net.houwing.model;

import net.houwing.repository.HistoryInterface;
import net.houwing.service.Bereken;
import net.houwing.service.FormatteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class RekenMachine {

    private Scanner in = new Scanner(System.in);
    private HistoryInterface historyInterface;
    private FormatteringService formatteringService = new FormatteringService();
    private Bereken bereken;
    Character[] checkFunctions = {'s','c','t','e','p'};

    @Autowired
    public RekenMachine(HistoryInterface historyInterface, Bereken bereken) {
        this.historyInterface = historyInterface;
        this.bereken = bereken;
    }


    //public RekenMachine(@Qualifier("cacheHistory") HistoryInterface historyInterface) {this.historyInterface = historyInterface}

    public void gebruikRekenMachine() {
        boolean running = true;
        String vorigeResultaatFormule = "";
        //List<String> history = new ArrayList<>();
        while (running) {
            String inputUser ="start";
            String restantInput = "start";
            while (!restantInput.isEmpty()) {
                if (restantInput.equals("start")) {
                    System.out.println("Voer de formule in en sluit af met ENTER. Om te stoppen type EXIT");
                }else{
                    System.out.println("Helaas een foute invoer. De waarde(n) '" + restantInput + "' worden NIET ondersteund zoals deze is ingevoerd.");
                    System.out.println("Voer een formule in. Sluit af met een ENTER. Om te stoppen type EXIT.");
                }
                inputUser = in.nextLine().toLowerCase();
                String regExpr = "hist[\\+\\-\\*/][0-9]|exit|[0-9]|-|\\+|\\*|\\(|\\)|/|\\.|sin\\([0-9]?.?[0-9]\\)|cos\\([0-9]?.?[0-9]\\)|tan\\([0-9]?.?[0-9]\\)|sqrt\\([0-9]|pow\\(-?[0-9]?.?[0-9],-?[0-9]?.?[0-9]\\)|pi\\(\\)|euler\\(\\)";
                restantInput = inputUser.replaceAll(regExpr, "");
            }
            if (!inputUser.equals("exit")) {
                String resultaatFormule;
                if (inputUser.startsWith("hist") & !vorigeResultaatFormule.equals("")) {
                    inputUser = vorigeResultaatFormule.concat(inputUser.substring(4));
                }
                if (inputUser.startsWith("hist") & vorigeResultaatFormule.equals("")){
                    inputUser = inputUser.substring(5);
                    System.out.println("Vorige resultaat bestaat niet, dus hist genegeerd.");
                }
                historyInterface.addHistory(inputUser);
                List<String> formuleStrings = formatteringService.formuleFormattering(inputUser,checkFunctions);
                List<String> resultaatNaFuncties = bereken.berekenFuncties(formuleStrings,checkFunctions);
                resultaatFormule = bereken.berekenResultaat(formuleStrings);
                System.out.println("De uitkomst van de formule \""+inputUser+ "\" is : " + resultaatFormule);
                vorigeResultaatFormule = resultaatFormule;
            }else{
                running = false;
                System.out.println("De ingevoerde formules zijn : ");
                if (historyInterface.getHistory().isEmpty()) {
                    System.out.println("....Geen formules berekend....");
                }else{
                    for (String historyItem : historyInterface.getHistory()) {
                        System.out.println(historyItem);
                    }
                }
                System.out.print("Druk op ENTER om het scherm te sluiten .....");
                in.nextLine();
            }
        }
    }
}
