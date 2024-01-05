package at.technikum.apps.mtcg.controller;

import at.technikum.apps.mtcg.service.SessionService;
import at.technikum.server.http.ContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;

public abstract class AbstractController {

    public abstract boolean supports(String route);

    public abstract Response handle(Request request);

    private SessionService sessionService = new SessionService();

    protected Response status(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"error\": \""+ httpStatus.getMessage() + "\"}");

        return response;
    }

    protected Response ok(HttpStatus httpStatus){
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"ok\": \""+ HttpStatus.OK + "\"}");
        return response;
    }

    protected Response notAllowed(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Not Allowed\": \""+ HttpStatus.NOT_ALLOWED + "\"}");

        return response;
    }

    protected Response created(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Created\": \""+ HttpStatus.CREATED + "\"}");

        return response;
    }

    protected Response badRequest(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Bad Request\": \""+ HttpStatus.BAD_REQUEST + "\"}");

        return response;
    }

    protected Response internalServerError(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"internal server error\": \"" + HttpStatus.INTERNAL_SERVER_ERROR + "\"}");

        return response;
    }

    protected Response accepted(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Accepted\": \""+ HttpStatus.ACCEPTED + "\"}");

        return response;
    }

    protected Response unauthorized(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Unauthorized\": \""+ HttpStatus.UNAUTHORIZED + "\"}");

        return response;
    }

    protected Response notFound(HttpStatus httpStatus) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody("{ \"Not Found\": \""+ HttpStatus.NOT_FOUND + "\"}");

        return response;
    }

    protected Response json(HttpStatus httpStatus, String jsonResponse) {
        Response response = new Response();
        response.setStatus(httpStatus);
        response.setContentType(ContentType.APPLICATION_JSON);
        response.setBody(jsonResponse);

        return response;
    }
    protected String extractUser(String header){
            String extractedToken = extractToken(header);
            if (extractedToken == null){
                return null;
            }
            String[] subSections = extractedToken.split("-");
            if (subSections.length == 2){
                return subSections[0];
            }

        return null;
    }
    protected String extractToken(String header){
        if (header == null){
            return null;
        }
        String[] sections = header.split(" ");
        if (sections.length == 2 && sections[0].equals("Bearer")){
            return sections[1];
        }
        return null;
    }

    protected boolean isLoggedIn(Request request){
        return sessionService.isLoggedIn(extractToken(request.getHttpHeader()));
    }
    protected boolean isLoggedInAsAdmin(Request request){
        return sessionService.isLoggedInAsAdmin(extractToken(request.getHttpHeader()));
    }
}
