package com.microservices.cards.mapper;

import com.microservices.cards.dto.CardDto;
import com.microservices.cards.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    Card mapToCard(CardDto cardDto);

    @Mapping(target = "mobileNumber", source ="mobileNumber" )
    @Mapping(target = "cardNumber", source ="cardNumber" )
    @Mapping(target = "cardType", source ="cardType" )
    @Mapping(target = "totalLimit", source ="totalLimit" )
    @Mapping(target = "amountUsed", source ="amountUsed" )
    @Mapping(target = "availableAmount", source ="availableAmount" )
    Card mapToCard(String mobileNumber, String cardNumber, String cardType, int totalLimit, int amountUsed, int availableAmount);
    CardDto mapToCardDto(Card card);
}
