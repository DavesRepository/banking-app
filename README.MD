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

