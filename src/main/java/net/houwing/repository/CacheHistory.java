package net.houwing.repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CacheHistory implements HistoryInterface {

    private List<String> history = new ArrayList<>();

    @Override
    public void addHistory(String historyItem){
        history.add(historyItem);
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}
