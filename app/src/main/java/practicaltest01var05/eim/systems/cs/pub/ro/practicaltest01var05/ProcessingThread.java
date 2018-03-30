package practicaltest01var05.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.sql.Date;

/**
 * Created by monica on 30.03.2018.
 */

public class ProcessingThread extends Thread {
    private int sum;
    private Context context = null;

    public ProcessingThread(Context context, int sum) {
        this.sum = sum;
        this.context = context;
    }
    @Override
    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("service", String.valueOf(sum));

        Intent intent = new Intent();
        intent.setAction("action");
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + sum);
        context.sendBroadcast(intent);
    }
}
