package com.example45.parkingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    // Declaración de vistas
    private ConstraintLayout mainLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView ivProfileIcon;
    private ViewPager2 viewPager;
    private FrameLayout homeContentContainer;
    private CardView cardMainBanner;
    private TextView tvBannerTitle, tvBannerSubtitle, tvParkingTitle, tvViewAll;
    private MaterialButton btnReserveNow;
    private RecyclerView rvParkingSpots;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Aplicar padding por insets del sistema (barra de estado, navegación, etc.)
        mainLayout = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas
        appBarLayout = findViewById(R.id.appBarLayout);
        toolbar = findViewById(R.id.toolbar);
        ivProfileIcon = findViewById(R.id.ivProfileIcon);
        viewPager = findViewById(R.id.viewPager);
        homeContentContainer = findViewById(R.id.homeContentContainer);
        cardMainBanner = findViewById(R.id.cardMainBanner);
        tvBannerTitle = findViewById(R.id.tvBannerTitle);
        tvBannerSubtitle = findViewById(R.id.tvBannerSubtitle);
        btnReserveNow = findViewById(R.id.btnReserveNow);
        tvParkingTitle = findViewById(R.id.tvParkingTitle);
        tvViewAll = findViewById(R.id.tvViewAll);
        rvParkingSpots = findViewById(R.id.rvParkingSpots);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Opcional: agregar listeners, cambiar textos, etc.
        btnReserveNow.setOnClickListener(v -> {
            // Acción al presionar "Reservar ahora"
        });

        tvViewAll.setOnClickListener(v -> {
            // Acción al presionar "Ver todos"
        });
    }
}
