/**
 * Created by Disha on 09-03-2018.
 */

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.management.Query;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DBUtil2 {

    private static Connection getDatabase() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/railrakshak", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static ArrayList<Fir> bar() {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
//        String count = null;
        ArrayList<Fir> f1 = new ArrayList<Fir>();
        try {
            ps = c.prepareStatement("select firtypef,count(firtypef) from fir group by firtypef");
            rs = ps.executeQuery();

            while (rs.next()) {
            Fir f = new Fir();
            f.setFirtypef(rs.getString(1));
            f.setFircount(rs.getString(2));
            f1.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return f1;}



        public static ArrayList<Track> pie() {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
//        String count = null;
        ArrayList<Track> t1 = new ArrayList<Track>();

        try {
            ps = c.prepareStatement("SELECT STATUS,COUNT(status) FROM fir GROUP BY status");
            rs = ps.executeQuery();

            while(rs.next()) {
                Track t = new Track();
                t.setStatus(rs.getString(1));
                t.counting = rs.getString(2);
                t1.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return t1;}

    public static String updation(Fir f) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        try {
            ps = c.prepareStatement("UPDATE fir SET status = 'underprocess' WHERE firstnamein=? AND username=?");
            ps.setString(1,f.getFirstnamein());
            ps.setString(2,f.getUsername());

            count = ps.executeUpdate();
            System.out.println(count);
            if( count > 0) {
                System.out.println("succesfully updated");
                String link = "http://localhost:63342/a1/AdminLTE-master/dashboard.html";
                return link;
            }
            else{
                System.out.println("unsuccesful");

            }

//        return link;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;}

    public static String putFir(Fir f) {
        Connection c = getDatabase();
        PreparedStatement ps=null;
        try {
            ps = c.prepareStatement("insert into fir(username,firstnamein,middlenamein,lastnamein,fathersnamein,dobin,occupationin,genderin,emailin,contactnoin,addressin,nationalityin,cityin,statein,districtin,aadhaarnoin,reasonsdelayin,firtypef,firdatef,firtimef,stationf,datefromf,datetof,timefromf,timetof,cityf,statef,trainnof,trainnamef,sourcef,destinationf,detailsf,typeit,valueworthit,descriptionit,suspectnames,relativesnames,contactnos,genders,areas,citys,districts,states) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, f.getUsername());
            ps.setString(2, f.getFirstnamein());
            ps.setString(3, f.getMiddlenamein());
            ps.setString(4, f.getLastnamein());
            ps.setString(5, f.getFathersnamein());
            ps.setString(6, f.getDobin());
            ps.setString(7, f.getOccupationin());
            ps.setString(8, f.getGenderin());
            ps.setString(9, f.getEmailin());
            ps.setString(10, f.getContactnoin());
            ps.setString(11, f.getAddressin());
            ps.setString(12, f.getNationalityin());
            ps.setString(13, f.getCityin());
            ps.setString(14, f.getStatein());
            ps.setString(15, f.getDistrictin());
            ps.setString(16, f.getAadhaarnoin());
            ps.setString(17, f.getReasonsdelayin());
            ps.setString(18, f.getFirtypef());
            ps.setString(19, f.getFirdatef());
            ps.setString(20, f.getFirtimef());
            ps.setString(21, f.getStationf());
            ps.setString(22, f.getDatefromf());
            ps.setString(23, f.getDatetof());
            ps.setString(24, f.getTimefromf());
            ps.setString(25, f.getTimetof());
            ps.setString(26, f.getCityf());
            ps.setString(27, f.getStatef());
            ps.setString(28, f.getTrainnof());
            ps.setString(29, f.getTrainnamef());
            ps.setString(30, f.getSourcef());
            ps.setString(31, f.getDestinationf());
            ps.setString(32, f.getDetailsf());
            ps.setString(33, f.getTypeit());
            ps.setString(34, f.getValueworthit());
            ps.setString(35, f.getDescriptionit());
            ps.setString(36, f.getSuspectnames());
            ps.setString(37, f.getRelativesnames());
            ps.setString(38, f.getContactnos());
            ps.setString(39, f.getGenders());
            ps.setString(40, f.getAreas());
            ps.setString(41, f.getCitys());
            ps.setString(42, f.getDistricts());
            ps.setString(43, f.getStates());
            int i = 0;

            i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("registered your fir " + f);
                String k = "http://localhost:63342/railrakshak/src/html/Successful_registration_page.html";
                return k;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   return null; }

    public static String updatestatus(Fir f) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            ps = c.prepareStatement("UPDATE fir SET status =? WHERE firid=?");
            ps.setString(1,f.getStatus());
            ps.setString(2,f.getFirid());

            count = ps.executeUpdate();
            System.out.println(count);
            if( count > 0) {
                System.out.println("succesfully updated");
                String link = "http://localhost:63342/SIH12/AdminLTE-master/dashboard2new.html";
                return link;
            }
            else{
                System.out.println("unsuccesful");

            }

//        return link;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }
    static public ArrayList<Track> track(String id) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs= null;
        ArrayList<Track> t1 = new ArrayList<Track>();


try {



            ps = c.prepareStatement("select status,firid from fir where firid=?");
            ps.setString(1,id);
            rs  = ps.executeQuery();
            while(rs.next()) {
                Track t = new Track();
                t.setStatus(rs.getString(1));
                t.setFirid(rs.getString(2));
                t1.add(t);
            }

    } catch (SQLException e) {
    e.printStackTrace();
}
return t1;
    }

    static public void retrievepdf() {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        final int BUFFER_SIZE = 4096;
        String s = "C:/Users/munde/gunj2.pdf";
        try {
            ps = c.prepareStatement("select testing from pdf where pdfid=10");
            rs = ps.executeQuery();
            System.out.println("hiiii");
            if(rs.next()) {
                Blob blob = rs.getBlob("testing");
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(s);
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    System.out.println(bytesRead);
                    outputStream.write(buffer, 0, bytesRead);
                    System.out.println("file saved");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static public void uploadpdf() {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        File pdf = new File("C:/Users/munde/Downloads/lab_practical_index.pdf");
        byte[] pdfd = new byte[(int)pdf.length()];
        try {
            ps = c.prepareStatement("insert into pdf(testing) values(?)");
            ps.setBytes(1,pdfd);

            int i = 0;
            i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("pdf entered");
            }
            else {
                System.out.println("unsuccesful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getaadhar(String id) {
    Connection c = getDatabase();
    PreparedStatement ps = null;
    ResultSet rs = null;
    ArrayList<User> u2 = new ArrayList<User>();
    User u1 = new User();

        try {
            ps = c.prepareStatement("select firstname,middlename,lastname,dob,gender,address,city,pincode,district,state,country,useremail,usermobile from aadhaar where aadharno=?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            System.out.println(rs);
            rs.next();
            {
                u1.setfirstname(rs.getString(1));
                u1.setmiddlename(rs.getString(2));
                u1.setlastname(rs.getString(3));
                u1.setdob(rs.getString(4));
                u1.setgender(rs.getString(5));
                u1.setaddress(rs.getString(6));
                u1.setcity(rs.getString(7));
                u1.setpincode(rs.getString(8));
                u1.setdistrict(rs.getString(9));
                u1.setstate(rs.getString(10));
                u1.setcountry(rs.getString(11));
                u1.setuseremail(rs.getString(12));
                u1.setusermobile(rs.getString(13));
                u2.add(u1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return u2;
    }
    public static ArrayList<Fir> getFirDetails(String id) {
//        int i = Integer.parseInt(id);
        Connection c = getDatabase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Fir> f2 = new ArrayList<Fir>();
        Fir f1 = new Fir();
        try {

            ps = c.prepareStatement("select username,firstnamein,middlenamein,lastnamein,fathersnamein,dobin,occupationin,genderin,emailin,contactnoin,addressin,nationalityin,cityin,statein,districtin,aadhaarnoin,reasonsdelayin,firtypef,firdatef,firtimef,stationf,datefromf,datetof,timefromf,timetof,cityf,statef,trainnof,trainnamef,sourcef,destinationf,detailsf,typeit,valueworthit,descriptionit,suspectnames,relativesnames,contactnos,genders,areas,citys,districts,states from fir where firid=?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            System.out.println(rs);
            rs.next();
            {
                f1.setUsername(rs.getString(1));
                f1.setFirstnamein(rs.getString(2));
                f1.setMiddlenamein(rs.getString(3));
                f1.setLastnamein(rs.getString(4));
                f1.setFathersnamein(rs.getString(5));
                f1.setDobin(rs.getDate(6) + "");
                f1.setOccupationin(rs.getString(7));
                f1.setGenderin(rs.getString(8));
                f1.setEmailin(rs.getString(9));
                f1.setContactnoin(rs.getInt(10) + "");
                f1.setAddressin(rs.getString(11));
                f1.setNationalityin(rs.getString(12));
                f1.setCityin(rs.getString(13));
                f1.setStatein(rs.getString(14));
                f1.setDistrictin(rs.getString(15));
                f1.setAadhaarnoin(rs.getString(16));
                f1.setReasonsdelayin(rs.getString(17));
                f1.setFirtypef(rs.getString(18) + "");
                f1.setFirdatef(rs.getDate(19) + "");
                f1.setFirtimef(rs.getTime(20) + "");
                f1.setStationf(rs.getString(21));
                f1.setDatefromf(rs.getDate(22) + "");
                f1.setDatetof(rs.getDate(23) + "");
                f1.setTimefromf(rs.getTime(24) + "");
                f1.setTimetof(rs.getTime(25) + "");
                f1.setCityf(rs.getString(26));
                f1.setStatef(rs.getString(27));
                f1.setTrainnof(rs.getInt(28) + "");
                f1.setTrainnamef(rs.getString(29));
                f1.setSourcef(rs.getString(30));
                f1.setDestinationf(rs.getString(31));
                f1.setDetailsf(rs.getString(32));
                f1.setTypeit(rs.getString(33));
                f1.setValueworthit(rs.getString(34));
                f1.setDescriptionit(rs.getString(35));
                f1.setSuspectnames(rs.getString(36));
                f1.setRelativesnames(rs.getString(37));
                f1.setContactnos(rs.getString(38));
                f1.setGenders(rs.getString(39));
                f1.setAreas(rs.getString(40));
                f1.setCitys(rs.getString(41));
                f1.setDistricts(rs.getString(42));
                f1.setStates(rs.getString(43));
//                f1.setRpfid(rs.getString(44)+"");
//                f1.setFirid(rs.getString(45)+"");

                f2.add(f1);
                f2.removeAll(Collections.singleton(null));
                System.out.println(f2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs.close();
            ps.close();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f2;
//        ps.setInt(1);
//        ps.executeQuery();
    }

    public static ArrayList<Fir> retrieveFir2(String id) {
        Connection c = getDatabase();
        ArrayList<Fir> f2 = new ArrayList<Fir>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = c.prepareStatement("SELECT firid,username,firstnamein,firtypef,datefromf,status  FROM fir where stationf=? AND status NOT IN (select status from fir WHERE status='submitted')");
            ps.setString(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Fir f1 = new Fir();
                System.out.println(rs);
                f1.setFirid(rs.getInt(1) + "");
                f1.setUsername(rs.getString(2) + "");
                f1.setFirstnamein(rs.getString(3));
                f1.setFirtypef(rs.getString(4)+"");
                f1.setDatefromf(rs.getString(5)+"");
                f1.setStatus(rs.getString(6));
                f2.add(f1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return f2;}

    public static ArrayList<Fir> retrieveFir(String id) {
        Connection c = getDatabase();
        ArrayList<Fir> f2 = new ArrayList<Fir>();
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            ps = c.prepareStatement("select firid,username,firstnamein,firtypef,datefromf from fir where stationf=? and status=?");
            ps.setString(1,id);
            ps.setString(2,"submitted");
            rs = ps.executeQuery();


            while (rs.next()) {
                Fir f1 = new Fir();
                System.out.println(rs);
                f1.setFirid(rs.getInt(1) + "");
                f1.setUsername(rs.getString(2) + "");
                f1.setFirstnamein(rs.getString(3));
                f1.setFirtypef(rs.getString(4)+"");
                f1.setDatefromf(rs.getString(5)+"");
                f2.add(f1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {

            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f2;
    }

    public static void putUser(User u) {
        Connection c = getDatabase();
        PreparedStatement ps=null;

        try {
             ps = c.prepareStatement(
                    "insert into user(username,userpassword,firstname,middlename,lastname,dob,gender,address,city,pincode,district,state,country,useremail,usermobile) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, u.getusername());
            ps.setString(2, u.getuserpassword());
            ps.setString(3, u.getfirstname());
            ps.setString(4, u.getmiddlename());
            ps.setString(5, u.getlastname());
            ps.setString(6, u.getdob());
            ps.setString(7, u.getgender());
            ps.setString(8, u.getaddress());
            ps.setString(9, u.getcity());
            ps.setString(10, u.getpincode());
            ps.setString(11, u.getdistrict());
            ps.setString(12, u.getstate());
            ps.setString(13, u.getcountry());
            ps.setString(14, u.getuseremail());
            ps.setString(15, u.getusermobile());
            // ps.setString(15,null);

            int i = 0;

            i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("registered " + u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            c.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User u) {


        Connection c = getDatabase();
        PreparedStatement ps= null;

        try {
            ps = c.prepareStatement("update usecase set username2=? where username=?");
            ps.setString(1, u.getusername());
            ps.setString(2, u.getuserpassword());
            int i = 0;
            i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("updated");
            }
            System.out.println(i);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void accepted(String firid) {

        Connection c = getDatabase();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update fir set status=? where firid=?");
            ps.setString(1,"FIR Verified");
            ps.setString(2,firid);
            int i = 0;
            i = ps.executeUpdate();
            if(i > 0)
            {
                System.out.println("successfully updated status to accepted");
            }
            else
            {
                System.out.println("unsuccesful to update status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void deleteUser(String username) {
        int i = 0;
        Connection c = getDatabase();
        PreparedStatement ps=null;
        try {


            ps = c.prepareStatement("delete from user where username=?");
            ps.setString(1, username);
            i = 0;
            i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("deleted");
            }
            else{
                System.out.println("unsuccesfull to delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static String validationfir1(String s,String t, String u) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        try {
            int count = 0;
            ps = c.prepareStatement("select * from rpfnew where rpfusername=? and rpfpassword=? and location=?");
            ps.setString(1,s);
            ps.setString(2,t);
            ps.setString(3,u);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            if(count > 0) {
                System.out.println("valid user");
                String link = "http://localhost:63342/SIH12/AdminLTE-master/rpfdashboard.html";
                return link;
            }
            else {
                System.out.println("invalid user");
                String k = "http://localhost:63342/SIH/src/html/mysitefinal.html";
                return k;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String validationfir(String s,String t, String u) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        try {
            int count = 0;
            ps = c.prepareStatement("select * from rpfnew where rpfusername=? and rpfpassword=? and location=?");
            ps.setString(1,s);
            ps.setString(2,t);
            ps.setString(3,u);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            if(count > 0) {
                System.out.println("valid user");
                String link = "http://localhost:63342/SIH12/src/html/dashboard_first_page.html?";
                return link;
            }
            else {
                System.out.println("invalid user");
                String k = "http://localhost:63342/SIH/src/html/mysitefinal.html";
                return k;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return "";
    }
    public static String abc(String username) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = c.prepareStatement("select * from user where username=?");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            if(count > 0) {
                return "yes";
            }
            else{
                return "no";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return " ";}

    public static String validation(String s, String t) {

        Connection c = getDatabase();
        PreparedStatement ps= null;
        try {
//            }
            int count = 0;
             ps = c.prepareStatement("select * from user where username=? and userpassword=?");
            ps.setString(1, s);
            ps.setString(2, t);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                System.out.println("you're a valid user");
                String link = "http://localhost:63342/railrakshak/src/html/firnew.html?_ijt=82l15ta4l303gj3p15apo3qv9j&username=";
                return link;
            } else {
                System.out.println("invalid user");
                String k = "http://localhost:63342/railrakshak/src/html/mysitefinal.html?_ijt=rke6k4ggi2bcb8egkgv34qmo5h";
                return k;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return " ";

    }

    public static int validationandroid(String s, String t) {

        Connection c = getDatabase();
        PreparedStatement ps= null;
        try {
//            }
            int count = 0;
            ps = c.prepareStatement("select * from user where username=? and userpassword=?");
            ps.setString(1, s);
            ps.setString(2, t);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            if (count > 0) {
                System.out.println("you're a valid user");
                return count;
            } else {
                System.out.println("invalid user");

                return count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  try {
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }



    static public List<User> getUserData() throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        Connection conn = null;
        List<User> userList = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM usecase";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                User u = new User();
                u.setusername(rs.getString("usernamer"));
                u.setuserpassword(rs.getString("userpasswordr"));


                //Display values
                System.out.print("First: " + u.getusername());
                // System.out.print(", Age: " + age);
                System.out.print(", last: " + u.getuserpassword());


                userList.add(u);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (
                SQLException se)

        {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (
                Exception e)

        {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally

        {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!" + userList);

        return userList;
    }

    public static String reject(String a, String b, String e, String d) {
    Connection c = getDatabase();
    PreparedStatement ps = null;
        int count = 0;
        try {
            ps = c.prepareStatement("delete  from fir where username=? and firstnamein=? and descriptionit=? and firdatef=?");
            ps.setString(1,a);
            ps.setString(2,b);
            ps.setString(3,d);
            ps.setString(4,e);
            count = ps.executeUpdate();

                if( count > 0) {
                    System.out.println("deleted");
                    String link = "http://localhost:63342/SIH12/src/html/dashboard_first_page.html";
                return link;}
                else{
                    System.out.println("unsuccesful");
                    return " ";
                }



        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    return " ";}

    public static void storeFirPath(String ffname) {

    }

    public static String cancelfir1(String e,String f,String g)
    {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        int i = 0;
        try {
            ps = c.prepareStatement("delete from fir where firid=? AND username=?");
            ps.setString(1,g);
            ps.setString(2,e);
            i = ps.executeUpdate();

            if(i > 0) {
                return "yes";
            }
            else {
                return "no";
            }
        } catch (SQLException q) {
            q.printStackTrace();
        }
        return "";
    }




    public static String cancelfir(String e,String f, String g) {
        Connection c = getDatabase();
        PreparedStatement ps = null;
        try {
            int count = 0;
            ps = c.prepareStatement("SELECT * FROM user a,fir b WHERE a.username =? AND b.username=? AND a.userpassword = ? AND b.firid=? AND a.username=b.username;");
            ps.setString(1,e);
            ps.setString(2,e);
            ps.setString(3,f);
            ps.setString(4,g);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            if(count > 0) {
                String s = cancelfir1(e,f,g);
                if(s.equals("yes")) {



                    String link = "http://localhost:63342/SIH12/src/html/mysitefinal.html";
                    return link;
                }}
            else {
                System.out.println("invalid user");
                String k = "http://localhost:63342/SIH12/src/html/dashboard_first_page.html?";
                return k;
            }
        } catch (SQLException q) {
            q.printStackTrace();
        }
        return "";
    }
}
