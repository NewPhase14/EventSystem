package sample.BLL.util;

import sample.BE.EventCoordinator;

import java.util.ArrayList;
import java.util.List;

public class StatisticsSearcher {


    public List<EventCoordinator> search(List<EventCoordinator> searchBase, String query){
        List<EventCoordinator> searchResult = new ArrayList<>();

        for (EventCoordinator ec : searchBase){
            if(compareToEventCoordinatorFN(query, ec) || compareToEventCoordinatorLN(query, ec)){
                searchResult.add(ec);
            }
        }
        return searchResult;
    }

    private boolean compareToEventCoordinatorFN(String query, EventCoordinator eventCoordinator){
        return eventCoordinator.getFirstName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToEventCoordinatorLN(String query, EventCoordinator eventCoordinator){
        return eventCoordinator.getLastName().toLowerCase().contains(query.toLowerCase());
    }
}
