/**
 * Created by munde on 23/03/2018.
 */
public class Track {
    String statusid;
    String firid;
    String status;
    String rpfid;
    String counting;

    public Track(){}

    public Track ( String statusid,String firid,String status,String rpfid,String counting) {
        this.statusid = statusid;
        this.firid = firid;
        this.status = status;
        this.rpfid = rpfid;
        this.counting = counting;
    }
    public String getStatusid() {return statusid;}
    public void setStatusid(String statusid) {this.statusid = statusid;}

    public String getFirid() { return firid;}
    public void setFirid(String firid) { this.firid = firid;}

    public String getStatus() { return  status;}
    public void setStatus(String status) { this.status = status;}

    public String getRpfid() { return  rpfid;}
    public void setRpfid(String rpfid) { this.rpfid = rpfid;}

    public String getCounting() { return counting;}
    public void setCounting(String counting ) { this.counting = counting;}
}
