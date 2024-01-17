package at.technikum.apps.mtcg.service;


import at.technikum.apps.mtcg.entity.Card;
import at.technikum.apps.mtcg.entity.User;
import at.technikum.apps.mtcg.repository.CardRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    private String userId;

    private List<Card> cards;

    @BeforeEach
    public void setUp(){
        userId = "test";
        cards = Arrays.asList(new Card(),new Card(),new Card());
    }
    @Test
    void testFindAll() {

    }

    @Test
    void find() {
    }

    @Test
    void save() {
    }

    @Test
    void findAllCardsByUser() {

        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> result = cardService.findAll();
        assertEquals(cards,result);
    }
}