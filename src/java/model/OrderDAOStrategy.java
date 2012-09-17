package model;

import java.util.List;

/**
 * A strategy for DAO objects for the menu ordering project.
 * 
 * @author Cody Auter
 */
public interface OrderDAOStrategy {

    List<MenuItem> getCurrentMenuChoices() throws RuntimeException;

    void saveOrder(List<MenuItem> orderList) throws RuntimeException;

}
