package ServerPackage;

import Common.Bogliste;
import Common.Bruger;
import Common.LoginInf;
import Common.SuperBogListe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

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
    Object retun;

    public UserHost(String brugerId, Server server){
        classList = new ArrayList<Object>(){Bruger bruger; Bogliste bogliste; SuperBogListe superBogListe;};

        this.server = server;

    }

    public void run() {

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
                                    if(){
                                        x = (chosenAction.getParameterTypes()[0].cast(orderArray[0]));
                                    }
                                    orderArray = (String[]) orderlist.toArray();
                                    requiredParameters[0]
                                    System.out.println("Executing:" + method);
                                }
                            }
                        }

                    }

                    if(object.getClass().getSimpleName() == orderlist.get(0)){

                        try {
                            retun = object.getClass().getMethod(orderlist.get(1)).invoke(orderlist.get(0),orderlist.get(2));
                            if(retun != null){
                                server.send(retun);
                                retun = null;
                            }
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
