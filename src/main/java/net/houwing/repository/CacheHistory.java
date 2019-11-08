package net.houwing.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
//@Primary
@Profile("cache")
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
