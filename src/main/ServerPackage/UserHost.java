package main.ServerPackage;

import main.Common.Bogliste;
import main.Common.Bruger;
import main.Common.LoginInf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class UserHost implements Runnable{

    LoginInf loginInf = new LoginInf("Admin","Admin");
    Bogliste bogliste;
    Bruger bruger;
    ArrayList<Object> classList;
    ArrayList<String> orderlist = null;
    String[] orderArray;
    String message = null;
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

        System.out.println("USER " + bruger.getBrugerID());

    }

    public void run() {

        classList = new ArrayList<Object>();
        classList.add(bruger);
        classList.add(bruger.getBogliste());
        classList.add(server.getSuperBogliste());

        while(true){
            if(message != null){
                orderlist = new ArrayList<String>(Arrays.asList(message.split("\\.")));
                message = null;

                for (Object object:classList) {
                    if (orderlist != null && orderlist.get(0) != null){
                        if(object.getClass().getSimpleName().equals(orderlist.get(0))){
                            chosenObject = object;
                            methods = object.getClass().getDeclaredMethods();
                            for (Method method:methods){
                                if (method.getName().equals(orderlist.get(1))){
                                    chosenAction = method;
                                    requiredParameters = method.getParameterTypes();
                                    if (requiredParameters != null && requiredParameters.length>0 && requiredParameters[0].isPrimitive()) {
                                        try {
                                            retur = method.invoke(chosenObject,Integer.parseInt(orderlist.get(2)));
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            System.out.println("Executing:" + chosenAction + "On: " + chosenObject/* + "With Parameters: " + orderlist.get(2)*/);
                                            retur = chosenAction.invoke(chosenObject);
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
                System.out.println("Returning: " + retur);
                server.send(retur);
                retur = null;
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
