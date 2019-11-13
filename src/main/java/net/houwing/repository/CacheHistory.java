package net.houwing.repository;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
@Profile("cache")
public class CacheHistory implements History {

    private OrderedMap<String, Double> history = new LinkedMap<>();

    @Override
    public void addHistory(String historyItem, Double uitKomst){
        System.out.println("in de addHistory van cache...");
        history.put(historyItem, uitKomst);
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
