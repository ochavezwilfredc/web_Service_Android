package a2017.uss.cristobal.ejemplo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import a2017.uss.cristobal.ejemplo.services.Constants;

public class verEmpleadoActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    ListView lv;
    Button button;
    private  String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empleado);
        button = (Button)findViewById(R.id.btn_return);

        lv = (ListView)findViewById(R.id.lv_empleados);
        lv.setOnItemClickListener(this);
        getEmpleados();
    }


    public void onClickVolver(View view){
        startActivity(new Intent(verEmpleadoActivity.this,MainActivity.class));
    }


    private void getEmpleados(){


        //Ejecuta hilos secundarios o asincronos
        class getEmpleado extends AsyncTask<Void,Void,String> {
            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(verEmpleadoActivity.this,"Listando Empleados...","Espere...",false,false);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                json_string = s;
                listarEmpleados();

            }

            @Override
            protected String doInBackground(Void... v) {
                requesthandler rh =  new requesthandler();
                String res =  rh.sendGetRequest(Constants.GET_EMPLEADO_ID);
                return res;
            }
        }

        getEmpleado ae =  new getEmpleado();
        ae.execute();

    }

    private  void listarEmpleados(){
        JSONObject jsonObject = null;

        ArrayList<HashMap<String,String>> lista_empledos =  new ArrayList<HashMap<String,String>>();

        try {
            jsonObject =  new JSONObject(json_string);
            JSONArray result = jsonObject.getJSONArray("resultado");

            for (int i=0;i<result.length();i++){
                JSONObject jo =  result.getJSONObject(i);
                String id = jo.getString("id");
                String nombre = jo.getString("nombre");
                HashMap<String,String> empleado = new HashMap<>();
                empleado.put("id",id);
                empleado.put("nombre",nombre);

                lista_empledos.add(empleado);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adaptador =  new SimpleAdapter(
                verEmpleadoActivity.this, lista_empledos,R.layout.item_empleado,
                new String[]{"id","nombre"},
                new int[]{R.id.et_id,R.id.et_nombre_item}
        );

        lv.setAdapter(adaptador);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =  new Intent(verEmpleadoActivity.this,detalleActivity.class);
        HashMap<String,String> map = (HashMap)parent.getItemAtPosition(position);
        String empId =  map.get("id").toString();
        intent.putExtra("id",empId);
        startActivity(intent);

    }
}
