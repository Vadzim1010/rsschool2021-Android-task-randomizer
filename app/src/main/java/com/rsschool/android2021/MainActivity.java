package com.rsschool.android2021;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragmentButtonListener, SecondFragmentButtonListener {

    private static int savedData = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }


    private void openFirstFragment(int previousNumber) {
        final FirstFragment firstFragment = FirstFragment.newInstance(previousNumber);
        firstFragment.setFirstFragmentButtonListener(this);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final SecondFragment secondFragment = SecondFragment.newInstance(min, max);
        secondFragment.setSecondFragmentButtonListener(this);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }


    @Override
    public void onSecondFragmentButtonListener(int previousValue) {
        openFirstFragment(previousValue);
    }

    @Override
    public void onFirstFragmentButtonListener(int min, int max) {
        openSecondFragment(min, max);
    }
}
