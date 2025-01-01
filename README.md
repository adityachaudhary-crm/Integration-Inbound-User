## Author
- Aditya Chaudhary
- adityachaudhary.crm@gmail.com
- 608-692-0386

## Integration: Java - Salesforce: WebSever/Authorization Code Grant Flow
Java Spring Boot, hosted on Heroku connects to Salesforce. User enters his salesforce-credentials to authorize the Java app. 

- Java App to User to Salesforce Server.
  
### Flow Summary:
- User accesses the Java App.
- Java App redirects the User to Saleforce Login page along with client Id. 
- Salesforce redirects the user to Java App's 'Callback URL' along with 'Access Code'
- Java App fetches Access-Token from Salesforce.
- Java App fetches /account ( resource) from Salesforce using Access Token.

### Heroku
- Host: https://integration-inbound-user-input-cafcf94ea457.herokuapp.com/
- Endpoint: Root (/)
- Salesforce-Org: https://adityachaudharycrm-dev-ed.develop.my.salesforce.com
- Brnach: heroku
