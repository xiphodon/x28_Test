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
//  aidl �� android�ӿڶ�������
//Զ�̷���  aidl  ʹ�÷���
//1.��Զ�̷���ķ�����ȡ��һ�������Ľӿ�xxx.java�ļ�
//2.�ѽӿ�xxx.java�ļ��ĺ�׺��Ϊxxx.aidl
//3.��gen�ļ������Զ����ɵ�ͬ��xxx.java�ļ��У���һ����̬������Stub�����Ѿ��̳���Binder�࣬
//      ��ʵ����xxx.java�ӿڣ����������µ��м���
//4.��aidl�ļ����Ƶ������ߵ���Ŀ�У�ע�⣺aidl�ļ������ڰ���������Ҫ�ͱ����÷�����Ŀ��aidl�İ���һ��
//5.�ڵ�������Ŀ�У�ǿת�м��˶���ʱ��ֱ��ʹ��Stub.asInterface(Service)����
//
//
//
public class MainActivity extends Activity {

	PayInterface pi;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Զ�̷�����ʽ����
        Intent intent = new Intent();
        intent.setAction("com.gc.alipay");
        //��
        ServiceConnection conn = new PayServiceConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
        
    }
    
    //���Ӷ���
    class PayServiceConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			//ʹ��aidl���Զ����ɵķ�����ǿת
			pi = Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public void click(View v){
    	//����Զ�̷����֧������
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
