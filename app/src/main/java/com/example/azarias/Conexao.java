package com.example.azarias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Conexao {

    static String  MQTTHOST = "ssl://875dd1d154ce451c87c69754f5c03df5.s1.eu.hivemq.cloud:8883";
    static String  USERNAME = "Chander";// Usuário Hive
    static String  PASSWORD = "ChanderF21";// Senha Hive
    MqttConnectOptions options;
    MqttClient client;
    DatabaseReference reference;
    Context context;
    String topic = "topico/1";


    public Conexao(Context context) {
        reference = FirebaseDatabase.getInstance().getReference();
        this.context = context;
        this.topic = topic;
    }

    public boolean networkConnectionStatus() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public boolean mqqtConnection(){

        boolean mqttConnected = false;

        String clientId = "Emulador";
        try{
            client = new MqttClient(
                    MQTTHOST,
                    clientId,
                    new MemoryPersistence());
        } catch (MqttException e){
            e.printStackTrace();
        }
        options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            client.connect(options);
            mqttConnected = true;
        } catch (MqttException e){
            e.printStackTrace();
        }

        return mqttConnected;
    }

    public void subscribe (String topic,int qos){
        try {
            client.subscribe(topic,qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void callback(){
        client.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(context, "Conexão perdida com o servidor", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                reference.child("Mensagens").setValue(new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });
    }

    public void publicar(){
        String message = "HelloWorld";
        try{
            client.publish(
                    topic,
                    message.getBytes(UTF_8),
                    0,
                    false);
        } catch (MqttException e ){
            e.printStackTrace();
        }
    }

}
