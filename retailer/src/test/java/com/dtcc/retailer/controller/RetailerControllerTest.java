package com.dtcc.retailer.controller;

import com.dtcc.retailer.dto.MonthlyPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetailerControllerTest {
   
   private final String CONTEXT_PATH = "/retailer";
   
   @BeforeEach
   void setUp() {
      RestAssured.baseURI = "http://localhost";
      RestAssured.port = 8080;
   }
   
   @Test
   void testGetPointsEarned() {
      Response response = given().pathParam("customerId", 100L)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .get(CONTEXT_PATH + "/pointsearned/{customerId}")
            .then()
            .statusCode(200)
            .extract()
            .response();
      
      String customerName = response.jsonPath()
            .getString("customerName");
      
      List<MonthlyPoints> monthlyPoints = response.jsonPath()
            .getList("monthlyPoints", MonthlyPoints.class);
      
      assertEquals("Jane Doe", customerName);
      assertEquals(2, monthlyPoints.size());
      assertEquals(4, monthlyPoints.stream()
            .map(MonthlyPoints::getPoints)
            .reduce(0, Integer::sum));
   }
   
   @Test
   void testGetPointsEarned_CustomerNotFound() {
      Response response = given().pathParam("customerId", 700L)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .get(CONTEXT_PATH + "/pointsearned/{customerId}")
            .then()
            .statusCode(404)
            .extract()
            .response();
      
      String errorMessage = response.jsonPath()
            .getString("message");
      assertEquals("Customer Not found", errorMessage);
   }
}
