Feature: Order management
    As a user
    I want to manage orders
    So that I can keep track of my orders

    Scenario: Create a new order
        Given I have access to the system
        When I create a new order
        Then the order should be saved in the system