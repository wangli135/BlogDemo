package com.xks.camera2;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;

/**
 * Created by Xingfeng on 2016-09-25.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private CameraDevice cameraDevice;

    private ImageReader imageReader;

    public CameraPreview(Context context, CameraDevice cameraDevice) {
        super(context);
        this.cameraDevice = cameraDevice;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.i(MainActivity.TAG, "surfaceCreated");
            initCameraAndPreview();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCameraAndPreview() {

        HandlerThread handlerThread = new HandlerThread("Camera2");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());

        try {
            final CaptureRequest.Builder captureRequest = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequest.addTarget(getHolder().getSurface());
            imageReader = ImageReader.newInstance(getWidth(), getHeight(), ImageFormat.JPEG, 7);
            cameraDevice.createCaptureSession(Arrays.asList(getHolder().getSurface(), imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {

                    captureRequest.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    captureRequest.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    try {
                        session.setRepeatingRequest(captureRequest.build(), new CameraCaptureSession.CaptureCallback() {
                            @Override
                            public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
                                super.onCaptureStarted(session, request, timestamp, frameNumber);
                                Log.i(MainActivity.TAG, "onCaptureStarted");
                            }

                            @Override
                            public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
                                super.onCaptureProgressed(session, request, partialResult);
                                Log.i(MainActivity.TAG, "onCaptureProgressed");

                            }

                            @Override
                            public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
                                super.onCaptureCompleted(session, request, result);
                                Log.i(MainActivity.TAG, "onCaptureCompleted");

                                for (CaptureResult.Key<?> key : result.getKeys()) {

                                    Log.i(MainActivity.TAG, "key:" + key.getName() + " 值：" + result.get(key));

                                }

                                try {
                                    session.stopRepeating();
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
                                super.onCaptureFailed(session, request, failure);
                                Log.i(MainActivity.TAG, "onCaptureFailed");

                            }

                            @Override
                            public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frameNumber) {
                                super.onCaptureSequenceCompleted(session, sequenceId, frameNumber);
                                Log.i(MainActivity.TAG, "onCaptureSequenceCompleted");
                            }

                            @Override
                            public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
                                super.onCaptureSequenceAborted(session, sequenceId);
                                Log.i(MainActivity.TAG, "onCaptureSequenceAborted");

                            }

                            @Override
                            public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface target, long frameNumber) {
                                super.onCaptureBufferLost(session, request, target, frameNumber);
                                Log.i(MainActivity.TAG, "onCaptureBufferLost");

                            }
                        }, handler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {

                }
            }, handler);


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }
}
