import beans.TableBean;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONWriter;
import redis.clients.jedis.Jedis;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import org.apache.commons.math3.stat.regression.SimpleRegression;



import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.function.Predicate;


public class Main {
    public static void main(String[] args) {
        // return "your first name is"+s+"middle name "+o;}
        Spark.get("/get", (request, response) -> {
            Main.getIp(request);
            System.out.println(request.attributes());
            System.out.println(request.queryString());
            Map<String, String[]> map = request.queryMap().toMap();
            List<User> u = DBUtil2.getUserData();
            if (map.containsKey("fname")) {
                System.out.println("debug: " + request.queryParams("fname"));
                System.out.println("debug: " + request.queryParams("lname"));
            }
            String s = request.queryParams("fname");
            String o = request.queryParams("lname");

            String w = new Gson().toJson(u);

            System.out.println(w);

            response.header("Access-Control-Allow-Origin",
                    "*");

            JSONArray table = new JSONArray((new Gson()).toJson(u));
            JSONObject result = new JSONObject();
            result.put("response", table);
            result.put("statusCode", 1);

            return result.toString();
        });


        Spark.get("/", (request, response) -> {
            Main.getIp(request);
            response.header("Access-Control-Allow-Origin",
                    "*");
            return "{}";
        });

        Spark.post("/data", (request, response) -> {
            Main.getIp(request);
            System.out.println();
            System.out.println(request.queryParams());
            for (String s : request.queryParams()) {
                System.out.println(request.queryParams(s));
            }
            User user = new User();
            user.setusername(request.queryParams("usernamer"));
            user.setuserpassword(request.queryParams("userpasswordr"));
            DBUtil2.putUser(user);

            return null;
        });
        Spark.post("/updateUser", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                System.out.println(request.queryParams());
                for (String s : request.queryParams()) {
                    System.out.println(request.queryParams(s));
                }
                User user = new User();
                user.setusername(request.queryParams("usernamer"));
                user.setuserpassword(request.queryParams("userpasswordr"));
                DBUtil2.updateUser(user);
                System.out.println("hey man");

                return " ";
            }
        });


        Spark.post("/deleteUser", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                System.out.println(request.queryParams());
                for (String s : request.queryParams()) {
                    System.out.println(request.queryParams(s));
                }
                User user = new User();
                user.setusername(request.queryParams("usernamer"));
                user.setuserpassword(request.queryParams("userpasswordr"));
                DBUtil2.deleteUser(user.getusername());
                System.out.println(" hello");
                return " ";
            }

        });


        Spark.post("/registrationentry", (request, response) -> {
            System.out.println();
            Main.getIp(request);
            System.out.println(request.queryParams());
            for (String s : request.queryParams()) {
                System.out.println(request.queryParams(s));
            }
            User u = new User();
            u.setusername(request.queryParams("usernamer"));
            u.setuserpassword(request.queryParams("userpasswordr"));
            u.setfirstname(request.queryParams("firstnamer"));
            u.setmiddlename(request.queryParams("middlenamer"));
            u.setlastname(request.queryParams("lastnamer"));
            u.setdob(request.queryParams("dobr"));
            u.setgender(request.queryParams("gender"));
            u.setaddress(request.queryParams("addressr"));
            u.setcity(request.queryParams("cityr"));
            u.setpincode(request.queryParams("pincoder"));
            u.setdistrict(request.queryParams("districtr"));
            u.setstate(request.queryParams("stater"));
            u.setcountry(request.queryParams("countryr"));
            u.setuseremail(request.queryParams("useremailr"));
            u.setusermobile(request.queryParams("usermobiler"));
            String s = u.getuseremail();
            System.out.println(s);
            Redis.addUser(u);
            Emailsend.Email(s, u.getusername());
            System.out.println("add kar dia bhai");
            return "<html><form action=\"http://192.168.43.57:4567/otpVerification\" method=\"get\" id=\"form1\">\n" +
                    "    enter OTP: <input type=\"text\" name=\"user-" + u.getusername() + "\"><br>\n" +
                    "<input type=\"submit\" name=\"submit\" value=\"submit\" form=\"form1\"></form>\n" +
                    "</html>";
        });

        Spark.get("/otpVerification", (request, response) -> {
            for (String username : request.queryParams()) {
                System.out.println(username + ":" + request.queryParams(username));
                if (username.contains("-")) {
                    username = username.split("-")[1];
                    if (Redis.IsUserUnverified(username) && Redis.checkOTP(username, request.queryParams("user-" + username))) {
                        try {
                            DBUtil2.putUser(Redis.fetchUser(username));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        response.redirect("http://localhost:63342/railrakshak/src/html/Successful_registration_page.html?_ijt=gui1l2j2tvjcee467gesb4c9g7");
                    } else {

                        response.redirect("http://localhost:63342/railrakshak/src/html/mysitefinal.html");
                    }
                }
            }
            return null;
        });

        Spark.post("/a1", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                System.out.println();
                Main.getIp(request);
                File archivo = new File("./uploads/");
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(archivo);
                ServletFileUpload fileUpload = new ServletFileUpload(factory);
                List<FileItem> items = fileUpload.parseRequest(request.raw());
                FileItem item = items.stream()
                        .filter(e -> "pdfFile".equals(e.getFieldName()))
                        .findFirst().get();
                FileItem fname = items.stream().filter(fileItem -> "filename".
                        equals(fileItem.getFieldName())).findFirst().get();
//                for (FileItem fileItem : items) {
//                    if (fileItem.getFieldName().equals("filename")) {
//                        fname = fileItem;
//                    }
//                }
                String ffname = new String(fname.get());
                item.write(new File(archivo, ffname + ".pdf"));
                DBUtil2.storeFirPath(ffname);
                String k = request.queryParams("emailin");
                String firid = request.queryParams("firid");
                System.out.println(k);
                System.out.println(firid);
                Emailsend.Email1(k, firid);
                DBUtil2.accepted(firid);

                return null;
            }
        });

        Spark.get("/getpdf", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                Main.getIp(request);
                String a = request.queryParams("username");
                String b = request.queryParams("firid");
                String c = a + b;
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader("./uploads/" + c + ".pdf");

                    if (fileReader == null) {
                        return "http://localhost:63342/SIH12/src/html/mysitefinal.html";
                    } else {
                        InputStream pdf_path = new FileInputStream(new File("./uploads/" + c + ".pdf"));

                        response.type("application/pdf");
                        response.header("Content-Disposition", "attachment; filename=" + c + ".pdf");
                        OutputStream responseOutputStream = response.raw().getOutputStream();
                        byte[] buf = new byte[4096];
                        int len = -1;

                        while ((len = pdf_path.read(buf)) != -1) {
                            responseOutputStream.write(buf, 0, len);
                        }

                        responseOutputStream.flush();
                        responseOutputStream.close();
//                        request.raw().
//                                getRequestDispatcher( "http://localhost:63342/railrakshak/a1/mysitefinal.html?pdfcheck=1")
//                                .forward(request.raw(),response.raw());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("not found");
                    response.redirect("http://localhost:63342/SIH12/src/html/mysitefinal.html?pdfcheck=0");
                }
                return null;

            }
        });

        Spark.post("/validation", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                System.out.println(request.queryParams());
                for (String s : request.queryParams()) {
                    System.out.println(request.queryParams(s));
                }
                String e = request.queryParams("usr1");
                String f = request.queryParams("pwd1");
                String m = DBUtil2.validation(e, f);
                String l = m + e;
                response.redirect(l);
                return null;

            }

        });
        Spark.get("/cancelfir", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                System.out.println(request.queryParams());
                for (String s : request.queryParams()) {
                    System.out.println(request.queryParams(s));
                }
                String e = request.queryParams("usr12");
                String f = request.queryParams("pwd12");
                String g = request.queryParams("firid12");
                String m = DBUtil2.cancelfir(e, f,g);
                String l = m + e;
                response.redirect(m);
                return null;

            }

        });

        Spark.post("/validationandroid", (request, response) -> {
            Main.getIp(request);
            System.out.println();
            System.out.println(request.queryParams());
            for (String s : request.queryParams()) {
                System.out.println(request.queryParams(s));
            }
            String e = request.queryParams("usr1");
            String f = request.queryParams("pwd1");
            Integer y = DBUtil2.validationandroid(e, f);
            if (y == 1) {
                return true;
            } else {
                return false;
            }
        });
        Spark.get("/validationrpf", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                String e = request.queryParams("userrpf");
                String f = request.queryParams("pwdrpf");
                String s = request.queryParams("locationrpf");
                System.out.println(e);
                String m = DBUtil2.validationfir1(e, f, s);
                response.redirect(m);
                return null;
            }
        });

        Spark.get("/validationfir", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                System.out.println();
                String e = request.queryParams("rpfusername");
                String f = request.queryParams("rpfpassword");
                String s = request.queryParams("location");
                System.out.println(e);
                String m = DBUtil2.validationfir(e, f, s);
                response.redirect(m);
                return null;
            }
        });

        Spark.get("/g", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                return "success";
            }
        });
        Spark.get("/getfir1", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                Main.getIp(request);
                response.header("Access-Control-Allow-Origin", "*");
                String id = request.queryParams("stationf");
                System.out.println(id);
                List<Fir> result = DBUtil2.retrieveFir2(id);
                System.out.println(result);
                return new Gson().toJson(result);
            }
        });
        Spark.get("/getfir2", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                Main.getIp(request);
                response.header("Access-Control-Allow-Origin", "*");
                String id = request.queryParams("stationf");
                System.out.println(id);
                List<Fir> result = DBUtil2.retrieveFir2(id);
                System.out.println(result);
                return new Gson().toJson(result);
            }
        });

        Spark.get("/getfir", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Main.getIp(request);
                response.header("Access-Control-Allow-Origin", "*");
                String id = request.queryParams("stationf");
                List<Fir> result = DBUtil2.retrieveFir(id);
                System.out.println(result);
//                System.out.println("before");
//                for (int i = 0; i < result.size(); i++) {
//                    System.out.println(result.get(i).getFirstnamein());
//                }
//
//                Collections.sort(result, new Comparator<Fir>() {
//                    public int compare(Fir o1, Fir o2) {
//                        return (o1.getDatefromf().compareTo(o2.getDatefromf()) > 0) ? 1 : -1;
//
//                    }
//                });
//                System.out.println();
//
//                System.out.println("after");
//                for (int i = 0; i < result.size(); i++) {
//                    System.out.println(result.get(i).getFirstnamein());
//                }
//                System.out.println(result);
                return new Gson().toJson(result);
            }
        });

        Spark.get("/reject", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Fir f = new Fir();
                String a = request.queryParams("username");
                String b = request.queryParams("firstnamein");
                String e = request.queryParams("firdatef");
                String d = request.queryParams("descriptionit");
                String l = DBUtil2.reject(a, b, e, d);
                response.redirect(l);

//                f.setUsername(request.queryParams("username"));
//                f.setFirstnamein(request.queryParams("firstnamein"));
//                f.setFirdatef(request.queryParams("firdatef"));
//                f.setDescriptionit(request.queryParams("descriptionit"));
//                String

                return null;
            }
        });

        Spark.get("/getaadhar", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");
                String id = request.queryParams("aadharno");
                System.out.println(id);
                List<User> result = DBUtil2.getaadhar(id);
                System.out.println(result);
                System.out.println();
                return new Gson().toJson(result);
            }
        });

        Spark.get("/getfirdetails", (request, response) -> {
            Main.getIp(request);
            response.header("Access-Control-Allow-Origin", "*");
            String id = request.queryParams("firid");
            System.out.println(id);
            List<Fir> result = DBUtil2.getFirDetails(id);
//                result.removeAll(Collections.singleton(null));

            System.out.println(result);
            System.out.println();
//              Fir f =   DBUtil2.getFirDetails(id);
            return new Gson().toJson(result);
        });

        Spark.get("/tab", (request, response) -> {
            Main.getIp(request);
            List<TableBean> tb = new ArrayList<TableBean>();
            tb.add(new TableBean("1", "text", "/kkkkk", "sad"));
            tb.add(new TableBean("1", "text", "/kkkkk", "sad"));
            tb.add(new TableBean("1", "text", "/kkkkk", "sad"));
            tb.add(new TableBean("1", "text", "/kkkkk", "sad"));
            tb.add(new TableBean("1", "text", "/kkkkk", "sad"));
            //    tb.add(new TableBean(""))

            response.header("Access-Control-Allow-Origin", "*");

            return new Gson().toJson(tb);
        });

//        Jedis jedis = new Jedis("localhost");
//
//        jedis.set("unverifies:username",new Gson().toJson(User));
//        User u = new Gson().fromJson(jedis.geoadd(""),User.class);
//        jedis.set("tutorial-name", "Redis tutorial");
//        // Get the stored data and print it
//        System.out.println("Stored string in redis:: "+ jedis.get("tutorialname"));
//
////        jedis.setex("otp:username",100,"fghjkl");
//        System.out.println(jedis.get("otp:username"));
//        System.out.println(jedis.get("opt:asd"));


        Spark.post("/registerfir", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                System.out.println();
                Main.getIp(request);
                System.out.println(request.queryParams());
                for (String s : request.queryParams()) {
                    System.out.println(request.queryParams(s));
                }
                Fir f = new Fir();
                f.setUsername(request.queryParams("username"));
                f.setFirstnamein(request.queryParams("firstnamein"));
                f.setMiddlenamein(request.queryParams("middlenamein"));
                f.setLastnamein(request.queryParams("lastnamein"));
                f.setFathersnamein(request.queryParams("fathersnamein"));
                f.setDobin(request.queryParams("dobin"));
                f.setOccupationin(request.queryParams("occupationin"));
                f.setGenderin(request.queryParams("genderin"));
                f.setEmailin(request.queryParams("emailin"));
                f.setContactnoin(request.queryParams("contactnoin"));
                f.setAddressin(request.queryParams("addressin"));
                f.setNationalityin(request.queryParams("nationalityin"));
                f.setCityin(request.queryParams("cityin"));
                f.setStatein(request.queryParams("statein"));
                f.setDistrictin(request.queryParams("districtin"));
                f.setAadhaarnoin(request.queryParams("aadhaarnoin"));
                f.setReasonsdelayin(request.queryParams("reasonsdelayin"));
                f.setFirtypef(request.queryParams("firtypef"));
                f.setFirdatef(request.queryParams("firdatef"));
                f.setFirtimef(request.queryParams("firtimef"));
                f.setStationf(request.queryParams("stationf"));
                f.setDatefromf(request.queryParams("datefromf"));
                f.setDatetof(request.queryParams("datetof"));
                f.setTimefromf(request.queryParams("timefromf"));
                f.setTimetof(request.queryParams("timetof"));
                f.setCityf(request.queryParams("cityf"));
                f.setStatef(request.queryParams("statef"));
                f.setTrainnof(request.queryParams("trainnof"));
                f.setTrainnamef(request.queryParams("trainnamef"));
                f.setSourcef(request.queryParams("sourcef"));
                f.setDestinationf(request.queryParams("destinationf"));
                f.setDetailsf(request.queryParams("detailsf"));
                f.setTypeit(request.queryParams("typeit"));
                f.setValueworthit(request.queryParams("valueworthit"));
                f.setDescriptionit(request.queryParams("descriptionit"));
                f.setSuspectnames(request.queryParams("suspectnames"));
                f.setRelativesnames(request.queryParams("relativesnames"));
                f.setContactnos(request.queryParams("contactnos"));
                f.setGenders(request.queryParams("genders"));
                f.setAreas(request.queryParams("areas"));
                f.setCitys(request.queryParams("citys"));
                f.setDistricts(request.queryParams("districts"));
                f.setStates(request.queryParams("states"));

                String l = DBUtil2.putFir(f);
                response.redirect(l);
                System.out.println("fir registered");
                return "fir submitted";
            }

        });

//        Spark.get("/test", new Route() {
//            public Object handle(Request request, Response response) throws Exception {
//                response.type("application/pdf");
//                response.header("Content-Disposition","attachment;filename="+ C:\\Users\\munde\\Downloads\\test.pdf");
//
//                return " ";
//            }
//        });
        Spark.get("/test", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");

                Main.getIp(request);
                DBUtil2.uploadpdf();


                return "check status";
            }
        });
        Spark.get("/test12", (request, response) -> {
            DBUtil2.retrievepdf();Main.getIp(request);

            return "hi";
        });

        Spark.get("/track", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
//            Main.getIp(request);
            String id = request.queryParams("firid");
//            System.out.println(id);
            List<Track> result = DBUtil2.track(id);
            System.out.println(result);
            return new Gson().toJson(result);
        });

        Spark.get("/pie", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            List<Track> result = DBUtil2.pie();
            System.out.println(result);
            return new Gson().toJson(result);
        });
        Spark.get("/bar", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");
                List<Fir> result = DBUtil2.bar();
                System.out.println();
                return new Gson().toJson(result);
            }
        });
        Spark.get("/return", new Route() {
            public Object handle(Request request, Response response) throws Exception {
//                response.header("Access-Control-Allow-Origin", "*");
                Fir f = new Fir();
                Main.getIp(request);
                f.setUsername(request.queryParams("username"));
                f.setFirstnamein(request.queryParams("firstnamein"));
                f.setLastnamein(request.queryParams("lastnamein"));
                String result = DBUtil2.updation(f);
                System.out.println(result);
                response.redirect(result);
                return null;
            }
        });
        Spark.get("/updatestatus", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                Fir f = new Fir();
                f.setFirid(request.queryParams("firid"));
                f.setStatus(request.queryParams("status"));
                System.out.println(f);
                String result = DBUtil2.updatestatus(f);
                System.out.println(result);
                response.redirect(result);

                return null;
            }
        });
    }

    public static void getIp(Request request) {
        String ip = request.raw().getRemoteAddr();
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            String ipAddress = inetAddress.getHostAddress();
            ip = ipAddress;
        }
        System.out.println("public ip ::: " + ip);
    }
}