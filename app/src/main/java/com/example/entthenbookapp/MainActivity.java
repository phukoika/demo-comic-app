package com.example.entthenbookapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.entthenbookapp.adapter.ComicAdapter;
import com.example.entthenbookapp.object.Comic;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton btnMenu;
    private FirebaseAuth mAuth;

    private GridView grvComic;
    private ComicAdapter adapter;
    private ArrayList<Comic> comicArrayList;
    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Xử lý sự kiện khi một mục menu được chọn
                if (item.getItemId() == R.id.NAV_SETTING) {
                    
                } else if (item.getItemId() == R.id.NAV_LOGOUT) {
                    mAuth.signOut();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
        init();
        map();
        setUp();
        setClick();

    }
    private void init() {
        comicArrayList = new ArrayList<>();
        comicArrayList.add(new Comic("Cố Nhân Tình - TH","150 Chap","https://i.ytimg.com/vi/nBfrFS7Gg0w/maxresdefault.jpg"));
        comicArrayList.add(new Comic("KHÓC CÙNG EM VER.2 - TH ","125 Chap","https://i.ytimg.com/vi/aNRak4DBCpI/maxresdefault.jpg"));
        comicArrayList.add(new Comic("HẠNH PHÚC ĐÓ EM KHÔNG CÓ VER.2 - TH","99 Chap","https://i.ytimg.com/vi/5e4C7ZG3F_A/maxresdefault.jpg"));
        comicArrayList.add(new Comic("Thay Lòng - TH","","https://i1.sndcdn.com/artworks-pVCrsfBwgDbpl8rd-UE6s1A-t500x500.jpg"));

        adapter = new ComicAdapter(this,0, comicArrayList);
    };
    private void map(){
        grvComic = findViewById(R.id.listComic);
        search = findViewById(R.id.txtSearch);
    };
    private void setUp() {
        grvComic.setAdapter(adapter);
    };
    private void setClick(){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchValue = search.getText().toString();
                adapter.sortComic(searchValue);
            }
        });
    };
}