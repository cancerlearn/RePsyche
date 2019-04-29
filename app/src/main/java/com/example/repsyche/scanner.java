package com.example.repsyche;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanner extends AppCompatActivity {

    private String user_email, user_credit, trash_bin;
    private UserDatabaseHelper mUserDatabaseHelper;
    private BinDatabaseHelper mBinDatabaseHelper;
    private ProductDatabaseHelper mProductDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        user_credit = getIntent().getStringExtra("User_credit");
        user_email = getIntent().getStringExtra("User_email");
        trash_bin = getIntent().getStringExtra("Bin");
        mBinDatabaseHelper = new BinDatabaseHelper(this);
        mUserDatabaseHelper = new UserDatabaseHelper(this);
        mProductDatabaseHelper = new ProductDatabaseHelper(this);
    }


    public void scanNow(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        integrator.setResultDisplayDuration(1);
        integrator.setWide();  // Wide scanning rectangle, may work better for 1D barcodes
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            TextView scan_format = (TextView) findViewById(R.id.scan_format);
            TextView scan_content = (TextView) findViewById(R.id.scan_content);
            scan_format.setText("FORMAT: " + scanFormat);
            scan_content.setText("CONTENT: " + scanContent);

            if (mBinDatabaseHelper.decreaseBinCapacity(trash_bin) && productNotScanned(scanContent) && !scanContent.equals(null)){

                mProductDatabaseHelper.addProduct(scanContent, user_email);
                mUserDatabaseHelper.addUserCredit(user_email, 0.2);
                createAlertBox("Succesful Scan", "0.2 pesewas credited to account.");
            }
            else if (!productNotScanned(scanContent)){
                createAlertBox("Unsuccesful Scan", "Product may have already been scanned.");
            }
            else if (!mBinDatabaseHelper.decreaseBinCapacity(trash_bin)){
                createAlertBox("Unsuccesful Scan", "Bin is full.");
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void createAlertBox(String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNegativeButton(android.R.string.no, null);
        alert.setIcon(android.R.drawable.ic_dialog_alert);
        alert.show();
    }

    public boolean productNotScanned(String scanContent){

        Cursor allData = mProductDatabaseHelper.getData();

        while (allData.moveToNext()){

            if (allData.getString(0).equals(scanContent)){
                return false;
            }

        }

        return true;

    }

}
