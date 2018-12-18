package network;

/**
 * 创建时间： 2018/11/30
 * 作者：yanyinan
 * 功能描述：
 */
public interface Api {

    String LOCAL_IP = "http://192.168.4.114:8025";

    String DEBUG_IP = "http://119.23.230.72:8025";

    String RELEASE_IP = "http://horoscope.funboxapp.net";

    String SUBMIT_HOROSCOPE = RELEASE_IP + "/api/demo/v1/fortune/addFortune";

    String UPDATE_HOROSCOPE = RELEASE_IP + "/api/demo/v1/fortune/editFortune";

    String FETCH_HOROSCOPE = RELEASE_IP + "/api/demo/v1/fortune/getFortune";
}
