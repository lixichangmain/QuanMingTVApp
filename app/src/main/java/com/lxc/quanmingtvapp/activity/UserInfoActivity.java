package com.lxc.quanmingtvapp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxc.quanmingtvapp.R;
import com.lxc.quanmingtvapp.application.MyApplication;
import com.lxc.quanmingtvapp.bean.User;
import com.lxc.quanmingtvapp.fragment.MineFragment;

import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserInfoActivity extends AppCompatActivity {

    private static final int NONE = 0;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private Bundle bundle;
    private SimpleDraweeView imageTuxiang;
    private TextView textName;
    private TextView textEmail;
    private TextView textPhone;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText editText;
    private View popuView;
    private PopupWindow popupWindow;
    private MineFragment mineFragment;
    private Bitmap bitmap;
    private String username;
    private String usericon;
    private String useremail;
    private String userphone;
    private String iconname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
        initPopuwindow();
    }

    //设置图像的点击弹出popuwindow
    private void initPopuwindow() {
        popuView = LayoutInflater.from(this).inflate(R.layout.userinfo_popuwindowview, null);
        popupWindow = new PopupWindow(popuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void showPopuWindow(View view) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    }

    private void init() {
        mineFragment = new MineFragment();
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
        //找到页面上设置相关信息的控件
        imageTuxiang = (SimpleDraweeView) findViewById(R.id.imagetuxiang_userinfo);
        textName = (TextView) findViewById(R.id.text_name_userinfoactivity);
        textEmail = (TextView) findViewById(R.id.text_email_userinfoactivity);
        textPhone = (TextView) findViewById(R.id.text_phone_userinfoactivity);

        //设置相关数据
        bundle = getIntent().getExtras();
        username = bundle.getString("username");

        if (username != null) {
            textName.setText(username + "");
        }

        //设置图片
        iconname = preferences.getString("iconname",null);
        usericon = bundle.getString("icon");
        Bitmap bitmap = BitmapFactory.decodeFile(this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname);
        if (bitmap != null) {
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse("file://" + this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname))
                    .build();
            imageTuxiang.setController(controller);
        } else if (usericon != null) {
            loadimage(usericon);
        } else {
            imageTuxiang.setImageResource(R.mipmap.img_touxiang_default);
        }

        useremail = bundle.getString("email");

        if (useremail != null) {
            textEmail.setText(useremail);
        }

        userphone = bundle.getString("phone");

        if (userphone != null) {
            textPhone.setText(userphone);
        }
    }

    private void loadimage(String icon) {
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(icon))
                .setAutoPlayAnimations(true)
                .build();
        imageTuxiang.setController(draweeController);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (view.getId()) {
            case R.id.imageback_userinfoactivity:

                finish();
                break;
            case R.id.rl_name_userinfoactivity:
                //设置昵称
                builder.setTitle("请输入昵称");
                editText = new EditText(this);
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString();
                        try {
                            KeyValue keyValue = new KeyValue("username", name);
                            x.getDb(MyApplication.getMyApplication().getDaoConfig()).update(User.class, WhereBuilder.b().and("username", "=", username), keyValue);
                            username = name;
                            textName.setText(name);
                            editor.putString("username", name).commit();
                        } catch (DbException e) {
                            e.printStackTrace();
                            Toast.makeText(UserInfoActivity.this, "修改用户名失败", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.rl_tuoxiang_userinfo:
                //点击图像弹出popuwindow设置框
                showPopuWindow(popuView);
                break;
            case R.id.rl_email_userinfoactivity:
                //设置邮箱
                builder.setTitle("请输入邮箱");
                editText = new EditText(this);
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = editText.getText().toString();


                        try {
                            KeyValue keyValue = new KeyValue("email", email);
                            x.getDb(MyApplication.getMyApplication().getDaoConfig()).update(User.class, WhereBuilder.b().and("email", "=", useremail), keyValue);
                            useremail = email;
                            textName.setText(email);
                            editor.putString("email", email).commit();
                        } catch (DbException e) {
                            e.printStackTrace();
                            Toast.makeText(UserInfoActivity.this, "修改邮箱失败", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.rl_phone_userinfoactivity:
                //设置电话
                builder.setTitle("请输入电话");
                editText = new EditText(this);
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = editText.getText().toString();

                        KeyValue keyValue = new KeyValue("phone", phone);
                        try {
                            x.getDb(MyApplication.getMyApplication().getDaoConfig()).update(User.class, WhereBuilder.b().and("phone", "=", userphone), keyValue);
                            userphone = phone;
                            textPhone.setText(phone);
                            editor.putString("phone", phone).commit();
                            Toast.makeText(UserInfoActivity.this, "修改电话失败", Toast.LENGTH_SHORT).show();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.btn_exitlogin_userinfoactivity:
                //退出登录
                editor.putString("username", null);
                editor.putString("email", null);
                editor.putString("phone", null);
                editor.putInt("loginState", 0);
                editor.commit();
                finish();
                break;

            //打开照相机
            case R.id.takephoto_popuwindow_userinfoactivity:
                //打开拍照的意图
                Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //先判断手机里面是否有相机
                if (takePhoto.resolveActivity(getPackageManager()) != null) {
                    //开启
                    takePhoto.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), "temp.jpg")));
                    startActivityForResult(takePhoto, PHOTO_GRAPH);
                }
                break;
            //从本地相册获取
            case R.id.getpic_popuwindow_userinfoactivity:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
                break;
            //取消
            case R.id.quxiao_popuwindow_userinfoactivity:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            File picture = new File(Environment.getExternalStorageDirectory()
                    + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null)
            return;

        // 读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTO_RESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                //用户修改图片我们使用时间戳的形式将图片存入内存中，并将其名字写到sharedpreference中
                long time = System.currentTimeMillis();
                this.iconname = "image"+time+".jpg";
                editor.putString("iconname",iconname).commit();
                //将图像存到本地sd卡中
                File fImage = new File(this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname);
                FileOutputStream iStream = null;
                try {
                    fImage.createNewFile();
                    iStream = new FileOutputStream(fImage);
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, iStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (iStream != null) {
                        try {
                            iStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                //从SD卡中获取图片，并显示到应用上
                Bitmap bitmap = BitmapFactory.decodeFile(this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname);
                if (bitmap != null) {
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse("file://" + this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + File.separator + iconname))
                            .build();
                    imageTuxiang.setController(controller);
                } else {
                    Toast.makeText(this, "图片没有存到内存中", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");//调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");//进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 30);
        intent.putExtra("outputY", 30);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
    }

}
