## The Small Banking App 

We are a small bank with a simple banking application and always had a tight budget for software development.
Because of this we only had one developer working on this application for quite some years and 
never really checked if the code was qualitative. (Just some testing by certain key business users) 
Our main developer recently quit and left us with some bugs we can not fix. 

Can you help us with the following tickets:

### Technical details
The Spring-boot application has 3 rest API's:
- GET /user/{username}
Should return the details of the user logged in

- GET /accounts/{username}
Should return the account details of a certain user

- GET /account/totalbalance/{username}
Should return the total balance of all the current accounts
and saving account. More or less how much money a client has at the bank.

We have provided an H2 in mem database that fills the data from the resource folder.

#### BA-1245 masking account numbers due to new regulation 

When we invoke the rest url GET http://localhost:8383/accounts/D.WEERNINK
we get back the following:

```json
[
  {
    "accountNumber": "58.44.23.14",
    "balance": 1600
  },
  {
    "accountNumber": "12.33.10.14",
    "balance": 1600
  }
]
```

Due to new regulations we need to mask the account number, so it doesn't show in our frontend,
and leaks the number. Instead of 58.44.23.14 we want to have **.**.**.14

Please implement the change and also check that the Integration test `shouldReturnMaskedAccounts()` is working.

#### BA-1255 getTotalBalance() is not working

The main developer quite just a few weeks ago; Mainly the issue was that we had to implement savings accounts
in our system. We always had current accounts which worked fine, but when the developer implemented the saving 
account logic it failed. The test seems to work according when we run it, but we haven't really looked into how
we actually do the testing. As far as we know it is an integration test in
AccountControllerIT.getTotalBalance.

The complaints that we get from IT support is that some users are seeing a wrong total balance.
Please investigate and solve the issue; If the tests are wrong fix those too.

#### BA-1246 

Look at the quality of the AccountController; Tell us what can be done better;
In the future we might want to not only have a AccountController that returns rest
but also returns another gRPC endpoint or a GraphQL resolver; How would you refactor 
this class so we don't have to duplicate our business logic for those new interfaces?

Please be elaborate in your answer of how we can improve the quality; I am sure we can 
use clean-code (SOLID?) principles and come with an extensive list of improvements for future 
tickets.  

#### BA-1270 Review: transfer money from a current account to a savings account

In his last weeks our developer built a new feature with a lot of help from an AI assistant. 
It is not in production yet. Customers should be able to move money from their own current account 
to their own savings account:

```
POST http://localhost:8383/transfer
Content-Type: application/json

{
  "username": "D.WEERNINK",
  "fromAccount": "58.44.23.14",
  "toAccount": "12.33.10.14",
  "amount": 100
}
```

The code is in `TransferController`, `TransferService` and `TransferServiceTest`. The test is green.

Please review this as if it were a pull request. **Don't rewrite it**, tell us:
- Would you approve it? If not, what has to change before it goes to production?
- Order your findings: what is the most dangerous for a bank, and what is just "nice to have"?
- Which tests are missing?

You may use AI for the review. If you do, we are also interested in what the AI missed or got wrong.

#### BA-1271 Review: customer support wants to search users by email

Customer support asked for a quick way to find a customer by (part of) an email address.
The same developer added:

```
GET http://localhost:8383/users/search?email=gmail
```

The code is in `UserSearchController`. Same question as BA-1270: would you approve this pull request,
and why (not)?

