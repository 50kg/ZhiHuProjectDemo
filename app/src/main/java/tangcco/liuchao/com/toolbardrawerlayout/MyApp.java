package tangcco.liuchao.com.toolbardrawerlayout;

import android.app.Application;

import com.zxy.recovery.core.Recovery;

/**
 * Created by sanji on 2017/1/23.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //initRecovery();


    }

    private void initRecovery() {
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
    }
}
