
public class User {
    String username;
    String userpassword;
    String firstname;
    String middlename;
    String lastname;
    String dob;
    String gender;
    String address;
    String city;
    String pincode;
    String district;
    String state;
    String country;
    String useremail;
    String usermobile;

    public User(){}



    public User( String username, String userpassword, String firstname,String middlename,String lastname,String dob,
                 String gender,String address,String city, String pincode,String district,String state,String country
            ,String useremail,String usermobile) {
        this.username = username;
        this.userpassword = userpassword;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.country = country;
        this.useremail = useremail;
        this.usermobile = usermobile;
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }

    public String getuserpassword() {
        return userpassword;
    }
    public void setuserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getfirstname() { return firstname; }
    public void setfirstname(String firstname) { this.firstname = firstname; }

    public String getmiddlename() { return middlename; }
    public void setmiddlename(String middlename) { this.middlename = middlename; }

    public String getlastname() {return lastname; }
    public void setlastname(String lastname) {this.lastname = lastname;}

    public String getdob() {return dob;}
    public void setdob(String dob) {this.dob = dob;}

    public String getgender() {return gender;}
    public void setgender(String gender) {this.gender = gender;}

    public String getaddress() {return address;}
    public void setaddress(String address) {this.address = address;}

    public String getcity() {return city;}
    public void setcity(String city) {this.city = city;}

    public String getpincode() {return pincode;}
    public void setpincode(String pincode) {this.pincode = pincode;}

    public String getdistrict() {return district;}
    public void setdistrict(String district) {this.district = district;}

    public String getstate() {return state;}
    public void setstate(String state) {this.state = state;}

    public String getcountry() {return country;}
    public void setcountry(String country) {this.country = country;}

    public String getuseremail() {return useremail;}
    public void setuseremail(String useremail) {this.useremail = useremail;}

    public String getusermobile() {return usermobile;}
    public void setusermobile(String usermobile) {this.usermobile = usermobile;}



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + userpassword + '\'' +
                ", first name='" + firstname + '\'' +
                ", middle name='" + middlename + '\'' +
                ", last name='" + lastname + '\'' +
                ", date of birth='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", email id='" + useremail + '\'' +
                ", mobile no='" + usermobile + '\'' +
                '}';
    }
}
