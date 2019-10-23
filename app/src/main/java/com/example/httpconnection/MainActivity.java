package com.example.httpconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button send;
    private TextView status;

    // ImgProcess
    private Button wordimage;
    private ImageView imgView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);
        status = findViewById(R.id.status);

        // ImgProcess
        wordimage = findViewById(R.id.wordimage);
        imgView = findViewById(R.id.imageView);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://noti-drawer.run.goorm.io/api/analyze-sentence";
                String sentence = "{\"sentence\": \"오늘은 치킨 섭취를 먹구 싶어용.\"}";

                GetNounInSentenceAsync getNoun = new GetNounInSentenceAsync(url, sentence);
                getNoun.execute();

                send.setEnabled(false);
                status.setText("데이터 수신중입니다.");
            }
        });

        // ImgProcess
        wordimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://noti-drawer.run.goorm.io/api/get-wordcloud";
                String sentence = "{\"치킨\": 7,\n" +
                        "\"피자\": 6,\n" +
                        "\"목걸이\": -4,\n" +
                        "\"일본\": -2}";
                GetImageAsync getImg = new GetImageAsync(url, sentence);
                getImg.execute();


                send.setEnabled(false);
                status.setText("데이터 수신중입니다.");

            }
        });

    }

    public class GetNounInSentenceAsync extends AsyncTask<Void, Void, String> {

        String url;
        String sentence;

        // Constructor
        public GetNounInSentenceAsync(String url, String sentence) {
            this.sentence = sentence;
            this.url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            // 비동기 처리 후 결과값을 리턴
            // 이 메소드가 끝난 후에 onPostExecute()가 실행됨
            return new GetNownInSentence().request(url, sentence);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // 서버 통신 후 처리 완료
            // 받아온 값은 result에 저장되며 JSON 타입으로 저장됨
            // 올바르지 못한 값을 받아왔을 때, 빈 String 값이 나옴

            send.setEnabled(true);
            if (result.equals(""))
                status.setText("실패하였습니다.");
            else
                status.setText("완료하였습니다.\n" + result);
        }
    }


    // ImgProcess
    public class GetImageAsync extends AsyncTask<Void, Void, Bitmap> {

        String url;
        String sentence;

        // Constructor
        public GetImageAsync(String url, String sentence) {
            this.sentence = sentence;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            // 비동기 처리 후 결과값을 리턴
            // 이 메소드가 끝난 후에 onPostExecute()가 실행됨
            return new GetNownInSentence().requestImg(url, sentence);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            // 서버 통신 후 처리 완료
            // 받아온 값은 result에 저장되며 JSON 타입으로 저장됨
            // 올바르지 못한 값을 받아왔을 때, 빈 String 값이 나옴



            send.setEnabled(true);
            if (result==null)
                status.setText("실패하였습니다.");
            else {
                status.setText("완료하였습니다.");
                imgView.setImageBitmap(result);
            }

        }
    }



}