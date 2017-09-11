package a2017.uss.cristobal.ejemplo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

import a2017.uss.cristobal.ejemplo.services.Constants;

public class detalleActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_id,ed_nom, et_des, et_sueldo;
    private Button btn_delete, btn_edit;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        btn_delete = (Button)findViewById(R.id.btn_eliminar);
        btn_delete.setOnClickListener(detalleActivity.this);

        btn_edit = (Button)findViewById(R.id.btn_actualizar);
        btn_edit.setOnClickListener(detalleActivity.this);

        et_id = (EditText)findViewById(R.id.et_det_id);
        ed_nom = (EditText)findViewById(R.id.et_det_nombre);
        et_des = (EditText)findViewById(R.id.et_det_designacion);
        et_sueldo = (EditText)findViewById(R.id.et_det_sueldo);

        Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id");
        //Toast.makeText(detalleActivity.this,id,Toast.LENGTH_SHORT).show();
        getEmpleadoID();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_actualizar:
                actualizarEmpleado();
                startActivity(new Intent(detalleActivity.this,verEmpleadoActivity.class));
                break;

            case R.id.btn_eliminar:
                eliminar();
                break;
        }
    }

    private void eliminar() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Esta seguro de eliminar ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();

    }
    public void aceptar() {
        deleteEmpleado();
        finish();

    }

    public void cancelar() {
        finish();
    }




    private void getEmpleadoID(){

        //Ejecuta hilos secundarios o asincronos
        class addEmpleado extends AsyncTask<Void,Void,String> {

            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(detalleActivity.this,"Consultado...","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();

                JSONArray emp = null;
                try {
                    emp = new JSONArray(s);
                    et_id.setText(emp.getString(0));
                    ed_nom.setText(emp.getString(1));
                    et_des.setText(emp.getString(2));
                    et_sueldo.setText(emp.getString(3));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... v) {
                requesthandler rh =  new requesthandler();
                String res =  rh.sendGetRequestParam(Constants.SELECT_EMPLEADO_ID,id);
                return res;
            }
        }

        addEmpleado ae =  new addEmpleado();
        ae.execute();

    }

    private void actualizarEmpleado(){
        final  String id =  et_id.getText().toString().trim();
        final  String nombre =  ed_nom.getText().toString().trim();
        final  String desg =  et_des.getText().toString().trim();
        final  String sal =  et_sueldo.getText().toString().trim();


        //Ejecuta hilos secundarios o asincronos
        class editEmpleado extends AsyncTask<Void,Void,String>{

            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(detalleActivity.this,"Agregando...","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                Toast.makeText(detalleActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",id);
                params.put("nombre",nombre);
                params.put("designacion",desg);
                params.put("salario",sal);
                requesthandler rh =  new requesthandler();
                String res =  rh.sendPostRequest(Constants.UPDATE_EMPLEADO,params);
                return res;
            }
        }

        editEmpleado ae =  new editEmpleado();
        ae.execute();

    }


    private void deleteEmpleado(){
        final  String id =  et_id.getText().toString().trim();


        //Ejecuta hilos secundarios o asincronos
        class deleteEmpleado extends AsyncTask<Void,Void,String>{

            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                et_id.setText("");ed_nom.setText("");et_des.setText("");et_sueldo.setText("");
                loaddin = ProgressDialog.show(detalleActivity.this,"Actualizado...","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                Toast.makeText(detalleActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                requesthandler rh =  new requesthandler();
                String res =  rh.sendGetRequestParam(Constants.DELETE_EMPLEADO,id);
                return res;
            }
        }

        deleteEmpleado ae =  new deleteEmpleado();
        ae.execute();

    }
    
}
