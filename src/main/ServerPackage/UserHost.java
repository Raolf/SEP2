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

        System.out.println("USERRRRRRRRRRRRRR " + bruger.getBrugerID());

    }

    public void run() {

        classList = new ArrayList<Object>();
        classList.add(bruger);
        classList.add(bogliste);
        classList.add(server.getSuperBogliste());

        while(true){
            if(message != null){
                orderlist = new ArrayList<String>(Arrays.asList(message.split("\\.")));
                System.out.println("USERHOST " + orderlist.get(0));
                System.out.println(message);
                System.out.println("SIMPLE NAME: " + orderlist.get(0).getClass().getSimpleName());
                System.out.println("CLASSLIST: " + classList.get(0));
                for (Object object:classList) {
                    /*System.out.println("OBJECT: " + object.getClass().getSimpleName());
                    System.out.println("ORDERLIST 0: " + orderlist.get(0));*/
                    if (orderlist != null && orderlist.get(0) != null){
                        if(object.getClass().getSimpleName().equals(orderlist.get(0))){
                            chosenObject = object;
                            methods = object.getClass().getDeclaredMethods();
                            for (Method method:methods){
                                if (method.getName().equals(orderlist.get(1))){
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
                                            System.out.println("For Loop Done");
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
    }
    public synchronized void message(String message){
        this.message = message;

    }
    public int getBID(){
        return bruger.getBrugerID();
    }
}
