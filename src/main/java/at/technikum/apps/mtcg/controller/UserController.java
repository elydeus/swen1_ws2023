package at.technikum.apps.mtcg.controller;

import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.User;
import at.technikum.apps.mtcg.service.CardService;
import at.technikum.apps.mtcg.service.UserService;
import at.technikum.server.http.ContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class UserController extends AbstractController {

    private final UserService userService;

    private final ObjectMapper objectMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public boolean supports(String route) {
        return route.equals("/users");
    }

    @Override
    public Response handle(Request request) {
        if (request.getRoute().equals("/users")) {
            switch (request.getMethod()) {
                case "GET":
                    return createWhenGET(request.getBody());
                case "POST":
                    return createWhenPOST(request.getBody());
            }

        }
            // THOUGHT: better 405
            return status(HttpStatus.BAD_REQUEST);

    }


        public Response createWhenGET(String route) {

          Response response = new Response();

          try{
              if(supports(route)){
                  String username = route.substring(route.lastIndexOf("/") + 1);
                  User user = userService.findByUsername(username);
                  if(user.isPresent()){

                  }
              }
          }

            return response;

            // return json(task);
        }

    public Response createWhenPOST(String route) {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try {
            user = objectMapper.readValue(request.getBody(), User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // task = toObject(request.getBody(), Task.class);

        user = userService.save(user);

        String userJson = null;
        try {
            userJson = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Response response = new Response();
        // THOUGHT: better status 201 Created
        response.setStatus(HttpStatus.OK);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody(userJson);

        return response;

        // return json(task);
    }
}