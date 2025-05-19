package com.example45.parkingapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Devolver el Fragment correspondiente según la posición
        switch (position) {
            case 0:
                return new AgregarFragment();
            case 1:
                return new VerificarFragment();
            case 2:
                return new PagosFragment();
            case 3:
                return new ReservasFragment();
            default:
                return new AgregarFragment(); // Por defecto, devolver el primer fragment
        }
    }

    @Override
    public int getItemCount() {
        // Solo devolvemos 4 porque el quinto tab (Encargado) abre una actividad independiente
        return 4;
    }
}