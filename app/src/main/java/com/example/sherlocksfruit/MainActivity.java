package com.example.sherlocksfruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sherlocksfruit.Helper.InternetConnectionChecker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    Button browseimage;
    Button takepic;
    Button recognizeImage;
    TextView txt;
    AlertDialog waitDialog;



    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView=findViewById(R.id.image_view);
        takepic=findViewById(R.id.takepic_button);
        browseimage=findViewById(R.id.browse_button);
        recognizeImage=findViewById(R.id.recognize_button);
        txt=findViewById(R.id.textView);



        browseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String [] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else
                    {
                        pickImage();
                    }
                }
                else
                {
                    pickImage();
                }
            }
        });

        takepic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        /*recognizeImage.setOnClickListener(view{
            @Override
            public void onClick(View v){

                BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
                Bitmap bitmp = drawable.getBitmap();
                recognize(bitmp);


            }
        });*/
        recognizeImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
                Bitmap bitmp = drawable.getBitmap();
                recognize(bitmp);
            }
        });

    }



    private void pickImage()
    {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    pickImage();
                }
                else{
                    Toast.makeText(this, "Permission denied!!!", Toast.LENGTH_SHORT).show();

                }
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
                    imgView.setImageURI(data.getData());

                }
                else {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imgView.setImageBitmap(bitmap);

                }

        }

    private void recognize(Bitmap bitmap){
        final FirebaseVisionImage image= FirebaseVisionImage.fromBitmap(bitmap);
        new InternetConnectionChecker(new InternetConnectionChecker.Consumer() {
            @Override
            public void accept(boolean internet) {
                if (internet){
                    FirebaseVisionCloudImageLabelerOptions options=
                            new FirebaseVisionCloudImageLabelerOptions.Builder().setConfidenceThreshold(0.8f)

                            .build();

                    FirebaseVisionImageLabeler detector= FirebaseVision.getInstance().getCloudImageLabeler(options);
                    detector.processImage(image)
                            .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                                @Override
                                public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                                    processDefaultRes(firebaseVisionImageLabels);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("EDMTERROR",e.getMessage());
                                }
                            });

                }
                else{
                    FirebaseVisionOnDeviceImageLabelerOptions options=
                            new FirebaseVisionOnDeviceImageLabelerOptions.Builder().setConfidenceThreshold(0.8f)

                                    .build();

                    FirebaseVisionImageLabeler detector= FirebaseVision.getInstance().getOnDeviceImageLabeler(options);

                    detector.processImage(image)
                            .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                                @Override
                                public void onSuccess(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
                                    processDefaultResDevice(firebaseVisionImageLabels);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("EDMTERROR",e.getMessage());
                                }
                            });
                }
            }
        });
    }

    private void processDefaultResDevice(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
        for (FirebaseVisionImageLabel label:firebaseVisionImageLabels){
            Toast.makeText(this,"Cloud Result: "+label.getText(),Toast.LENGTH_SHORT);
            //txt.setText("Result: "+label.getText());
        }
        if(waitDialog.isShowing()){
            waitDialog.dismiss();
        }


    }

    private void processDefaultRes(List<FirebaseVisionImageLabel> firebaseVisionImageLabels) {
        for (FirebaseVisionImageLabel label:firebaseVisionImageLabels){
            Toast.makeText(this,"Cloud Result: "+label.getText(),Toast.LENGTH_SHORT);
            //txt.setText("Result: "+label.getText());

        }
        if(waitDialog.isShowing()){
            waitDialog.dismiss();
        }
    }


}
