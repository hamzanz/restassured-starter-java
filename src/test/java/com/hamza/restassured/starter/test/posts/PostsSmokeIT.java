package com.hamza.restassured.starter.test.posts;

import com.hamza.restassured.starter.models.post.PostModel;
import com.hamza.restassured.starter.test.BaseClass;
import com.hamza.restassured.starter.utils.SchemaReaderUtil;
import com.hamza.restassured.starter.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Epic("Posts API")
public class PostsSmokeIT extends BaseClass {

    private final String testDataFilePath = "posts/create_posts.json";
    private final int postId = 10;

    @DataProvider(name = "createPostData")
    public Object[][] createPostsDataProvider() throws FileNotFoundException {
        Object[][] data;
        PostModel[] createPosts = TestDataReader.getTestData(testDataFilePath, PostModel[].class);
//        PostModel[] createPosts = TestDataReader.getTestData(testDataFilePath);
        data = new Object[createPosts.length][1];

        int index = 0;
        for (Object eachPost : createPosts) {
            data[index++][0] = eachPost;
        }
        return data;
    }

    @Test(description = "POST /posts - Create posts details", dataProvider = "createPostData", priority = 1)
    @Description("Verify create new post api")
    public void createPostTest(PostModel post) {
        String createPostURI = "/posts";
        String endPoint = baseURL.concat(createPostURI);

        given()
                .spec(requestSpecification)
                .body(post)
                .when()
                .post(endPoint)
                .then()
                .statusCode(201);
    }

    @Test(description = "GET /posts/{id} - Get post by id", priority = 2)
    @Description("Verify get existing post by id")
    public void getPostById() {
        String getPostURI = "/posts/{postId}";
        String endpoint = baseURL.concat(getPostURI);

        PostModel response = given()
                .spec(requestSpecification)
                .when()
                .get(endpoint, postId)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(SchemaReaderUtil.readJsonSchemaRelativeToModels("/post/PostSchema.json")))
                .extract()
                .as(PostModel.class);

        Assert.assertEquals(postId, response.id);
        logger.debug(response);
    }

    @Test(description = "PUT /posts/{id} - Update a post by id", priority = 3)
    @Description("Verify update a post by post id")
    public void verifyUpdatePost(){

        String putURI = baseURL.concat("/posts/{postId}");

        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("title", "Post Title Edited");
        requestJsonMap.put("body", "Post Body edited");

        given()
                .spec(requestSpecification)
                .body(requestJsonMap)
                .when()
                .put(putURI, postId)
                .then()
                .statusCode(200);

    }

    @Test(description = "DELETE /posts/{id} - Remove a post by id", priority = 4)
    @Description("Verify delete a post")
    public void verifyRemovePost(){

        String deleteURI = baseURL.concat("/posts/{postId}");

        given()
                .spec(requestSpecification)
                .when()
                .delete(deleteURI, postId)
                .then()
                .statusCode(200);
    }


}
