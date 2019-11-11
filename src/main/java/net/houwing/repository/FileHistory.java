package net.houwing.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@Profile("file")
public class FileHistory implements HistoryInterface {


    private List<String> history = new ArrayList<>();

    @Override
    public void addHistory(String historyItem) {
        System.out.println("Nu vanuit FileHistory .... ");
        history.add(historyItem);
    }

    @Override
    public List<String> getHistory() {
        System.out.println("En weer vanuit FileHistory .... ");
        return history;
    }

}
