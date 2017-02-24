package tangcco.liuchao.com.toolbardrawerlayout.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tangcco.liuchao.com.toolbardrawerlayout.R;
import tangcco.liuchao.com.toolbardrawerlayout.entity.InfoCard;
import tangcco.liuchao.com.toolbardrawerlayout.fragment.HomeFragment;

/**
 * Created by sanji on 2016/12/10.
 */

public class DBHelper {


    private Context context;
    private String path = "data/data/tangcco.liuchao.com.toolbardrawerlayout/zhihu7.db";
    private SQLiteDatabase db;
    private int pageSize = 4;//每页显示数量

    public DBHelper(Context context) {
        this.context = context;
        db = addSqLiteDataBase();
    }


    //查所有
    public List<InfoCard> showPage(int page) {
        List<InfoCard> list = new ArrayList<>();
        InfoCard infoCard;
        int index = (page - 1) * pageSize;
        String sql = "select * from zhihu limit ? , ? ";//从哪里开始，每页显示数量
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(index), String.valueOf(pageSize)});
        while (cursor.moveToNext()) {
            infoCard = new InfoCard();
            infoCard.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            infoCard.setCator(cursor.getString(cursor.getColumnIndex("cator")));
            infoCard.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            infoCard.setTitleContent(cursor.getString(cursor.getColumnIndex("titleContent")));
            infoCard.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            //infoCard.setContentAll(cursor.getString(cursor.getColumnIndex("contentAll")));
            //infoCard.setShoucang(cursor.getInt(cursor.getColumnIndex("shoucang")));
            infoCard.setPicture(R.drawable.img_user_head);
            list.add(infoCard);
        }
        return list;
    }

    //查所有收藏的内容
    public List<InfoCard> showPageByShoucang(int page) {
        List<InfoCard> list = new ArrayList<>();
        InfoCard infoCard;
        int index = (page - 1) * pageSize;
        String sql = "select * from zhihu where shoucang = 1 limit ? , ? ";//从哪里开始，每页显示数量
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(index), String.valueOf(pageSize)});
        while (cursor.moveToNext()) {
            infoCard = new InfoCard();
            infoCard.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            infoCard.setCator(cursor.getString(cursor.getColumnIndex("cator")));
            infoCard.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            infoCard.setTitleContent(cursor.getString(cursor.getColumnIndex("titleContent")));
            infoCard.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            //infoCard.setContentAll(cursor.getString(cursor.getColumnIndex("contentAll")));
            //infoCard.setShoucang(cursor.getInt(cursor.getColumnIndex("shoucang")));
            infoCard.setPicture(R.drawable.img_user_head);
            list.add(infoCard);
        }
        return list;
    }

    //根据ID查询正文内容
    public InfoCard showPageContent(int _id) {
        InfoCard infoCard = new InfoCard();
        String sql = "select * from zhihu where _id = ?";//从哪里开始，每页显示数量
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(_id)});
        while (cursor.moveToNext()) {
            infoCard = new InfoCard();
            infoCard.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            //infoCard.setCator(cursor.getString(cursor.getColumnIndex("cator")));
            infoCard.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //infoCard.setTitleContent(cursor.getString(cursor.getColumnIndex("titleContent")));
            infoCard.setCount(cursor.getInt(cursor.getColumnIndex("count")));
            infoCard.setContentAll(cursor.getString(cursor.getColumnIndex("contentAll")));
            infoCard.setShoucang(cursor.getInt(cursor.getColumnIndex("shoucang")));
            infoCard.setPicture(R.drawable.img_user_head);
        }
        return infoCard;
    }

    //收藏
    public boolean updateShoucang(int _id, int shoucang) {
        ContentValues values = new ContentValues();
        values.put("shoucang", shoucang);

        int zhihu = db.update("zhihu", values, "_id=?", new String[]{String.valueOf(_id)});
        if (zhihu >= 1) {
            return true;
        }
        return false;
    }

    //点赞
    public boolean updateCount(int _id, int count) {
        ContentValues values = new ContentValues();
        values.put("count", count);

        int zhihu = db.update("zhihu", values, "_id=?", new String[]{String.valueOf(_id)});
        if (zhihu >= 1) {
            return true;
        }
        return false;
    }

    public SQLiteDatabase addSqLiteDataBase() {
        File file = new File(path);
        if (file.exists()) {
            return SQLiteDatabase.openOrCreateDatabase(file, null);
        } else {
            try {
                InputStream inputStream = context.getAssets().open("zhihu.db");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = new byte[1024 * 3];
                while (inputStream.read(bytes) != -1) {
                    fileOutputStream.write(bytes, 0, bytes.length);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return addSqLiteDataBase();
    }

}
