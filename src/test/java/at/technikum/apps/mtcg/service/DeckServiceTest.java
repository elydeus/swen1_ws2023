package at.technikum.apps.mtcg.service;

import at.technikum.apps.mtcg.entity.Deck;
import at.technikum.apps.mtcg.repository.DeckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeckServiceTest {

    @InjectMocks
    private DeckService deckService;

    @Mock
    private DeckRepository deckRepository;

    private String playerId;
    private List<String> cards;


    @BeforeEach
    void setUp() {
        deckRepository = mock(DeckRepository.class);
        deckService = new DeckService(deckRepository);
    }

    @Test
    void save_deckAndUserId_deckIsSaved() {
        Deck deck = new Deck("1", "Card2", "Deck1");
        String userId = "user123";

        deckService.save(deck, userId);
        verify(deckRepository).save(deck, userId);
    }

    @Test
    public void testCheckIfCardsMatchUser() {
        when(deckRepository.checkIfCardsMatchUser(cards, playerId)).thenReturn(true);

        boolean result = deckService.checkIfCardsMatchUser(cards, playerId);

        assertTrue(result);
    }

    @Test
    public void testCardIsAvailableForTrade() {
        String cardId = "card1";

        when(deckRepository.isCardAvailableForTrade(cardId)).thenReturn(true);

        boolean result = deckService.isCardAvailableForTrade(cardId);

        assertTrue(result);
    }

}