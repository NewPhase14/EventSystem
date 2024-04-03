package sample.BLL.util;

import sample.BE.EventCoordinator;

import java.util.ArrayList;
import java.util.List;

public class StatisticsSearcher {


    public List<EventCoordinator> search(List<EventCoordinator> searchBase, String query){
        List<EventCoordinator> searchResult = new ArrayList<>();

        for (EventCoordinator ec : searchBase){
            if(compareToEventCoordinator(query, ec)){
                searchResult.add(ec);
            }
        }
        return searchResult;
    }

    private boolean compareToEventCoordinator(String query, EventCoordinator eventCoordinator){
        return eventCoordinator.getFirstName().toLowerCase().contains(query.toLowerCase());
    }
}
