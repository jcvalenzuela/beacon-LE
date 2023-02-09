package com.sample.mybeacon;

import android.Manifest;
import android.os.ParcelUuid;

import java.util.UUID;

public class Constant {
    public static final UUID THINGY_BASE_UUID                                                   = new UUID(0xEF6801009B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_CONFIGURATION_SERVICE                                       = new UUID(0xEF6801009B354933L, 0x9B1052FFA9740042L);
    public static final UUID DEVICE_NAME_CHARACTERISTIC_UUID                                    = new UUID(0xEF6801019B354933L, 0x9B1052FFA9740042L);
    public static final UUID ADVERTISING_PARAM_CHARACTERISTIC_UUID                              = new UUID(0xEF6801029B354933L, 0x9B1052FFA9740042L);
    public static final UUID APPEARANCE_CHARACTERISTIC_UUID                                     = new UUID(0xEF6801039B354933L, 0x9B1052FFA9740042L);
    public static final UUID CONNECTION_PARAM_CHARACTERISTIC_UUID                               = new UUID(0xEF6801049B354933L, 0x9B1052FFA9740042L);
    public static final UUID EDDYSTONE_URL_CHARACTERISTIC_UUID                                  = new UUID(0xEF6801059B354933L, 0x9B1052FFA9740042L);
    public static final UUID CLOUD_TOKEN_CHARACTERISTIC_UUID                                    = new UUID(0xEF6801069B354933L, 0x9B1052FFA9740042L);
    public static final UUID FIRMWARE_VERSION_CHARACTERISTIC_UUID                               = new UUID(0xEF6801079B354933L, 0x9B1052FFA9740042L);
    public static final UUID MTU_CHARACERISTIC_UUID                                             = new UUID(0xEF6801089B354933L, 0x9B1052FFA9740042L);
    public static final UUID NFC_CHARACTERISTIC_UUID                                            = new UUID(0xEF6801099B354933L, 0x9B1052FFA9740042L);

    public static final UUID BATTERY_SERVICE                                                    = UUID.fromString("0000180F-0000-1000-8000-00805f9b34fb");
    public static final UUID BATTERY_SERVICE_CHARACTERISTIC                                     = UUID.fromString("00002A19-0000-1000-8000-00805f9b34fb");

    public static final UUID THINGY_ENVIRONMENTAL_SERVICE                                       = new UUID(0xEF6802009B354933L, 0x9B1052FFA9740042L);
    public static final UUID TEMPERATURE_CHARACTERISTIC                                         = new UUID(0xEF6802019B354933L, 0x9B1052FFA9740042L);
    public static final UUID PRESSURE_CHARACTERISTIC                                            = new UUID(0xEF6802029B354933L, 0x9B1052FFA9740042L);
    public static final UUID HUMIDITY_CHARACTERISTIC                                            = new UUID(0xEF6802039B354933L, 0x9B1052FFA9740042L);
    public static final UUID AIR_QUALITY_CHARACTERISTIC                                         = new UUID(0xEF6802049B354933L, 0x9B1052FFA9740042L);
    public static final UUID COLOR_CHARACTERISTIC                                               = new UUID(0xEF6802059B354933L, 0x9B1052FFA9740042L);
    public static final UUID CONFIGURATION_CHARACTERISTIC                                       = new UUID(0xEF6802069B354933L, 0x9B1052FFA9740042L);

    public static final UUID THINGY_UI_SERVICE                                                  = new UUID(0xEF6803009B354933L, 0x9B1052FFA9740042L);
    public static final UUID LED_CHARACTERISTIC                                                 = new UUID(0xEF6803019B354933L, 0x9B1052FFA9740042L);
    public static final UUID BUTTON_CHARACTERISTIC                                              = new UUID(0xEF6803029B354933L, 0x9B1052FFA9740042L);

    public static final UUID THINGY_MOTION_SERVICE                                              = new UUID(0xEF6804009B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_MOTION_CONFIGURATION_CHARACTERISTIC                         = new UUID(0xEF6804019B354933L, 0x9B1052FFA9740042L);
    public static final UUID TAP_CHARACTERISTIC                                                 = new UUID(0xEF6804029B354933L, 0x9B1052FFA9740042L);
    public static final UUID ORIENTATION_CHARACTERISTIC                                         = new UUID(0xEF6804039B354933L, 0x9B1052FFA9740042L);
    public static final UUID QUATERNION_CHARACTERISTIC                                          = new UUID(0xEF6804049B354933L, 0x9B1052FFA9740042L);
    public static final UUID PEDOMETER_CHARACTERISTIC                                           = new UUID(0xEF6804059B354933L, 0x9B1052FFA9740042L);
    public static final UUID RAW_DATA_CHARACTERISTIC                                            = new UUID(0xEF6804069B354933L, 0x9B1052FFA9740042L);
    public static final UUID EULER_CHARACTERISTIC                                               = new UUID(0xEF6804079B354933L, 0x9B1052FFA9740042L);
    public static final UUID ROTATION_MATRIX_CHARACTERISTIC                                     = new UUID(0xEF6804089B354933L, 0x9B1052FFA9740042L);
    public static final UUID HEADING_CHARACTERISTIC                                             = new UUID(0xEF6804099B354933L, 0x9B1052FFA9740042L);
    public static final UUID GRAVITY_VECTOR_CHARACTERISTIC                                      = new UUID(0xEF68040A9B354933L, 0x9B1052FFA9740042L);

    public static final UUID THINGY_SOUND_SERVICE                                               = new UUID(0xEF6805009B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_SOUND_CONFIG_CHARACTERISTIC                                 = new UUID(0xEF6805019B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_SPEAKER_DATA_CHARACTERISTIC                                 = new UUID(0xEF6805029B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_SPEAKER_STATUS_CHARACTERISTIC                               = new UUID(0xEF6805039B354933L, 0x9B1052FFA9740042L);
    public static final UUID THINGY_MICROPHONE_CHARACTERISTIC                                   = new UUID(0xEF6805049B354933L, 0x9B1052FFA9740042L);

    public static final UUID CLIENT_CHARACTERISTIC_CONFIGURATOIN_DESCRIPTOR                     = new UUID(0x0000290200001000L, 0x800000805f9B34FBL);

    public final static ParcelUuid PARCEL_SECURE_DFU_SERVICE                                    = ParcelUuid.fromString("0000FE59-0000-1000-8000-00805F9B34FB");
    public final static UUID SECURE_DFU_SERVICE                                                 = UUID.fromString("0000FE59-0000-1000-8000-00805F9B34FB");
    public static final UUID THINGY_BUTTONLESS_DFU_SERVICE                                      = new UUID(0x8E400001F3154F60L, 0x9FB8838830DAEA50L);
    public static final UUID DFU_DEFAULT_CONTROL_POINT_CHARACTERISTIC                           = new UUID(0x8EC90001F3154F60L, 0x9FB8838830DAEA50L);
    public static final UUID DFU_CONTROL_POINT_CHARACTERISTIC_WITHOUT_BOND_SHARING              = new UUID(0x8EC90003F3154F60L, 0x9FB8838830DAEA50L);

    public static final String TARGET_DEVICE_NAME = "PHSWT125";
    public static final int FORMAT_SINT8 = 0x21;
    public static final int FORMAT_UINT8 = 0x11;
    public static final String[] BLUETOOTH_PERMISSIONS_S = { Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT} ;
}
