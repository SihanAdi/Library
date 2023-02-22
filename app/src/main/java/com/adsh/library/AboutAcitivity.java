package com.adsh.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AboutAcitivity extends AppCompatActivity {

    private Button goToWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_acitivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        goToWebsite = findViewById(R.id.btnGoToWebsite);
        goToWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AboutAcitivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed By AD at https://github.com/SihanAdi\n" +
                        "Check my website for more applications:");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AboutAcitivity.this, WebsiteActivity.class);
                        intent.putExtra("url", "https://github.com/SihanAdi");
                        startActivity(intent);
                    }
                });
                builder.create().show();

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}