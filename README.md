# BHC technical interview


### Author : 
Misheck Mudamburi
### Email :
mmmudamburi@gmail.com
### Cell :
0778017733

>To access application  from  your favourite browser/REST client use the link below
(http://localhost:8084/swagger-ui/#/).
  
## Steps to run the application
***

1. Create a db

>Create a  database  of your   favourite which you will use .In this scenario I used mysql e.g database is absa.
>Add the database credentials in application.properties file found in src/main/resources/application.properties

spring.datasource.username=your_sername
spring.datasource.password=your_password
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/bhc
spring.jpa.hibernate.ddl-auto=create

This  will create a db for you, check with your favourite workbench

2. Build and run app

>Build your maven  project  using -- mvn clean install 
> 
>  Change directory to project folder  && java -jar target/AbsaBanking-0.0.1-SNAPSHOT.jar
> 
> Access  the endpoints  as  stated above .I have provided some sample  requests  to test in src/test/resources/scratch.txt

3. Test Data for Question 1
Copy and paste into swagger
>>>>>
   {
   "baleList": [
   {
   "barcode": "110000011",
   "grade": "TMOS",
   "grower": {
   "growerNumber": "123456"
   },
   "mass": 115,
   "price": 2.50
   },
   {
   "barcode": "110000012",
   "grade": "TLOS",
   "grower": {
   "growerNumber": "123456"
   },
   "mass": 85,
   "price": 4.5
   },
   {
   "barcode": "110000013",
   "grade": "TLOS",
   "grower": {
   "growerNumber": "123456"
   },
   "mass": 95,
   "price": 5.5
   }
   ],
   "growerNumber": "123456"
   }
>>>>>>>>>>>>>
> 4. Test Data for question 5
> {
     "bales": [
     {
     "barcode": "110000010",
     "grade": "TLOS",
     "grower": {
     "growerNumber": "12345"
     },
     "mass": 85,
     "price": 2.50
     }, {
     "barcode": "110000013",
     "grade": "TLOS",
     "grower": {
     "growerNumber": "123456"
     },
     "mass": 95,
     "price": 5.5
     }
     ],
     "grades": [
     "TLOS"
     ]
     }
