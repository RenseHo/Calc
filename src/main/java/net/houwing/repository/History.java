package net.houwing.repository;

import java.util.Map;

public interface History {

    void addHistory(String historyItem, Double uitkomst);

    Map<String, Double> getHistory();

    Double getLastHistoryValue();
}
