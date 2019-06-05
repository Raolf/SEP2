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
                System.out.println(classList.size());
                message = null;

                for (Object object:classList) {
                    System.out.println("Object: " + object.getClass());

                    System.out.println("Object: " + object.getClass().getSimpleName());
                    System.out.println("Orderlist 0: " + orderlist.get(0));
                    if (orderlist != null && orderlist.get(0) != null){
                        System.out.println(object.getClass().getSimpleName().equals(orderlist.get(0)));
                        if(object.getClass().getSimpleName().equals(orderlist.get(0))){
                            chosenObject = object;
                            System.out.println("found object"+chosenObject);
                            methods = object.getClass().getDeclaredMethods();
                            System.out.println("Target Method: " + orderlist.get(1));
                            for (Method method:methods){

                                System.out.println("Method: " + method.getName());
                                if (method.getName().equals(orderlist.get(1))){
                                    chosenAction = method;
                                    System.out.println("found method"+chosenAction);
                                    requiredParameters = method.getParameterTypes();
                                    System.out.println("");
                                    if (requiredParameters != null && requiredParameters.length>0 && requiredParameters[0].isPrimitive()) {
                                        try {
                                            System.out.println("Executing:" + chosenAction + "On: " + chosenObject + "With Parameters: " + orderlist.get(2));
                                            retur = method.invoke(chosenObject,Integer.parseInt(orderlist.get(2)));
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("For Loop Done");
                                        //orderArray = (String[]) orderlist.toArray();
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
