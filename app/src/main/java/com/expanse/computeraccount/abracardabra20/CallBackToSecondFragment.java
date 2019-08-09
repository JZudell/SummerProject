package com.expanse.computeraccount.abracardabra20;

import com.expanse.computeraccount.abracardabra20.pojo.Card;

public interface CallBackToSecondFragment {
    void updateLists(Card card, int sideIndex);
    void addCard(Card card,int sideIndex);
    void subtractCard(Card card, int sideIndex);
}
