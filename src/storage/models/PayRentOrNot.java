package storage.models;

public class PayRentOrNot extends ContiuousOffer {
    private City city;

    public PayRentOrNot(City city){
        this.city = city;
    }

    @Override
    public City getCity() {
        return city;
    }

    @Override
    public void performOffer(Player firstPlayer, Player secondPlayer) {
        //first player does not pay money for the city of second player
        firstPlayer.removeMoney(0);
        //second player does not take any money from the first player for the sepecified city
        secondPlayer.addMoney(0);
    }
}
