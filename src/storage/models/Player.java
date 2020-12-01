package storage.models;

public class Player {
    private boolean isInQuarantine;
    // TODO - private City[] cities;
    private String name;
    private int id;
    private String color;
    private String pawn;
    private double money;
    private boolean isBankrupted;
    private int location;
    private boolean isInfected;

    public Player(String name, String color, String pawn, int id)
    {
        isInQuarantine = false;
        // TODO - add cities
        this.name = name;
        this.id = id;
        this.color = color;
        this.pawn = pawn;
        money = 0;
        isBankrupted = false;
        location = 0;
        isInfected = false;
    }

    public void bankrupt() { isBankrupted = true; }

    public void quarantine(boolean bool)
    {
        isInQuarantine = bool;
    }

    public void addMoney(double money)
    {
        this.money = this.money + money;
    }

    public void removeMoney(double money)
    {
        this.money = this.money - money;
    }

    public void infect()
    {
        isInfected = true;
    }

    //  Getters - Setters
    public void setLocation(int regionID)
    {
        location = regionID;
    }

    public int getLocation()
    {
        return location;
    }

    public void setId(int id) { this.id = id; }

    public int getId()
    {
        return id;
    }

    public void setColor(String color) { this.color = color; }

    public String getColor()
    {
        return color;
    }

    public void setPawn(String pawn) { this.pawn = pawn; }

    public String getPawn() { return pawn; }

    public double getMoney()
    {
        return money;
    }

    public boolean isBankrupted()
    {
        return isBankrupted;
    }

    public boolean isInfected()
    {
        return isInfected;
    }

    public void setName(String name) { this.name = name; }

    public String getName()
    {
        return name;
    }

    public boolean isInQuarantine()
    {
        return isInQuarantine;
    }
}