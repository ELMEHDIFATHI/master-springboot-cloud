package com.hp.cards.services;


import com.hp.cards.Dto.CardsDto;

public interface ICardsService {


    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);


    boolean deleteCard(String mobileNumber);

}