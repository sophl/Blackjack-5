package Models;

import org.hibernate.type.descriptor.java.SerializableTypeDescriptor;

/**
 * Created by Natha_000 on 3/5/2016.
 */
public class Player {
    private int money;
    private int roundMoney;
    private int roundMoneySplit;
    private int roundValue;
    private int roundValueSplit;
    private boolean hasAce;
    private boolean splitHasAce;

    public Player(){
        money = 100;
        roundValue = 0;
        roundMoney = 0;
        roundValueSplit = 0;
        roundMoneySplit = 0;
        hasAce = false;
        splitHasAce = false;
    }

    public void reset(){
        roundValue = 0;
        roundMoney = 0;
        roundValueSplit = 0;
        roundMoneySplit = 0;
        hasAce = false;
        splitHasAce = false;
    }

    public void lost(){
        roundMoney = 0;
    }

    public void lostSplit(){
        roundMoneySplit = 0;
    }

    public void won(){
        money += roundMoney*2;
    }

    public void wonSplit(){
        money += roundMoneySplit*2;
    }

    public int getMoney(){
        return money;
    }

    public int getRoundMoney(){
        return roundMoney;
    }

    public int getRoundMoneySplit(){
        return roundMoneySplit;
    }

    public int getRoundValue(){
        return roundValue;
    }

    public int getRoundValueSplit(){
        return roundValueSplit;
    }

    public void makeInitialBet(){
        roundMoney = 2;
        money -= 2;
    }

    public void doubleDown(){
        roundMoney *= 2;
    }

    public void split(Card SplitCard){
        money -= 2;
        roundMoneySplit = 2;
        roundValue -= SplitCard.getValue();
        hitSplit(SplitCard);
    }

    public void hit(Card newCard){
        if(newCard.getValue() == 1){
            hasAce = true;
            if(roundValue > 10)
                roundValue += 1;
            else
                roundValue += 11;
        }
        else if(newCard.getValue() < 10)
            roundValue += newCard.getValue();
        else
            roundValue += 10;
        if(hasAce && roundValue > 21) {
            roundValue -= 10;
            hasAce = false;
        }
    }

    public void hitSplit(Card newCard){
        if(newCard.getValue() == 1){
            splitHasAce = true;
            if(roundValueSplit > 10)
                roundValueSplit += 1;
            else
                roundValueSplit += 11;
        }
        else if(newCard.getValue() < 10)
            roundValueSplit += newCard.getValue();
        else
            roundValueSplit += 10;
        if(splitHasAce && roundValueSplit > 21) {
            roundValueSplit -= 10;
            splitHasAce = false;
        }
    }

}
