package br.com.buscadoctor.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class Functions {

    private static SharedPreferences sharedPreferences;
    private static int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "BuscaDoctor";

    /**
     * Metodo para salvar usuario nas preferencias.
     *
     * @param context
     * @param state
     */
    public static void saveUser(Context context, boolean state) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("usuario", state);
        editor.apply();
    }

    /**
     * Metodo para retornar usuario logado.
     *
     * @param context
     * @return
     */
    public static boolean getUser(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getBoolean("usuario", false);
    }

    /**
     * Este metodo verifica a conexao com a internet
     *
     * @since 1.0.0
     */
    public static boolean isconnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void saveUserId(Context context, int id) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.apply();
    }

    /**
     * Metodo para retornar usuario logado.
     *
     * @param context
     * @return
     */
    public static int getUserId(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getInt("id", 0);
    }

    /**
     * Este metodo transforma uma string em Data.
     *
     * @param date
     * @return data formatado
     * @since 1.0.0
     */
    public static String getDateFormated(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        return dateFormat.format(date);
    }

    /**
     * Este metodo transforma uma string em Data.
     *
     * @param date
     * @return data formatado
     * @since 1.0.0
     */
    public static String getDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        return dateFormat.format(date);
    }

    /**
     * Este metodo transforma uma string em Data.
     *
     * @param dateString
     * @return data formatado
     * @since 1.0.0
     */
    public static Date getDate(String dateString) {
        try {
            DateFormat dateF = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = dateF.parse(dateString);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            return dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Funcao para chamar o whatsapp
     */
    public static void callWhatsapp(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        activity.startActivity(intent);
    }

    /**
     * Funcao para fazer ligacao
     */
    public static void makeCall(Activity activity, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse("tel:" + phone));
        activity.startActivity(intent);
    }

    /**
     * Funcao para compartilhar
     */
    public static void share(Activity activity, StringBuilder strInfo) {
        String mensagem = strInfo.toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);
        activity.startActivity(Intent.createChooser(intent, "Compartilhar por..."));
    }

    public static String formatHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());

        return dateFormat.format(date);
    }

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());

        return dateFormat.format(date);
    }

    public static String formatDatePattern(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

        return dateFormat.format(date);
    }

    public static String formatCompleteHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

        return dateFormat.format(date);
    }
}