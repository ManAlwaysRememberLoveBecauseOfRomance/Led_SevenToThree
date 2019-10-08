/*此源码为七色灯转三色灯*/
package com.example.elcapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.Toast;
import com.example.myapplication.R;


public class MainActivity extends Activity implements OnSeekBarChangeListener{

    private SeekBar seekBar_red,seekBar_blue,seekBar_green;
    private Switch mSwitch;
    private static final int seek_red=0xa1;
    private static final int seek_green=0xa2;
    private static final int seek_blue=0xa3;
    private static final int seek_red_left=0xb1;
    private static final int seek_green_left=0xb2;
    private static final int seek_blue_left=0xb3;
    private static final int led_off=20;
    private static final int led_on=1;
    private static final int led_red=2;
    private static final int led_blue=3;
    private static final int led_green=4;
    private static final int led_red_left=9;
    private static final int led_blue_left=10;
    private static final int led_green_left=11;
    private int brightness;
    private int sb=1;
    private static int ledCtlFlag=led_red;
    static Handler mhandler = new Handler();
    private static boolean ledrunflag=true;
    private static Runnable mTask = new Runnable() {       //Main函数中的循环，与开机广播不同。
        public void run() {
            if (ledrunflag = true) {
                if (ledCtlFlag == led_red) {
                    ledCtlFlag = led_blue;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_red, 10);
                    jnielc.ledseek(seek_red_left, 10);
                    jnielc.seekstop();
                } else if (ledCtlFlag == led_blue) {
                    ledCtlFlag = led_green;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_blue, 10);
                    jnielc.ledseek(seek_blue_left, 10);
                    jnielc.seekstop();
                } else if (ledCtlFlag == led_green) {
                    ledCtlFlag = led_red;
                    jnielc.seekstart();
                    jnielc.ledseek(seek_green, 10);
                    jnielc.ledseek(seek_green_left, 10);
                    jnielc.seekstop();
                }
                mhandler.postDelayed(mTask, 5000);
            }
        }
    };
    private static final String pwm1 = "ledbrightness_zx";
    private static final String pwm2 = "ledbrightness_ml";
    private static final String led_right = "ledcolor_kp";
    private static final String led_left = "ledcolor_rd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar_red=(SeekBar) findViewById(R.id.SeekBar_red);
        seekBar_blue=(SeekBar) findViewById(R.id.SeekBar_blue);
        seekBar_green=(SeekBar) findViewById(R.id.SeekBar_green);
        seekBar_red.setOnSeekBarChangeListener(this);
        seekBar_blue.setOnSeekBarChangeListener(this);
        seekBar_green.setOnSeekBarChangeListener(this);
        mSwitch=(Switch) findViewById(R.id.switch1);
        Log.i("mled","get_led_color(led_right)="+get_led_color(led_right));
        Log.i("mled","get_led_color(led_left)="+get_led_color(led_left));
        Log.i("mled","get_led_brightness(pwm1)="+get_led_brightness(pwm1));
        Log.i("mled","get_led_brightness(pwm2)="+get_led_brightness(pwm2));
        switch (get_led_color(led_right)) {
            case led_red:
                seekBar_red.setProgress(get_led_brightness(pwm1));
                seekBar_blue.setProgress(0);
                seekBar_green.setProgress(0);
                break;
            case led_blue:
                seekBar_blue.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_green.setProgress(0);
                break;
            case led_green:
                seekBar_green.setProgress(get_led_brightness(pwm1));
                seekBar_red.setProgress(0);
                seekBar_blue.setProgress(0);
                break;
            default:
                break;
        }
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    set_led_color(led_on,led_right);
                    set_led_color(led_on,led_left);
                        mhandler.post(mTask);
                        ledrunflag=true;
                        sb=1;
                        seekBar_green.setProgress(0);
                        seekBar_red.setProgress(0);
                        seekBar_blue.setProgress(0);
                        /*Intent intent = new Intent("android.intent.action.ledctl");
                        intent.putExtra("led", led_on);
                        intent.putExtra("ledbrightness", 50);
                        sendBroadcast(intent);*/
                }else {
                    if(get_led_color(led_right)==led_on || get_led_color(led_left)==led_on ) {
                        set_led_color(led_off,led_right);
                        set_led_color(led_off,led_left);
                        mhandler.removeCallbacks(mTask);
                        ledrunflag = false;
                        /*Intent intent1 = new Intent("android.intent.action.ledctl");
                        intent1.putExtra("led", led_off);
                        sendBroadcast(intent1);*/
                        seekBar_green.setProgress(0);
                        seekBar_red.setProgress(0);
                        seekBar_blue.setProgress(0);
                        jnielc.seekstart();
                        if (sb==1){
                            jnielc.ledoff();
                        }
                        jnielc.seekstop();
                    }
                }
            }

        });

    }

    //数值改变
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        // TODO Auto-generated method stub
            if (seekBar == seekBar_red) {
                jnielc.ledseek(seek_red, progress);
                jnielc.ledseek(seek_red_left, progress);
                brightness=progress;
            }
            if (seekBar == seekBar_green) {
                jnielc.ledseek(seek_green, progress);
                jnielc.ledseek(seek_green_left, progress);
                brightness=progress;
            }
            if (seekBar == seekBar_blue) {
                jnielc.ledseek(seek_blue, progress);
                jnielc.ledseek(seek_blue_left, progress);
                brightness=progress;
            }
    }

    //开始拖动
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        jnielc.seekstart();
        ledrunflag=false;
    }

    //停止拖动
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        jnielc.seekstop();
       if(seekBar == seekBar_red){
           sb=0;
           set_led_brightness(brightness,pwm1);
           set_led_color(led_red,led_right);
           set_led_color(led_red_left,led_left);
           seekBar_blue.setProgress(0);
           seekBar_green.setProgress(0);
       }
        if (seekBar == seekBar_green) {
           sb=0;
            set_led_brightness(brightness,pwm1);
            set_led_color(led_green,led_right);
            set_led_color(led_green_left,led_left);
            seekBar_blue.setProgress(0);
            seekBar_red.setProgress(0);
        }
        if (seekBar == seekBar_blue) {
           sb=0;
            set_led_brightness(brightness,pwm1);
            set_led_color(led_blue,led_right);
            set_led_color(led_blue_left,led_left);
            seekBar_green.setProgress(0);
            seekBar_red.setProgress(0);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwitch.isChecked()) {
                    mSwitch.setChecked(false);
                    mhandler.removeCallbacks(mTask);
                }
            }
        }, 500);
    }

    private void set_led_color(int freq,String name){
        SharedPreferences save_par = getSharedPreferences("addata", 0);
        SharedPreferences.Editor save_editor = save_par.edit();
        save_editor.putString(name, String.valueOf(freq));
        save_editor.commit();
    }

    private int get_led_color(String name){
        int value = 0;
        try {
            SharedPreferences save_par = getSharedPreferences("addata", 0);
            value = Integer.parseInt(save_par.getString(name, "0"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return value;
    }

    private void set_led_brightness (int freq,String name){
        SharedPreferences save_par = getSharedPreferences("addata", 0);
        SharedPreferences.Editor save_editor = save_par.edit();
        save_editor.putString(name, String.valueOf(freq));
        save_editor.commit();
    }

    private int get_led_brightness (String name){
        int value = 0;
        try {
            SharedPreferences save_par = getSharedPreferences("addata", 0);
            value = Integer.parseInt(save_par.getString(name, "0"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return value;
    }
}