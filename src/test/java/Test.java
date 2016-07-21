import com.wfj.exception.client.handler.EcMapperHandler;
import com.wfj.exception.client.handler.MyExceptionHandler;
import com.wfj.exception.client.util.PropertiesLoad;
import com.wfj.exception.client.util.SystemBootstrap;

import java.util.HashMap;
import java.util.Map;

public class Test {

    private static void c() {
        int i = 1 / 0;
        int j = 1 / 0;
    }
    @org.junit.Test
    public void sendMesTest() {
            addtMes("01");
//            addtMes("02");
//            addtMes("03");
    }

    public static void addtMes(String sysCode) {
        Map<String, String> bussMap = new HashMap<>();
        Map<String, String> errMap = new HashMap<String, String>();
        bussMap.put("001", "订单拆分");
        bussMap.put("002", "订单支付");
        bussMap.put("003", "订单库存");
        errMap.put("001", "商品信息不全");
        errMap.put("002", "物流地址不符合要求");
        errMap.put("003", "库存有误");
        EcMapperHandler.getInstanceEcMap().init(bussMap, errMap);
        SystemBootstrap.afterPropertiesSet();
//        PropertiesLoad.putProperties("IpAddre", "http://127.0.0.1:8089/");
//        PropertiesLoad.putProperties("IpAddre", "http://192.168.6.140:8089/");
        PropertiesLoad.putProperties("sysCode", sysCode);
        System.out.println(PropertiesLoad.getProperties("mq_server"));
        try {
            c();
        } catch (Exception e) {
            MyExceptionHandler myE = new MyExceptionHandler();
            myE.putException("002", "001", e);
        }
//        try {
//            c();
//        } catch (Exception e) {
//            MyExceptionHandler myE = new MyExceptionHandler();
//            myE.putException("002", "001", e);
//        }
//        try {
//            c();
//        } catch (Exception e) {
//            MyExceptionHandler myE = new MyExceptionHandler();
//            myE.putException("003", "003", e);
//        }
//
//        try {
//            c();
//        } catch (Exception e) {
//            MyExceptionHandler myE = new MyExceptionHandler();
//            myE.putException("003", "003", e);
//        }
//
//        try {
//            String[] a = new String[]{"aa", "bb"};
//            System.out.println(a[3]);
//        } catch (Exception e) {
//            MyExceptionHandler myE = new MyExceptionHandler();
//            myE.putException("003", "001", e);
//        }
//        try {
//            try {
//                String j = null;
//                String k = new String(j);
//
//            } catch (Exception e) {
//                throw new MyExceptionHandler("002", "001", e);
//            }
//        } catch (MyExceptionHandler e) {
//            System.out.println(e.getMessage());
//        }
//        try {
//            try {
//                String j = null;
//                String k = new String(j);
//
//            } catch (Exception e) {
//                throw new MyExceptionHandler("003", "002", e);
//            }
//        } catch (MyExceptionHandler e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            try {
//                String j = null;
//                String k = new String(j);
//
//            } catch (Exception e) {
//                throw new MyExceptionHandler("002", "001", e);
//            }
//        } catch (MyExceptionHandler e) {
//            System.out.println(e.getMessage());
//        }
    }
}
