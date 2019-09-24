package com.emdd.emdd_android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class FileActivity extends AppCompatActivity {

    String  basePath ;

    public void toAnnotationDemoPage(View v) {
        startActivity(new Intent(FileActivity.this, AnnotationDemoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        FileActivityPermissionsDispatcher.filePermissionWithPermissionCheck(this);
//            FileActivityPermissionsDispatcher.filePermissionWithCheck(this);
    }

    private  void writeToFile ()  {
        basePath  = Environment.getExternalStorageDirectory().getAbsolutePath();
        File filePath  = new File (basePath + File.separator +"/chenwei");
        if(!filePath.exists()){
            filePath.mkdir();
        }
        FileStorageTools.getInstance(this).putStringToExternalStorage(
                "test",
                new File (basePath + File.separator +"/chenwei"),
                "a.txt",
                true

        );
    }
    private void read (){
        writeToFile();
        readFromFile ();
    }
    private void readFromFile (){
        String path  = basePath +File.separator+ "/chenwei/a.txt";
        File file  = new File (path);
        if(!file.exists()) {
            System.out.println("===========================文件不存在");
            return ;
        }
        List<String> resList  = FileStorageTools.getInstance(this)
                .getStringFromExternalStorage(path) ;
        if(resList==null) return ;
        StringBuffer stringBuffer   =  new StringBuffer()  ;
        for (int i =0; i < resList.size(); i ++){
            stringBuffer.append("------------" + resList.get(i));
        }
        System.out.println("===========================文件中的内容为： "+stringBuffer.toString());

    }

//    private void read() {
//        File sdDir = null;
//        //判断sd卡是否存在
//        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
//        if (sdCardExist) {
//            //获得SD卡根目录路径
//            sdDir = Environment.getExternalStorageDirectory();
//            String sdpath = sdDir.getAbsolutePath();
//            //获取指定图片路径
////            String filepath = sdpath + File.separator +"hahaha1"+File.separator+ "cll_file1.txt";
////           File file  = new File(filepath);
////            if(! file.exists()) file.mkdir();
////            filepath  += File.separator+ "cll_file1.txt";
////            File file = new File(filepath);
////            if (file.exists()) {
////                try {
////                    FileInputStream in = new FileInputStream(file);
////
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                }
////                Bitmap bm = BitmapFactory.decodeFile(filepath);
////                // 将图片显示到ImageView中
////                imageView.setImageBitmap(bm);
////                ll.addView(imageView);}
//            String filepath  ="a.txt";
//            try {
//                String content = "//                Bitmap bm = BitmapFactory.decodeFile(filepath);\n" +
//                        "//                // 将图片显示到ImageView中\n" +
//                        "//                imageView.setImageBitmap(bm);\n" +
//                        "//                ll.addView(imageView);}";
//
//                FileStorageTools.getInstance(this)
//                        .putDataToInternalStorage(content.getBytes(), "hello.txt",
//                                MODE_PRIVATE);
//
////                FileStorageTools.getInstance(this).putStringToExternalStorage (
////                        content, new File(sdpath+"/hello"), "hello.txt", true
////                );
////                writeFile (this, filepath, content);
////                readFile(this, filepath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 写数据
     *
     * @param mContext 上下文
     * @param fileName 文件名
     * @param writestr 写入文件的字符串
     * @throws IOException
     */
    public static void writeFile(Context mContext, String fileName, String writestr) throws IOException {
        try {
            File file  = new File (fileName);


            System.out.println("file.exists()-----"+file.exists());

            //创建流文件写出类
            FileOutputStream fout = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            //获取流的字符数
            byte[] bytes = writestr.getBytes();
            //写出流,保存在文件fileName中
            fout.write(bytes);
            //关闭流
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读数据
     *
     * @param mContext 上下文
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static String readFile(Context mContext, String fileName) throws IOException {
        String res = "";
        try { File file  = new File (fileName);
            if(file.exists()) {
                //创建流文件读入类
                FileInputStream fin  = mContext.openFileInput(fileName);
                //通过available方法取得流的最大字符数
                byte[] buffer = new byte[fin.available()];
                //读入流,保存在byte数组
                fin.read(buffer);
                res = new String(buffer, Charset.forName("UTF-8"));
                System.out.println("文件中的内容为： " + res);
//            res = EncodingUtils.getString(buffer, "UTF-8");
                //关闭流
                fin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FileActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void filePermission() {
        read();
    }
//
//    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void filePermission() {
//
//        read();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        FileActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
}
