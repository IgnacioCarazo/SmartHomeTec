package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.smarthometec.mobileapp.R;
/**
 * @class RegisterDevice
 * Permite agregar un dispositivo de forma manual al usuario
 * @author JosephJimenez
 */
public class RegisterDevice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_device);
    }
}