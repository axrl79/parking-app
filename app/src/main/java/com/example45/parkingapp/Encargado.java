package com.example45.parkingapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Encargado extends AppCompatActivity {

    private TextInputEditText etNombre, etEmail, etPassword;
    private TextInputLayout tilNombre, tilEmail, tilPassword;
    private RadioGroup rgRol;
    private Button btnRegistrarEmpleado, btnViewEmployees, btnDailyReport, btnMonthlyReport, btnBack;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Inicializar campos
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        tilNombre = findViewById(R.id.tilNombre);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);

        rgRol = findViewById(R.id.rgRol);

        btnRegistrarEmpleado = findViewById(R.id.btnRegistrarEmpleado);
        btnViewEmployees = findViewById(R.id.btnViewEmployees);
        btnDailyReport = findViewById(R.id.btnDailyReport);
        btnMonthlyReport = findViewById(R.id.btnMonthlyReport);
        btnBack = findViewById(R.id.btnBack);

        btnRegistrarEmpleado.setOnClickListener(v -> registerEmployee());

        btnBack.setOnClickListener(v -> finish());
    }

    private void registerEmployee() {
        tilNombre.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);

        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        boolean isValid = true;

        if (TextUtils.isEmpty(nombre)) {
            tilNombre.setError("El nombre es obligatorio");
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            tilEmail.setError("El correo electrónico es obligatorio");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Correo electrónico inválido");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("La contraseña es obligatoria");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("La contraseña debe tener al menos 6 caracteres");
            isValid = false;
        }

        int selectedRoleId = rgRol.getCheckedRadioButtonId();
        if (selectedRoleId == -1) {
            Toast.makeText(this, "Debe seleccionar un rol", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (isValid) {
            RadioButton selectedRadioButton = findViewById(selectedRoleId);
            String role = selectedRadioButton.getText().toString();

            // Crear usuario en Firebase Authentication
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                String uid = firebaseUser.getUid();

                                // Datos del empleado
                                Map<String, Object> empleado = new HashMap<>();
                                empleado.put("uid", uid);
                                empleado.put("nombre", nombre);
                                empleado.put("email", email);
                                empleado.put("rol", role);

                                // Guardar en Realtime Database
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(uid)
                                        .setValue(empleado)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(this, "Empleado registrado correctamente", Toast.LENGTH_LONG).show();
                                            etNombre.setText("");
                                            etEmail.setText("");
                                            etPassword.setText("");
                                            rgRol.clearCheck();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(this, "Error al guardar en la base de datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }
                        } else {
                            Toast.makeText(this, "Error al registrar usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
