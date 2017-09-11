package a2017.uss.cristobal.ejemplo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

import a2017.uss.cristobal.ejemplo.services.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_reg, btn_ver;
    EditText et_nombre, et_des, et_salario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_reg = (Button)findViewById(R.id.btn_registrar);
        btn_reg.setOnClickListener(this);
        btn_ver = (Button)findViewById(R.id.btn_ver);
        btn_ver.setOnClickListener(this);

        et_nombre = (EditText)findViewById(R.id.et_nombre);
        et_des = (EditText)findViewById(R.id.et_designacion);
        et_salario = (EditText)findViewById(R.id.et_sueldo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registrar:
                this.agregarEmpleado();
                break;

            case R.id.btn_ver:
                startActivity(new Intent(MainActivity.this, verEmpleadoActivity.class));
                break;
        }


    }

    private void agregarEmpleado(){
        final  String nombre =  et_nombre.getText().toString().trim();
        final  String desg =  et_des.getText().toString().trim();
        final  String sal =  et_salario.getText().toString().trim();


        //Ejecuta hilos secundarios o asincronos
        class addEmpleado extends AsyncTask<Void,Void,String>{

            ProgressDialog loaddin;
           @Override
            protected void onPreExecute() {
                super.onPreExecute();
               et_nombre.setText("");et_des.setText("");et_salario.setText("");
               loaddin = ProgressDialog.show(MainActivity.this,"Agregando...","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nombre",nombre);
                params.put("designacion",desg);
                params.put("salario",sal);
                requesthandler rh =  new requesthandler();
                String res =  rh.sendPostRequest(Constants.SET_EMPLEADO,params);
                return res;
            }
        }

        addEmpleado ae =  new addEmpleado();
        ae.execute();

    }
}
