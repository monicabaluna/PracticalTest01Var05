package practicaltest01var05.eim.systems.cs.pub.ro.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var05Service extends Service {

    private ProcessingThread processingThread = null;
    public PracticalTest01Var05Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int sum = intent.getIntExtra("sum", 0);
        processingThread = new ProcessingThread(this, sum);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
