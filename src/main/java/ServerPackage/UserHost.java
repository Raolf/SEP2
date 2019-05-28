package ServerPackage;

import Common.Bogliste;
import Common.Bruger;
import Common.LoginInf;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class UserHost implements Runnable{

    LoginInf loginInf = new LoginInf("Admin","Admin");
    Bogliste bogliste;
    Bruger bruger;
    ArrayList<?> classList;
    ArrayList<String> orderlist;
    String order;
    String message;

    public UserHost(String brugerId){
        classList = new ArrayList<Object>(){Bruger bruger; Bogliste bogliste; LoginInf loginInf;};

    }

    public void run() {

        while(true){
            if(message != null){
                orderlist = new ArrayList<String>(Arrays.asList(message.split(".")));
                message = null;
            }
            for (Object object:classList) {
                if (orderlist != null){
                    if(1==1){
                        try {
                            Object.class.getMethod(orderlist.get(1)).invoke(orderlist.get(0));

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public void message(String message){
        this.message = message;
    }
    public int getBID(){
        return bruger.getBrugerID();
    }
}
