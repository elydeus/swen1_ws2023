package at.technikum.apps.mtcg.controller;

import at.technikum.server.http.ContentType;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import com.sun.nio.file.SensitivityWatchEventModifier;

import at.technikum.apps.mtcg.entity.Deck;
import at.technikum.apps.mtcg.entity.User;
import at.technikum.apps.mtcg.service.DeckService;
import at.technikum.apps.mtcg.service.PackageService;
import at.technikum.apps.mtcg.service.SessionService;
import at.technikum.apps.mtcg.service.StackService;
import at.technikum.server.http.HttpStatus;
import at.technikum.server.http.Request;
import at.technikum.server.http.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class DeckController extends AbstractController {

    private final SessionService sessionService;

    private final PackageService packageService;

    private final DeckService deckService;

    public DeckController() {
        this.sessionService = new SessionService();
        this.packageService = new PackageService();
        this.deckService = new DeckService();
    }

    @Override
    public boolean supports(String route) {
        return route.equals("/deck");
    }

    @Override
    public Response handle(Request request) {
        if (supports(request.getRoute())) {
            switch (request.getMethod()) {
                case "GET":
                    return readAll(request);
                case "PUT":
                    return configure(request);
            }
            return notAllowed(HttpStatus.NOT_ALLOWED);
        }
        return notAllowed(HttpStatus.NOT_ALLOWED);
    }

    public Response readAll(Request request) {

        String token = request.getHttpHeader();
        String username = extractUser(token);
        token = token.split(" ")[1];
        String user_id = deckService.getUserId(username);
        String deck_id = deckService.getDeck_Id(user_id);

        if (sessionService.isLoggedIn(token)) {
            if(deck_id == null){
                Deck deck = new Deck();
                deckService.save(deck, user_id);//TODO DeckService save erstellen und hier das Deck saven
            }

            List<String> cardsInDeck = deckService.findAll(deck_id); //TODO DeckService findAll erstellen

            ObjectMapper objectMapper = new ObjectMapper();

            String deckJson;
            try {
                deckJson = objectMapper.writeValueAsString(cardsInDeck);
            } catch (JsonProcessingException e) {
                return internalServerError(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return json(HttpStatus.OK, deckJson);
        }else{
            return notAllowed(HttpStatus.NOT_ALLOWED);
        }
    }

    public Response configure(Request request){

        if (request.getContentType().equals("application/json")) {

            String token = request.getHttpHeader();
            String username = extractUser(token);
            token = token.split(" ")[1];
            String user_id = deckService.getUserId(username);
            String deck_id = deckService.getDeck_Id(user_id);

            if(sessionService.isLoggedIn(token)){

                List<String> cardsToBeSaved;
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    cardsToBeSaved = objectMapper.readValue(request.getBody(), new TypeReference<List<String>>(){});
                } catch (JsonProcessingException e) {
                    return badRequest(HttpStatus.BAD_REQUEST);
                }

                if(cardsToBeSaved.size() != 4){
                    return json(HttpStatus.BAD_REQUEST, "Insufficient number of cards!");
                }

                if(deckService.checkIfCardsMatchUser(cardsToBeSaved, user_id)){
                    return json(HttpStatus.BAD_REQUEST, "Cards dont belong to user");
                }

                List<String> cards = deckService.findAll(deck_id);
                if(cards.isEmpty()){
                    deckService.saveCardsInDeck(cardsToBeSaved, deck_id);
                    return ok(HttpStatus.OK);
                }
                deckService.updateCardsInDeck(cardsToBeSaved, deck_id);
            }else{
                return notAllowed(HttpStatus.NOT_ALLOWED);
            }
        }
        return ok(HttpStatus.OK);
    }
}
