package a2017.uss.cristobal.ejemplo.services;

/**
 * Created by crist on 17/08/2017.
 */

public final class Constants {

    //IP que se utiliza para la conexión.
    private static final String IP = "192.168.1.59";

    //Puerto que se utiliza para la conexión.
    private static final String PUERTO_HOST = ":8000";

    //Constantes

    //URLS para el acceso a los datos
    public static final String SET_EMPLEADO = "http://" + IP + PUERTO_HOST + "/clase/registrarempleado.php";
    public static final String UPDATE_EMPLEADO = "http://" + IP + PUERTO_HOST + "/clase/actualizar.php";
    public static final String DELETE_EMPLEADO = "http://" + IP + PUERTO_HOST + "/clase/eliminar.php?id=";
    public static final String SELECT_EMPLEADO_ID = "http://" + IP + PUERTO_HOST + "/clase/consulta.php?id=";
    public static final String GET_EMPLEADO_ID = "http://" + IP + PUERTO_HOST + "/clase/getallempleado.php";





}



