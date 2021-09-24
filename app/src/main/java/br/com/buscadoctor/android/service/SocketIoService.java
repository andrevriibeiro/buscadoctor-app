package br.com.buscadoctor.android.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.net.URISyntaxException;
import java.util.List;

import br.com.buscadoctor.android.R;
import br.com.buscadoctor.android.activity.AgendaActivity;
import br.com.buscadoctor.android.activity.MainActivity;
import br.com.buscadoctor.android.manager.ConsultaManager;
import br.com.buscadoctor.android.model.Consulta;
import br.com.buscadoctor.android.model.dto.HourDTO;
import br.com.buscadoctor.android.service.listener.ConsultaServiceListener;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * @author Guilherme Mendes
 * @version 1.0.0
 * @since 1.0.0
 */
public class SocketIoService extends Service {

    private int threadId;
    private boolean newRun = true;
    public static Socket mSocket;

    public static int userId = 1;

    public static String userNome = "Guilherme";

    {
        try {
            mSocket = IO.socket("https://murmuring-chamber-91453.herokuapp.com/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Socket getSocket() {
        return mSocket;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSocket.connect();

        mSocket.emit("pacienteDados", userId, userNome);
        mSocket.on("conectado", statusConexao);
        mSocket.on("notification", notifica);
        mSocket.on("agendandoTo", agendamento);
        mSocket.on("agendandoToCancel", cancelamento);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (threadId == startId) {
            threadId = startId;
            newRun = false;
        }
        return (START_STICKY);
    }

    private Emitter.Listener statusConexao = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            boolean b = (boolean) args[0];
        }
    };

    private Emitter.Listener notifica = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (args.length > 2) {
                int id = (int) args[2];
                ConsultaManager consultaManager = new ConsultaManager(getApplicationContext());
                consultaManager.getConsulta(id, new ConsultaServiceListener() {
                    @Override
                    public void onSuccess(Consulta consulta) {
                        notification(consulta);
                    }

                    @Override
                    public void onSuccess(List<HourDTO> hourDTOList) {
                    }

                    @Override
                    public void onFail(Throwable t) {
                        Log.d("", "");
                    }
                });
            }
        }
    };

    private Emitter.Listener agendamento = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("","");
            Intent intent = new Intent();
            intent.setAction("com.courrier.novaRoom2User");
            intent.putExtra("novaRoom2User", "foi");
            sendBroadcast(intent);
        }
    };

    private Emitter.Listener cancelamento = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("","");
        }
    };

    public void notification(Consulta consulta) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setPriority(2);
        builder.setTicker("text");
        builder.setContentTitle("test");
        builder.setContentText("text");
        builder.setSmallIcon(R.mipmap.ic_birthday);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_birthday));

        Intent resultIntent = new Intent(this, AgendaActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        nm.notify(R.mipmap.ic_birthday, n);
        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Worker extends Thread {

        public int count;
        public int startId;
        public boolean active;

        public Worker(int startId) {
            this.startId = startId;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                Log.e("TESTE", "Testando a service do socket IO");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("NeverKill"));
    }
}