package br.com.kaminhoneiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spin_mes;
    EditText txt_kilometro;
    String nomeMes;
    int idMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_mes = (Spinner) findViewById(R.id.spinner_mes);
        txt_kilometro = (EditText) findViewById(R.id.txt_km);

        final ArrayAdapter mes = ArrayAdapter.createFromResource(this, R.array.Mes ,android.R.layout.simple_spinner_item);
        spin_mes.setAdapter(mes);

        spin_mes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nomeMes = (String) mes.getItem(i);
                idMes = (int) mes.getItemId(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.ver) {
            startActivity(new Intent(this, ListaKm.class));
        }else if(id == R.id.calcular) {
            startActivity(new Intent(this, Calculo.class));
        }
        return true;
    }

    public void inserir(View view) {

        String km = txt_kilometro.getText().toString();

        if(km.isEmpty()){
            txt_kilometro.setError("Insira a Quilômetragem percorrida");
            return;
        }

        KilometroAndado item = new KilometroAndado(nomeMes, Float.parseFloat(km), idMes);
        Toast.makeText(MainActivity.this, idMes+"", Toast.LENGTH_SHORT).show();

        List<KilometroAndado>  lst = Select.from(KilometroAndado.class).where( Condition.prop("mes").eq(nomeMes)).list();

        if(lst!= null && lst.size() > 0){

            KilometroAndado nKm = lst.get(0);

         //   KilometroAndado itemId = (KilometroAndado) KilometroAndado.findWithQuery(KilometroAndado.class, "SELECT _id FROM KILOMETRO_ANDADO WHERE MES =", nomeMes);
         //   KilometroAndado nKm = KilometroAndado.findById(KilometroAndado.class, itemId.getId());
          //  nKm.setMes(nomeMes);
            nKm.setKm(Float.parseFloat(km));
            nKm.save();
        }else{
            item.save();
        }
        txt_kilometro.setText(null);

    }

}
