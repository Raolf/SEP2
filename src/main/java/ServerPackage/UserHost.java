package ServerPackage;

import Common.Bogliste;
import Common.Bruger;
import Common.LoginInf;
import Common.SuperBogListe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class UserHost implements Runnable{

    LoginInf loginInf = new LoginInf("Admin","Admin");
    Bogliste bogliste;
    Bruger bruger;
    ArrayList<Object> classList;
    ArrayList<String> orderlist;
    String[] orderArray;
    String message;
    Server server;
    Method[] methods;
    Object chosenObject;
    Method chosenAction;
    Class<?>[] requiredParameters;
    Object retur;
    Runnable parameterFunktion;

    public UserHost(Bruger bruger, Server server){
        this.bruger = bruger;
        this.server = server;

    }

    public void run() {

        classList = new ArrayList<Object>();
        classList.add(this.bruger);
        classList.add(bogliste);
        classList.add(server.getSuperBogliste());
        while(true){
            if(message != null){
                orderlist = new ArrayList<String>(Arrays.asList(message.split("\\.")));
                message = null;
            }

            for (Object object:classList) {
                if (orderlist != null){
                    if(object.getClass().getSimpleName() == orderlist.get(0)){
                        chosenObject = object;
                        methods = object.getClass().getDeclaredMethods();
                        for (Method method:methods){
                            if (method.getName() == orderlist.get(2)){
                                chosenAction = method;
                                requiredParameters = method.getParameterTypes();
                                if (requiredParameters != null) {
                                    if(requiredParameters[0].isPrimitive()){
                                        try {
                                            retur = method.invoke(chosenObject,Integer.parseInt(orderlist.get(2)));
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }else{
                                        retur = chosenAction.getParameterTypes()[0].cast(orderArray[0]);
                                    }
                                    orderArray = (String[]) orderlist.toArray();

                                    System.out.println("Executing:" + chosenAction + "On: " + chosenObject + "With Parameters: " + orderlist.get(2));
                                } else {
                                    try {
                                        chosenAction.invoke(chosenObject);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
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
