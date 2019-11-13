package net.houwing.service;

import net.houwing.repository.CacheHistory;
import net.houwing.repository.FileHistory;
import net.houwing.repository.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class InvoerRekenMachineTest {

    private InvoerRekenMachine invoerRekenMachine;
    private Bereken bereken;
    private BerekenService berekenService;
    private History history;
    private CacheHistory cacheHistory;
    private FileHistory fileHistory;
    private FormatteringService formatteringService;
    private List<String> formule = new ArrayList<>();

    @BeforeEach
    void setup(){
        this.cacheHistory = new CacheHistory();
        this.berekenService = new BerekenService(cacheHistory);
        this.fileHistory = new FileHistory();
        this.invoerRekenMachine = new InvoerRekenMachine(history, bereken);
        formule.clear();
        formule.add("1");
        formule.add("+");
        formule.add("1");
        formule.add("2");
        formule.add("*");
        formule.add("(");
        formule.add("5");
        formule.add("-");
        formule.add("3");
        formule.add(")");
        formule.add("*");
        formule.add("2");
    }

    @Test
    public void testGebruikRekenMachine(){
        //ToDo moet ik hier wat mee?
    }

    @Test
    void testCalculateResult(){                         //Deze test klopt niet...
        String inputUser = "1+12*(5-3)*2";
        String resultaat = berekenService.berekenResultaat(formule,inputUser);
        assertThat(resultaat).isEqualTo("2");
    }
    @Test
    void testSetFormule(){
        int positie1 = 5;
        int positie2 = 6;
        int positie3 = 7;
        List<String> formuleString = new ArrayList<>();
        formuleString.add("1");
        formuleString.add("+");
        formuleString.add("12");
        formuleString.add("*");
        formuleString.add("(");
        formuleString.add("5");
        formuleString.add("-");
        formuleString.add("3");
        formuleString.add(")");
        formuleString.add("*");
        formuleString.add("2");
        List<String> testFormule = berekenService.setFormule(positie1,positie2,positie3,formuleString);
        assertThat(testFormule).containsExactly("1","+","12","*","(","2.0",")","*","2");
        assertThat(testFormule.size()).isEqualTo(9);
//        assertThatThrownBy(() -> calculatorService.setFormule(positie1,positie2,positie3,formuleString))
//                .isInstanceOf(Throwable.class)
//                .hasMessage("Error tekst");
    }
}
