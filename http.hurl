POST http://localhost:8080/invoices?user_id=ashley&amount=321
HTTP 200

POST http://localhost:8080/invoices
[QueryStringParams]
user_id: aldo
amount: 200
[Options]
HTTP 200

GET http://localhost:8080/invoices
HTTP 200
