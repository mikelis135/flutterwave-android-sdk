# Flutterwave Android SDK

An Android SDK consuming the Flutterwave Node API      (https://github.com/Flutterwave/nodeAPI)

### Flutterwave Services

- Currently Available:
  - Card :
    - Tokenize
    - Charge
    - Validate
- Todo:
    - Card:
        - Charge With Token
        - Preauth
        - Capture
        - Refund
        - Void
        - Enquiry
        - Validate Enquiry
        - Withdraw
        - Status
    - Account
    - ACH
    - BVN

### Implementation

#### Card.tokenize() - Calls card tokenize endpoint

```
Card.tokenize(String cardNumber, String cvv, String expiryMonth, String expiryYear, String validateOption, String authModel)
```

###### Sample successful response:

```javascript
{"responsecode":"00","responsemessage":"Completed Successfully","otptransactionidentifier":null,"transactionreference":null,"responsehtml":null,"responsetoken":"hWflntv6As0P1C96796","requiresValidation":false}
```
