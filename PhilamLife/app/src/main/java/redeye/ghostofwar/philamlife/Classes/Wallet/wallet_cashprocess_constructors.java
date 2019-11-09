package redeye.ghostofwar.philamlife.Classes.Wallet;
public class wallet_cashprocess_constructors {

    private String pw_processedmoney;
    private String pw_processtype;
    private String pw_processdate;
    private String pw_processfee;

    public wallet_cashprocess_constructors(String pw_processedmoney, String pw_processtype ,
                                           String pw_processdate, String pw_processfee) {
        this.pw_processedmoney = pw_processedmoney;
        this.pw_processtype = pw_processtype;
        this.pw_processdate  = pw_processdate;
        this.pw_processfee  = pw_processfee;
    }

    public String pw_processedmoney() {
        return pw_processedmoney;
    }
    public String pw_processtype() {
        return pw_processtype;
    }
    public String pw_processdate() {return pw_processdate;}
    public String pw_processfee(){ return  pw_processfee;}

}
