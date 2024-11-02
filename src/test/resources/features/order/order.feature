Feature: Order management
    As a user
    I want to manage orders
    So that I can keep track of my orders
    
    Scenario: Create a new order
        Given I have a new order
        When I create a new order
        Then I should see the order in the list
    
    Scenario: Retrieve all orders
        Given I have a list of orders
        When I retrieve all orders
        Then I should see all orders in the list
    
    Scenario: Retrieve order by id
        Given I have a list of orders
        When I retrieve order by id
        Then I should see the order in the list

    