package net.houwing.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("file")
public class FileHistory implements HistoryInterface {


    @Override
    public void addHistory(String historyItem) {
        //ToDo Schrijf weg naar een bestand...
    }

}
