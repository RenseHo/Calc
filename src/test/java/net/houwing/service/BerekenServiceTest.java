package net.houwing.service;

import net.houwing.model.RekenMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import net.houwing.service.BerekenService;

import static org.assertj.core.api.Assertions.assertThat;

public class BerekenServiceTest {

    private BerekenService berekenService;

    @BeforeEach
    void setup(){
        this.berekenService = new BerekenService();
    }

    @Test
    void testSetFormule(){
        int positie1 = 5;
        int positie2 = 6;
        int positie3 = 7;
        List<String> formuleString = new ArrayList<>();
        formuleString.add("-10");
        formuleString.add("+");
        formuleString.add("12");
        formuleString.add("*");
        formuleString.add("(");
        formuleString.add("-5");
        formuleString.add("+");
        formuleString.add("3");
        formuleString.add(")");
        formuleString.add("*");
        formuleString.add("2");
        List<String> testFormule = berekenService.setFormule(positie1,positie2,positie3,formuleString);
        assertThat(testFormule).containsExactly("-10","+","12","*","(","-2",")","*","2");
        assertThat(testFormule.size()).isEqualTo(9);
//        assertThatThrownBy(() -> calculatorService.setFormule(positie1,positie2,positie3,formuleString))
//                .isInstanceOf(Throwable.class)
//                .hasMessage("Error tekst");
    }

    @Test
    public void testBerekenResultaat(){
        //ToDo bedenk nog een test hiervoor..
    }
}
