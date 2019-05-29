package ServerPackage;

import Common.Bogliste;
import Common.Bruger;
import Common.LoginInf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;

public class UserHost implements Runnable{

    LoginInf loginInf = new LoginInf("Admin","Admin");
    Bogliste bogliste;
    Bruger bruger;
    ArrayList<? extends Object> classList;
    ArrayList<String> orderlist;
    String message;
    Server server;
    Method[] methods;
    Method Argument2;

    public UserHost(String brugerId, Server server){
        classList = new ArrayList<Object>(){Bruger bruger; Bogliste bogliste; LoginInf loginInf;};
        this.server = server;
        //classList.add(bruger);

    }

    public void run() {

        while(true){
            if(message != null){
                orderlist = new ArrayList<String>(Arrays.asList(message.split(".")));
                message = null;
            }

            if(orderlist.get(0) == "bruger"){
                if(orderlist.get(1) == "getBog"){
                    server.send(bogliste.getBog(Integer.parseInt(orderlist.get(2))));
                }else if (orderlist.get(1) == "addBog"){
                    //bogliste.addBog();
                }
            }

            for (Object object:classList) {
                if (orderlist != null){
                    methods = object.getClass().getDeclaredMethods();
                    for (Method method:methods){
                        if (method.getName() == orderlist.get(2)){
                            Argument2 = method;
                            System.out.println("Executing:" + method);
                        }
                    }
                    if(1==1){
                        try {
                            Object.class.getMethod(orderlist.get(1)).invoke(orderlist.get(2));

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
