package sgc;

public class Visitor {
    private String name;
    private String creditCardNum;
    private int carNum;
    private int numID;

    public Visitor(String name, String creditCardNum, int numID){
        this.name = name;
        this.creditCardNum = creditCardNum;
        this.carNum = 2;
        this.numID = numID;
    }

    public String getName(){
        return name;
    }

    public String getCreditCardNum(){
        return creditCardNum;
    }

    public int getCarNum(){
        return carNum;
    }

    public int getNumID(){
        return numID;
    }
}
