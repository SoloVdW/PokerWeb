/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.google.inject.Inject;
import filters.SecureFilter;
import models.Card;
import models.Hand;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import services.IPokerService;
import services.PokerService;

import java.util.ArrayList;
import java.util.List;

@Singleton
@FilterWith(SecureFilter.class)
public class ApplicationController {

    @Inject
    private IPokerService pokerService;

    public Result index() {
        Result result = Results.html();

        Hand hand= pokerService.dealHand();
        List<Card> cards= hand.getCards();

        result.render("hand", hand.toString())
                .render("one", cards.get(0).getRank() +"_" + cards.get(0).getSuit() + ".png")
                .render("two", cards.get(1).getRank() +"_" + cards.get(1).getSuit() + ".png")
                .render("three", cards.get(2).getRank() +"_" + cards.get(2).getSuit() + ".png")
                .render("four", cards.get(3).getRank() +"_" + cards.get(3).getSuit() + ".png")
                .render("five", cards.get(4).getRank() +"_" + cards.get(4).getSuit() + ".png").render("handType", pokerService.evaluateHand(hand).toString());
        return result;

    }

    public void setPokerService(IPokerService pokerService) {
        this.pokerService = pokerService;
    }
}
