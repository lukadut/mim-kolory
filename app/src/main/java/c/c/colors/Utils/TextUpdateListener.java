package c.c.colors.Utils;

import android.text.Editable;
import android.text.TextWatcher;

 public class TextUpdateListener implements TextWatcher {
   static boolean locked = false;
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    /**
     * funkcja wywoływana przy zmianie tekstu w polu
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().matches("\\d\\.0*")){
            return;
        }
        // zabezpieczenie przed zapętleniem programu
      if(locked || (count == 0 && start <= before )){
         return;
      }
       locked=true;
       action();
       locked=false;
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    /**
     * wywoływana akcja, nadpisana przy ustawianiu listenera
     */
    public void action(){
        locked=false;
    }
}
