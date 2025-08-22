package com.microservices.cards.service.Impl;

import com.microservices.cards.constants.CardConstant;
import com.microservices.cards.dto.CardDto;
import com.microservices.cards.entity.Card;
import com.microservices.cards.exception.CardAlreadyExistException;
import com.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.cards.mapper.CardMapper;
import com.microservices.cards.repository.CardRepository;
import com.microservices.cards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class ServiceCardImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public void create(String mobileNumber) {

        checkExistingCard(mobileNumber);
        Card newCard = this.createNewCard(mobileNumber);
        this.save(newCard);
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        return this.cardMapper.mapToCardDto(getCard(mobileNumber));
    }

    @Override
    public boolean updated(CardDto cardDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cardToDelete = this.getCard(mobileNumber);
        this.cardRepository.deleteById(cardToDelete.getCardId());
        return true;
    }

    private Card createNewCard(String mobileNumber) {
        long randomCardNumber= 100000000000L + new Random().nextInt(900000000);
        return this.cardMapper.mapToCard(mobileNumber, Long.toString(randomCardNumber), CardConstant.CREDIT_CARD,CardConstant.NEW_CARD_LIMIT,0, CardConstant.NEW_CARD_LIMIT);
    }

    private void checkExistingCard(String mobileNumber){
        this.cardRepository.fetchByMobileNumber(mobileNumber).orElseThrow(
                ()-> new CardAlreadyExistException("Card already registered with given mobileNumber "+mobileNumber)
        );
    }

    private Card getCard(String mobileNumber){
       return this.cardRepository.fetchByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Card","mobile number",mobileNumber)
        );
    }
    
    private void save(Card card){
        this.cardRepository.save(card);
    }

}
