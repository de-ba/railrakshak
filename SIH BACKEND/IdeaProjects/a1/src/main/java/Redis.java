import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

/**
 * Created by munde on 29/03/2018.
 */
public class Redis {

    private static Jedis getRedisConnection() {
        return new Jedis("localhost");
    }

    public static boolean checkOTP(String key, String value) {
        Jedis jedis = new Jedis("localhost");
        return value.equals(jedis.get(key));
//        return jedis.get(key).equals(value);
    }

    public static void storeOTP(String email, String otp) {
        Jedis jedis = new Jedis("localhost");
        jedis.setex(email, 5 * 60, otp);
    }

    public static void addUser(User user) {
        Jedis jedis = getRedisConnection();
        jedis.setex("unverified::" + user.getusername(), 10 * 60, new Gson().toJson(user));
    }

    public static User fetchUser(String username) {
        Jedis jedis = getRedisConnection();
        return new Gson().fromJson(jedis.get("unverified::" + username), User.class);
    }

    public static boolean IsUserUnverified(String username) {
        String result = getRedisConnection().get("unverified::" + username);
        if (result != null && result.length() > 0) return true;
        return false;
    }
}
