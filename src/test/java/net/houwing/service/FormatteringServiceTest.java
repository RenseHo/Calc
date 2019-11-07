package net.houwing.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class FormatteringServiceTest {

    private FormatteringService formatteringService;

    @BeforeEach
    void setup(){
        this.formatteringService = new FormatteringService();
    }

    @Test
    public void testFormuleFormattering(){
//        String input = "1+12*(5-3)*2";          //vergelijken of de inhoud van 2 lists gelijk zijn.
//        List<String> resultaat = formatteringService.formuleFormattering(input);
//        assertThat(resultaat).containsExactly("1","+","12","*","(","5","-","3",")","*","2");
        //assertEquals();
    }
}
