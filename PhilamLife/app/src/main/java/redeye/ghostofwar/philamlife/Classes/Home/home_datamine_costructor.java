package redeye.ghostofwar.philamlife.Classes.Home;
public class home_datamine_costructor {

    private String brief;
    private String image;
    private String product;
    private String price;
    private String micro;

    public home_datamine_costructor(String brief, String image ,
                                    String product, String price, String micro) {
        this.brief = brief;
        this.image = image;
        this.product  = product;
        this.price = price;
        this.micro = micro;
    }

    public String brief() {
        return brief;
    }
    public String image() {
        return image;
    }
    public String product() {return product;}
    public String price() {return price;}
    public String micro() {return micro;}

}
