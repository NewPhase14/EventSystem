package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.BE.EventCoordinator;
import sample.BLL.util.Searcher;

import java.util.ArrayList;
import java.util.List;

class SearcherTest {

    @Test
    void search() {
        //Arrange
        Searcher searcher = new Searcher();
        List<EventCoordinator> ek = new ArrayList<>();
        EventCoordinator e1 = new EventCoordinator(1,"Jeppe","Baden","JBaden", "qwer1234","JBaden@easv.dk");
        EventCoordinator e2 = new EventCoordinator(2,"Casper","Jensen","CJensen", "qwer1234","CJensen@easv.dk");
        EventCoordinator e3 = new EventCoordinator(3,"Thomas","Knudsen","TKnudsen", "qwer1234","TKnudsen@easv.dk");
        ek.add(e1);
        ek.add(e2);
        ek.add(e3);

        //Act
        List<EventCoordinator> actualValue = searcher.search(ek,"J");
        List<EventCoordinator> expectedValue = new ArrayList<>();
        expectedValue.add(e1);
        expectedValue.add(e2);

        //Assert
        Assertions.assertEquals(expectedValue, actualValue);
    }
}