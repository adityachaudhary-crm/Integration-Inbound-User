## Author
- Aditya Chaudhary
- adityachaudhary.crm@gmail.com
- 608-692-0386

## Integration: Java - Salesforce: WebSever/Authorization Code Grant Flow
Java Spring Boot, hosted on Heroku connects to Salesforce. User enters his salesforce-credentials to authorize the Java app. 

- Java App to User to Salesforce Server.
  
### Flow Summary:
- User accesses the Java App's endpoint '/'.
- Redirected to redirect:/oauth2/authorization/salesforce
- Above endpoint acts as a function to invoke Salesforce '/services/oauth2/authorize' with clientId, responsetype=code and redirect URI=/login/oauth2/code/salesforce
- User logs in salesforce. 
- Salesforce redirects the user to Java App's 'Callback URL'(2 steps up) along with 'Access Code'
- This endpoint is resposible to fetches Access-Token from Salesforce.
- Java App fetches /account ( resource) from Salesforce using Access Token.

### Heroku
- Host: https://integration-inbound-user-input-cafcf94ea457.herokuapp.com/
- Endpoint: Root (/)
- Salesforce-Org: https://adityachaudharycrm-dev-ed.develop.my.salesforce.com
- Branch: heroku

### Salesforce
- External Client App - Setup in Salesforce
- Redirect URL is same as Callback URL.
