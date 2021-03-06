package models;

import java.io.Serializable;

public class Agreement implements Serializable {
    private Player firstPlayer;
    private Player secondPlayer;
    private Offer firstOffer;
    private Offer secondOffer;
    private boolean isAccepted = false;
    private boolean isOffered = false;
    private String name;
    private boolean isEmpty = false;

    public Agreement(Offer firstOffer, Offer secondOffer, Player firstPlayer, Player secondPlayer, String name){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstOffer = firstOffer;
        this.secondOffer = secondOffer;
        this.name = name;
    }

    private void performOneTimeOffer(){
        if(firstOffer instanceof OneTimeOffer && secondOffer instanceof OneTimeOffer){
            firstOffer.performOffer(firstPlayer, secondPlayer);
            secondOffer.performOffer(firstPlayer, secondPlayer);
            firstOffer = null;
            secondOffer = null;
            isEmpty = true;
            return;
        }
        if(firstOffer instanceof OneTimeOffer) {
            firstOffer.performOffer(firstPlayer, secondPlayer);
            firstOffer = null;
        }
        if(secondOffer instanceof OneTimeOffer) {
            secondOffer.performOffer(firstPlayer, secondPlayer);
            secondOffer = null;
        }
    }

    // check offers if they are continuous
    public boolean performOffers(City city, Player player){
        if(isAccepted){
            // if firstOffer instanceof ContinuousOffer
            if(firstOffer != null){
                if(city.getId() == ((ContiuousOffer) firstOffer).getCity().getId()){
                    if(firstOffer instanceof TakePercentage) {
                        player.removeMoney(city.getRent());
                        firstOffer.performOffer(firstPlayer, secondPlayer);
                    }
                    else {
                        if(firstPlayer.getId() != player.getId()){
                            player.removeMoney(city.getRent());
                            secondPlayer.addMoney(city.getRent());
                        }
                        else {
                            firstOffer.performOffer(firstPlayer,secondPlayer);
                        }
                    }
                }
            }
            // if secondOffer instanceof ContinuousOffer
            if(secondOffer != null){
                if(city.getId() == ((ContiuousOffer) secondOffer).getCity().getId()){
                    if(secondOffer instanceof TakePercentage) {
                        player.removeMoney(city.getRent());
                        secondOffer.performOffer(firstPlayer, secondPlayer);
                    }
                    else {
                        if(firstPlayer.getId() != player.getId()){
                            player.removeMoney(city.getRent());
                            secondPlayer.addMoney(city.getRent());
                        }
                        else {
                            secondOffer.performOffer(firstPlayer,secondPlayer);
                        }
                    }
                }
            }
        }
        return isAccepted;
    }

    // check offers if city is involved
    public boolean checkOffers(City city) {
        if(isOffered) {
            if(firstOffer instanceof ContiuousOffer){
                return city.getId() == ((ContiuousOffer) firstOffer).getCity().getId();
            }
            else if(secondOffer instanceof ContiuousOffer){
                return city.getId() == ((ContiuousOffer) secondOffer).getCity().getId();
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    // getter method for isEmpty prop
    public boolean isEmpty(){
        return isEmpty;
    }

    // return if second player knows about the agreement
    public boolean isOffered(){
        return isOffered;
    }

    // accept agreement
    public void accept(){
        isAccepted = true;
        isOffered = true;
        performOneTimeOffer();
    }

    // decline agreement
    public void decline(){
        isOffered = true;
        isEmpty = true;
    }

    // get first player
    public Player getFirstPlayer(){
        return firstPlayer;
    }

    // get second player
    public Player getSecondPlayer() { return secondPlayer; }

    // get id
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Agreement between " + firstPlayer +
                " and " + secondPlayer +
                "\n First offer: " + firstOffer +
                "\n Second offer: " + secondOffer;
    }
}
