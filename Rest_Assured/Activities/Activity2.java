import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2
{
    final static String ROOT_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority=1)
    public void addNewUserFromFile() throws IOException
    {

        FileInputStream inputJSON = new FileInputStream(
                "src/test/java/resources/userinfo.json");
        String reqBody = new String(inputJSON.readAllBytes());
        Response response = given().contentType(ContentType.JSON).body(reqBody)
                        .when().post(ROOT_URI);
        inputJSON.close();
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("2456"));
    }

    @Test(priority=2)
    public void getUserInfo()
    {

        File outputJSON = new File(
                "src/test/java/resources/userGETResponse.json");

        Response response = given().contentType(ContentType.JSON)
                .pathParam("username", "testuserdemo")
                        .when().get(ROOT_URI + "/{username}");

        String resBody = response.getBody().asPrettyString();

        try
        {

            outputJSON.createNewFile();
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        response.then().body("id", equalTo(2456));
        response.then().body("username", equalTo("testuserdemo"));
        response.then().body("firstName", equalTo("Justin"));
        response.then().body("lastName", equalTo("Case"));
        response.then().body("email", equalTo("justincase@mail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9812763450"));
    }

    @Test(priority=3)
    public void deleteUser() throws IOException
    {
        Response response = given().contentType(ContentType.JSON)
                        .pathParam("username", "testuserdemo")
                        .when().delete(ROOT_URI + "/{username}");

        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("testuserdemo"));
    }
}