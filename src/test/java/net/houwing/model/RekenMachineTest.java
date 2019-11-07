package net.houwing.model;

import net.houwing.service.Bereken;
import net.houwing.service.BerekenService;
import net.houwing.service.FormatteringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class RekenMachineTest {

    private RekenMachine rekenMachine;
    private BerekenService berekenService;
    private Bereken bereken;
    private FormatteringService formatteringService;
    private List<String> formule = new ArrayList<>();

    @BeforeEach
    void setup(){
        this.berekenService = new BerekenService();
        this.rekenMachine = new RekenMachine(bereken);
        //this.rekenMachine = new RekenMachine(new BerekenService());
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
    void testCalculateResult(){
      String resultaat = berekenService.berekenResultaat(formule);
        assertThat(resultaat).isEqualTo("2");
    }
//    @Test
//    void testSetFormule(){
//        int positie1 = 5;
//        int positie2 = 6;
//        int positie3 = 7;
//        List<String> formuleString = new ArrayList<>();
//        formuleString.add("1");
//        formuleString.add("+");
//        formuleString.add("12");
//        formuleString.add("*");
//        formuleString.add("(");
//        formuleString.add("5");
//        formuleString.add("-");
//        formuleString.add("3");
//        formuleString.add(")");
//        formuleString.add("*");
//        formuleString.add("2");
//        List<String> testFormule = berekenService.setFormule(positie1,positie2,positie3,formuleString);
//        assertThat(testFormule).containsExactly("1","+","12","*","(","2",")","*","2");
//        assertThat(testFormule.size()).isEqualTo(9);
////        assertThatThrownBy(() -> calculatorService.setFormule(positie1,positie2,positie3,formuleString))
////                .isInstanceOf(Throwable.class)
////                .hasMessage("Error tekst");
//    }
}
