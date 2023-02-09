package com.sample.mybeacon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static android.bluetooth.le.ScanSettings.MATCH_MODE_AGGRESSIVE;
import static android.bluetooth.le.ScanSettings.MATCH_NUM_MAX_ADVERTISEMENT;
import static com.sample.mybeacon.Constant.TARGET_DEVICE_NAME;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "PHSWT";

    private Button mBtn_scan;
    private Button mBtn_disconnect;
    private TextView mTextTemp;

    private BluetoothDevice mBluetoothDevice = null;
    private BluetoothGatt mBluetoothDeviceGatt = null;

    private BluetoothGattCharacteristic mOrientationCharacteristic;
    private static final int REQUEST_CODE_BLUETOOTH_SCAN_CONNECT = 11006;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn_scan = findViewById(R.id.btn_scan);
        mTextTemp = findViewById(R.id.text_temp);
        mBtn_disconnect = findViewById(R.id.btn_disconnect);

        mTextTemp.setText("0.0°c");

        mBtn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScan();
            }
        });

        mBtn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disconnectGatt();
            }
        });


    }


    private void startScan() {
        BluetoothManager bleManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bleAdapter = bleManager.getAdapter();
        BluetoothLeScanner bleScanner = bleAdapter.getBluetoothLeScanner();
        ScanSettings scanSettings;
        final ArrayList<ScanFilter> scanFilterArrayList = new ArrayList<>();
        Log.i(TAG, "startScan");

        if (bleScanner == null) {
            Log.i(TAG, "bluetoothLeScanner = null");
        } else {

            Log.i(TAG, "scanning");

            scanFilterArrayList.add(new ScanFilter.Builder().setDeviceName(TARGET_DEVICE_NAME).build());
            scanSettings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .setNumOfMatches(MATCH_NUM_MAX_ADVERTISEMENT)
                    .setMatchMode(MATCH_MODE_AGGRESSIVE)
                    .build();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions(new String[]{
                            Manifest.permission.BLUETOOTH_SCAN,
                            Manifest.permission.BLUETOOTH_CONNECT,
                    }, REQUEST_CODE_BLUETOOTH_SCAN_CONNECT);
                }
                return;
            }
            Log.e(TAG, "result: " );
            bleScanner.startScan(scanFilterArrayList, scanSettings, scanCallback);
        }
    }

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Log.e(TAG, "result: " + result);

            if (result.getDevice().getName() != null && result.getDevice().getName().equals(TARGET_DEVICE_NAME)) {
                final SparseArray<byte[]> manufacturerData = Objects.requireNonNull(result.getScanRecord()).getManufacturerSpecificData();
                Log.d(TAG, "manufacturerData: " + manufacturerData);

                String mDeviceAddress = result.getDevice().getAddress();
                connectGatt(mDeviceAddress);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.e(TAG, "errorCode: " + errorCode );
        }
    };

    private void disconnectGatt() {
        if (mBluetoothDeviceGatt != null) {
            mBluetoothDeviceGatt.close();
            mBluetoothDeviceGatt = null;
            mTextTemp.setText("0.0°c");
            Toast.makeText(this, "Device has been disconnected!", Toast.LENGTH_LONG);
        }
    }

    private final BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            Log.e(TAG, " onConnectionStateChange status: " + status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothGatt.STATE_CONNECTED) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Device has been connected!", Toast.LENGTH_LONG);
                        }
                    });

                    gatt.discoverServices();
                } else {
                    if (mBluetoothDeviceGatt != null) {
                        mBluetoothDeviceGatt.close();
                    }
                }
            } else {
                if (mBluetoothDeviceGatt != null) {
                    mBluetoothDeviceGatt.close();
                }
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            Log.e(TAG, " onServicesDiscovered status: " + status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                List<BluetoothGattService> gattServices = gatt.getServices();
                for (BluetoothGattService service : gattServices) {
                    Log.e(TAG, "services: " + service.getUuid());
                }
                final BluetoothGattService thingyMotionService = gatt.getService(Constant.THINGY_MOTION_SERVICE);
                if (thingyMotionService != null) {

                    //mOrientationCharacteristic = thingyMotionService.getCharacteristic(Constant.ORIENTATION_CHARACTERISTIC);

                    if (mBluetoothDeviceGatt != null) {
                        Log.v(TAG, "Reading environment config chars");
                        final BluetoothGattDescriptor descriptor = mOrientationCharacteristic.getDescriptor(Constant.CLIENT_CHARACTERISTIC_CONFIGURATOIN_DESCRIPTOR);
                        if (descriptor != null) {
                            mBluetoothDeviceGatt.setCharacteristicNotification(thingyMotionService.getCharacteristic(Constant.ORIENTATION_CHARACTERISTIC), true);
                            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            mBluetoothDeviceGatt.writeDescriptor(descriptor);
                        }
                    }

                }
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            Log.e(TAG, "onCharacteristicRead status: " + status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            Log.e(TAG, "onCharacteristicWrite status: " + status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);

            if (characteristic.equals(mOrientationCharacteristic)) {
                String str = byteToHex(characteristic.getValue());
                int color = R.color.white;
                if (str.equals("00")) {
                    setTempValue(getColor(R.color.black));
                } else if (str.equals("01")) {
                    setTempValue(getColor(R.color.teal_200));
                } else if (str.equals("02")) {
                    setTempValue(getColor(R.color.purple_500));
                } else if (str.equals("03")) {
                    setTempValue(getColor(R.color.teal_700));
                }
                Log.e(TAG, "onCharacteristicChanged str: " + str);
//                final int mTemperatureInt = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT8, 0);
//                final int mTemperatureDec = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
//                Log.e(TAG, "onCharacteristicChanged temperature: " + mTemperatureInt + "." + mTemperatureDec + "°c");
//                String mTempTextValue = mTemperatureInt + "." + mTemperatureDec + "°c";


            }
        }

    };

    private void connectGatt(String macAddress) {
        mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(macAddress);
        if (mBluetoothDevice != null && mBluetoothDeviceGatt == null) {
            Log.e(TAG, "connectGatt");
            BluetoothManager bleManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            BluetoothAdapter bleAdapter = bleManager.getAdapter();
            BluetoothLeScanner bleScanner = bleAdapter.getBluetoothLeScanner();

            if (bleScanner != null) {
                bleScanner.stopScan(scanCallback);
            }
            mBluetoothDeviceGatt = mBluetoothDevice.connectGatt(getApplicationContext(), false, mBluetoothGattCallback, BluetoothDevice.TRANSPORT_LE);
        }
    }

    private void setTempValue(final int temp) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mTextTemp.setBackgroundColor(temp);
            }
        });
    }


    private static String byteToHex(byte[] hexString) {
        StringBuilder hexValue = new StringBuilder();
        for (byte b : hexString) {
            hexValue.append(String.format("%02x", b));
        }
        return hexValue.toString();
    }
}