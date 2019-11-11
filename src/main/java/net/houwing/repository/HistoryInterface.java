package net.houwing.repository;

import java.util.List;

public interface HistoryInterface {

    void addHistory(String historyItem);

    List<String> getHistory();
}
