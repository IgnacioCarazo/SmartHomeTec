package com.smarthometec.mobileapp.administration;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.smarthometec.mobileapp.R;
/**
 * @class RegisterRoom
 * Define los diferentes aposentos en la casa donde se ubicarán los dispositivos adquiridos por el usuario.
 * @author JosephJimenez
 */
public class RegisterRoom extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_room);
    }
}