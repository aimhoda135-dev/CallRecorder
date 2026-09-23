package com.example.callrecorder;

import android.media.MediaRecorder;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public class CallRecorder {

    private MediaRecorder mediaRecorder;
    private String audioFilePath;
    private boolean isRecording = false;

    public void startRecording() {
        audioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() 
                + "/CallRecord_" + System.currentTimeMillis() + ".mp4";

        mediaRecorder = new MediaRecorder();
        
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setOutputFile(audioFilePath);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (isRecording && mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaRecorder = null;
            isRecording = false;
        }
    }
}
