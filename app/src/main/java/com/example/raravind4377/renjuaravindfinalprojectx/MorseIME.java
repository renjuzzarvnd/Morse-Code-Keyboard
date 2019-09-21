package com.example.raravind4377.renjuaravindfinalprojectx;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by raravind4377 on 12/6/2018.
 */

public class MorseIME  extends InputMethodService
            implements KeyboardView.OnKeyboardActionListener {

        private KeyboardView kv;
        private Keyboard keyboard;

        private boolean caps = false;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }
    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

       HashMap<String, String> keyCodeMap = new HashMap<String, String>();
        keyCodeMap.put("5001",".----");
        keyCodeMap.put("5002","..---");
        keyCodeMap.put("5003","...--");
        keyCodeMap.put("5004","....-");
        keyCodeMap.put("5005",".....");
        keyCodeMap.put("5006","-....");
        keyCodeMap.put("5007","--...");
        keyCodeMap.put("5008","---..");
        keyCodeMap.put("5009","----.");
        keyCodeMap.put("5000","-----");

        keyCodeMap.put("6001",".-");
        keyCodeMap.put("6002","-...");
        keyCodeMap.put("6003","-.-.");
        keyCodeMap.put("6004","-..");
        keyCodeMap.put("6005",".");
        keyCodeMap.put("6006","..-.");
        keyCodeMap.put("6007","--.");
        keyCodeMap.put("6008","....");
        keyCodeMap.put("6009","..");
        keyCodeMap.put("6010",".---");
        keyCodeMap.put("6011","-.-");
        keyCodeMap.put("6012",".-..");
        keyCodeMap.put("6013","--");
        keyCodeMap.put("6014","-.");
        keyCodeMap.put("6015","---");
        keyCodeMap.put("6016",".--.");
        keyCodeMap.put("6017","--.-");
        keyCodeMap.put("6018",".-.");
        keyCodeMap.put("6019","...");
        keyCodeMap.put("6020","-");
        keyCodeMap.put("6021","..-");
        keyCodeMap.put("6022","...-");
        keyCodeMap.put("6023",".--");
        keyCodeMap.put("6024","-..-");
        keyCodeMap.put("6025","-.--");
        keyCodeMap.put("6026","--..");
        keyCodeMap.put("7000"," ");


       /*  String[] alpha = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z" };
*/
         String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",};

     //    HashMap<String, String> keyCodeMap = new HashMap<String,String>();


/*

int j=0;
int k=6000;
            for (int i = 1; i <=27  ; i++) {
                keyCodeMap.put(""+k, morse[j]);
                k++;
j++;
            }

*/

        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                ic.commitText(keyCodeMap.get(String.valueOf(primaryCode)),1);
        }
    }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeUp() {
        }


}
