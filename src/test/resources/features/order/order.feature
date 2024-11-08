Feature: Order management
    As a user
    I want to manage orders
    So that I can keep track of my orders

    Scenario: Create a new order
        Given I have access to the system
        When I create a new order
        Then the order should be saved in the system

    Scenario: Retrieve all orders
        Given I have access to the system
        When I retrieve all orders
        Then I should see the list of orders
        
    Scenario: Retrieve oder by id
        Given I have access to the system
        When I retrieve order by id
        Then I should see the order with the given id

    Scenario: Retrieve order with invalid id
        Given I have access to the system
        When I retrieve order with invalid id
        Then I should see an error message
    