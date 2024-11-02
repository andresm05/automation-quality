Feature: Customer management
    As a user
    I want to manage customers
    So that I can keep track of my customers
    
    Scenario: Create a new customer
     Given I have access to the system
     When I create a new customer
     Then the customer should be saved in the system