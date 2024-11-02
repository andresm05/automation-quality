Feature: Customer management
    As a user
    I want to manage customers
    So that I can keep track of my customers
    
    Scenario: Create a new customer
     Given I have a new customer
     When I create a new customer
     Then I should see the customer in the list

    Scenario: Retreve all customers
     Given I have a list of customers
     When I retrieve all customers
     Then I should see all customers in the list

    