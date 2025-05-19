package com.example45.parkingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class admin extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar TabLayout y ViewPager2
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // Configurar adaptador para ViewPager2 con solo los primeros 4 tabs
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Configurar TabLayoutMediator para los primeros 4 tabs
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Agregar");
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_agregar));
                    break;
                case 1:
                    tab.setText("Verificar");
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_check_parking));
                    break;
                case 2:
                    tab.setText("Pagos");
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_payments));
                    break;
                case 3:
                    tab.setText("Reservas");
                    tab.setIcon(getResources().getDrawable(R.drawable.ic_reservations));
                    break;
            }
        }).attach();

        // Agregar el quinto tab manualmente (ya que abre una nueva actividad)
        TabLayout.Tab encargadoTab = tabLayout.newTab();
        encargadoTab.setText("Encargado");
        encargadoTab.setIcon(getResources().getDrawable(R.drawable.ic_users));
        tabLayout.addTab(encargadoTab);

        // Agregar listener para manejar el clic en el tab de Encargado
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                // Si se selecciona el tab de Encargado (último tab)
                if (position == 4) {
                    // Iniciar la actividad de Encargado
                    Intent intent = new Intent(admin.this, Encargado.class);
                    startActivity(intent);

                    // Resetear la selección al tab previamente seleccionado
                    tabLayout.selectTab(tabLayout.getTabAt(viewPager.getCurrentItem()));
                } else {
                    // Para otros tabs, solo actualizar el ViewPager
                    viewPager.setCurrentItem(position);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No es necesario implementar
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Manejar si el tab de Encargado es seleccionado nuevamente
                if (tab.getPosition() == 4) {
                    Intent intent = new Intent(admin.this, Encargado.class);
                    startActivity(intent);
                }
            }
        });
    }
}