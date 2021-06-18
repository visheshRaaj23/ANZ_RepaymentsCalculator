Feature: Test the Home Loan Borrowing functionality by filling mandatory fields on ANZ Homeloan calculator
Scenario: Application should predict borrowing estimate amount he can obtain based on the inputs provided for various mandatory fields
Given User is on Home Loan calculator Page launched with "chrome" browser
When User enters the following details to get the borrowing estimate
|ApplicationType|Dependents|PropertyType|YourIncome|YourOtherIncome|LivingExpenses|CurrentHomeLoanExpenses|OtherLoanRepayments|Committments|TotalCreditCardLimits|
|Single|0|Home to live in|80000|10000|500|0|100|0|10000|
Then Borrowing estimate is displayed as "$479,000"

Scenario: Clicking on Start-over button clears the form
When The user clicks start-over button
Then Borrowing estimate form is cleared

Scenario: Entering some amount in living expenses and leaving all other fields as zero,
clicking work out how much i could borrow button returns the error message
When User enters only "1$" for living expenses
And Clicks on Work out how much I could borrow button
Then Error message is displayed like "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500."









