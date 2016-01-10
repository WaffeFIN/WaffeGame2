title Card selection, playedLegalTurn()


note over GameLogicWaffeGame2
    Unsuccesful selection
end note

loop
    GameLogicWaffeGame2->GameLogicWaffeGame2: getPlay(player1)
    GameLogicWaffeGame2->Play: new CardCollection()
    GameLogicWaffeGame2->Player: selectCards(playable)
    Player->CardSelector: selectCards(Player, playable)

    alt a card is selected
        CardSelector-->Player: Card
        Player-->GameLogicWaffeGame2: Card
    else the selection is hit
        CardSelector-->Player: empty list
        Player-->GameLogicWaffeGame2: empty list
        note over GameLogicWaffeGame2
            break loop
        end note
    end
end

GameLogicWaffeGame2->Play: transferCards(Pile)
Play->Play: getCards()
Play->Pile: addCards()
Pile-->Play: false
Play-->GameLogicWaffeGame2: false


note over GameLogicWaffeGame2
    The turn is going to be passed
end note

GameLogicWaffeGame2->GameLogicWaffeGame2: getPlay(player1)
GameLogicWaffeGame2->Play: new CardCollection()
GameLogicWaffeGame2->Player: selectCards(playable)
Player->CardSelector: selectCards(Player, playable)
CardSelector-->Player: empty list
Player-->GameLogicWaffeGame2: empty list
GameLogicWaffeGame2->GameLogicWaffeGame2: turnPassed()

note left of Play
    Play is a
    CardCollection
end note

