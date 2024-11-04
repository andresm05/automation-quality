Feature: Customer management
    As a user
    I want to manage customers
    So that I can keep track of my customers
    
    Scenario: Create a new customer
     Given I have access to the system
     When I create a new customer
     Then the customer should be saved in the system

    Scenario: Retrieve all customers
     Given I have access to the system
     When I retrieve all customers
     Then I should see the list of customers

    Scenario: Create a customer with existing email
     Given I have access to the system
     When I create a new customer with existing email
     Then I should see an error message