Simple Receiver app (port 8081) using Spring REST Template to exchange data from Simple Sender App running on port 8080  

This App exposes two API's end points created with Spring Boot 3.5.4. Used for

1. Health check                                 /healthcheck-receiver-app
2. Fetches a list of pre-defined customers 
   from the sending app (simple-sender)         /customer/get

Receives a pre-defined customer list sent from Simple Sender Spring Boot 3.5.4 app for testing (sending data for consumption on API endpoint)
