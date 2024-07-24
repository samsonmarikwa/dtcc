# **Retail Point Reward API**

The **Retail Point Reward API** will show points earned by a customer on a month by month basis depending on the
purchases made by the customer. The total of the points earned is also shown.

## Install API

1. Use the docker-compose.yml file in the retailer folder
2. Make sure you are in the retailer folder and in the command prompt, run the following command 'docker-compose up -d'
    This will download the image in [DockerHub](https://hub.docker.com/repositories/samsonmarikwa) and start the application.
    
    Alternatively,
    You can also pull the image by using 'docker pull samsonmarikwa/retailer:s1'
    When the image is pulled, you can use the command 'docker run -p 8080:8080 -d samsonmarikwa/retailer:s1'
    This will run the application
3. Once the application is running, you can upload the Postman collection into Postman to test the application. You can also use the browser to hit the endpoints [Get Points Earned](http://localhost:8080/retailer/pointsearned/100). You can replace 100 with 200, 300. 400 is [NoPointsEarned](http://localhost:8080/retailer/pointsearned/400, which means the customer made purchases but those do not qualify for points. Any other entries, like 500 will return an error message indicating Customer Not Found. You can use [CustomerNotExists](http://localhost:8080/retailer/pointsearned/500). The above tests are based on seed data. H2 database has been used.

## Executing Test Cases
1. You can clone the [GitHub repository](https://github.com/samsonmarikwa/dtcc.git)
2. Java 21 has been used to develop the application.
3. Open the application in IntelliJ Idea or any other IDE. Application was developed with Intellij Idea.
4. Navigate to 'src/test/java/com/dtcc/retailer/RetailerApplicationTests.java'
5. Execute the file, it will launch all the tests