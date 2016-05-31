package com.example.x28_test;

import com.example.x28_alipaytest.PayInterface;
import com.example.x28_alipaytest.PayInterface.Stub;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;


//
//  aidl ： android接口定义语言
//远程服务  aidl  使用方法
//1.把远程服务的方法抽取成一个单独的接口xxx.java文件
//2.把接口xxx.java文件的后缀改为xxx.aidl
//3.在gen文件夹中自动生成的同名xxx.java文件中，有一个静态抽象类Stub，它已经继承了Binder类，
//      并实现了xxx.java接口，这个类就是新的中间人
//4.把aidl文件复制到调用者的项目中，注意：aidl文件的所在包名，必须要和被调用服务项目中aidl的包名一致
//5.在调用者项目中，强转中间人对象时，直接使用Stub.asInterface(Service)方法
//
//
//
public class MainActivity extends Activity {

	PayInterface pi;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //远程服务，隐式启动
        Intent intent = new Intent();
        intent.setAction("com.gc.alipay");
        //绑定
        ServiceConnection conn = new PayServiceConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
        
    }
    
    //连接对象
    class PayServiceConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//使用aidl中自动生成的方法来强转
			pi = Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public void click(View v){
    	//调用远程服务的支付方法
    	try {
			pi.pay();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
