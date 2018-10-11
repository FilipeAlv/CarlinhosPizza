package Util;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Util {
    public static boolean verificaConexao(Context context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static void carregarImagem(ImageView imageView, String url){
        Picasso.get().load(url).into(imageView);
    }


}
