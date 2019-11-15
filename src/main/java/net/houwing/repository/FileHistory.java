package net.houwing.repository;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
@Profile("file")
public class FileHistory implements History {

    private OrderedMap<String, Double> history = new LinkedMap<>();

    @Override
    public void addHistory(String historyItem, Double uitkomst) {
        System.out.println("in de addHistory van file ...");
        history.put(historyItem, uitkomst);
    }

    @Override
    public Map<String, Double> getHistory() {
        return history;
    }

    @Override
    public Double getLastHistoryValue() {
        return history.get(history.lastKey());
    }
}
