package com.itstep.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv1;


    SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            StringBuilder builder =new StringBuilder();
            builder.append(event.sensor.getName()+"\n");
            builder.append("x:"+String.valueOf(event.values[0])+"\n");
            builder.append("y:"+String.valueOf(event.values[1])+"\n");
            builder.append("z:"+String.valueOf(event.values[2])+"\n");

            switch (event.accuracy){
                case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                    builder.append("HIGH");
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                    builder.append("LOW");
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                    builder.append("MEDIUM");
                    break;
                case SensorManager.SENSOR_STATUS_UNRELIABLE:
                    builder.append("UNREALEBLE");
                    break;
            }



            tv1.setText(builder.toString());



           // sensorManager.unregisterListener(eventListener);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorManager sensorManager;

    void initSensors(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

       Sensor s = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

       if(s!=null){
           sensorManager.registerListener(eventListener,s,SensorManager.SENSOR_DELAY_NORMAL);
       }else {
           Toast.makeText(this,"Sensor not found",Toast.LENGTH_LONG).show();
       }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        initSensors();
    }
}
