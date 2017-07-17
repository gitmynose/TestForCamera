package com.example.nibikoski.testforcamera002;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Camera;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    SurfaceView cameraView;
    SurfaceHolder surfaceHolder;
    Camera camera;
    ConstraintLayout conLayout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);
        cameraView = (SurfaceView) this.findViewById(R.id.CameraView);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        try
        {
            camera.setPreviewDisplay(holder);
        }
        catch (IOException exception)
        {
            camera.release();
        }
        camera.startPreview();
    }
    
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }
}

