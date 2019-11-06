package net.houwing.repository;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

public class History {

    private List<String> history = new ArrayList<>();
    private String name;



    public void addHistory(String historyItem){
        history.add(historyItem);
    }

    public void addHistoryToFile(String historyItem){
        //ToDo maak een bestand waarin de history wordt weggeschreven.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}
