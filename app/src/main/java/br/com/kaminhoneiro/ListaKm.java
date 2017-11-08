package br.com.kaminhoneiro;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.query.Select;

import java.util.List;

/**
 * Created by 16254861 on 30/10/2017.
 */

public class ListaKm extends AppCompatActivity {

    ListView lstKm;
    ListaKmAdapter kmAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilometro_por_mes);
        lstKm = (ListView) findViewById(R.id.lista_kilometro_andado);

        List<KilometroAndado> lst = Select.from(KilometroAndado.class).orderBy("MES_ID").list();
        kmAdapter = new ListaKmAdapter(this, lst);
        lstKm.setAdapter(kmAdapter);

    }

    private class ListaKmAdapter extends ArrayAdapter<KilometroAndado>{

        public ListaKmAdapter(Context context, List<KilometroAndado> items){
            super(context, 0 , items);
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

            Mes.setText(item.getMes() + "");
            km.setText(item.getKm() + "");

            return v;
        }
    }

}
