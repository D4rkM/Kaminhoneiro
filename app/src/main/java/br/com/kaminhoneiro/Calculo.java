package br.com.kaminhoneiro;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16254861 on 30/10/2017.
 */

public class Calculo extends AppCompatActivity {

    TextView txt_total;
    ListView lstCalculo;
    CalculoAdapter calcAdapter;
    float resultado = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        txt_total = (TextView) findViewById(R.id.txt_total);
        lstCalculo = (ListView) findViewById(R.id.lista_calculo);


//            Toast.makeText(this, lista+"", Toast.LENGTH_SHORT).show();

        List<KilometroAndado> items = Select.from(KilometroAndado.class).orderBy("MES_ID").list();
        List<KilometroAndado> calcList = new ArrayList();

        for( int i = 0; i < items.size(); i++) {
            KilometroAndado lista = items.get(i);

            if (lista.getKm() <= 4000) {
                resultado += (float) ((lista.getKm() * 1.50));

            } else {
                resultado += (float) ((lista.getKm() * 1.25));

            }
            KilometroAndado km = new KilometroAndado();
            km.setMes(lista.getMes());
            km.setKm(resultado);
            calcList.add(km);
        }

        calcAdapter = new CalculoAdapter(this, calcList);
        lstCalculo.setAdapter(calcAdapter);

        txt_total.setText("Total: R$"+ resultado);

    }


    private class CalculoAdapter extends ArrayAdapter<KilometroAndado> {

        public CalculoAdapter(Context context, List<KilometroAndado> items){
            super(context,0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = convertView;

            if(v == null){
                v = LayoutInflater.from(getContext())
                        .inflate(R.layout.list_item, null);
            }

            KilometroAndado item = getItem(position);

            TextView Mes = v.findViewById(R.id.txt_mes_rodado);
            TextView km = v.findViewById(R.id.txt_kilometro_mes);


            Mes.setText(item.getMes()+"");
            km.setText("R$ "+item.getKm());

            return v;
        }
    }

}
