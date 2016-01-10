title PileRuleWaffegame2 transferCard

note over CardOwner: succesfull addition

CardOwner->Pile: addCard(card)
Pile->Pile: add(card)
Pile->PileRuleWaffeGame2: checkType(pileCards)
PileRuleWaffeGame2->PileRuleWaffeGame2: testSuit()
PileRuleWaffeGame2->PileRuleWaffeGame2: testStraight()
PileRuleWaffeGame2->PileRuleWaffeGame2: testGroup(2)
PileRuleWaffeGame2-->Pile: PAIRS
Pile-->CardOwner: true
CardOwner->CardOwner: remove(card)

note over CardOwner: unsuccesfull addition

CardOwner->Pile: addCard(card)
Pile->Pile: add(card)
Pile->PileRuleWaffeGame2: checkType(pileCards)
PileRuleWaffeGame2->PileRuleWaffeGame2: testSuit()
PileRuleWaffeGame2->PileRuleWaffeGame2: testStraight()
PileRuleWaffeGame2->PileRuleWaffeGame2: testGroup(2)
PileRuleWaffeGame2->PileRuleWaffeGame2: testGroup(3)
PileRuleWaffeGame2->PileRuleWaffeGame2: testGroup(4)
PileRuleWaffeGame2-->Pile: NULL
Pile->Pile: remove(card)
Pile-->CardOwner: false
