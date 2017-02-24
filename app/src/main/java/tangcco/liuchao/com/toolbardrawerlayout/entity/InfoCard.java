package tangcco.liuchao.com.toolbardrawerlayout.entity;

/**
 * Created by sanji on 2016/12/10.
 */

public class InfoCard {
    private int _id;
    private String cator;
    private String title;
    private String titleContent;
    private String contentAll;
    private int picture;
    private int count;
    private int shoucang;

    @Override
    public String toString() {
        return "InfoCard{" +
                "_id=" + _id +
                ", cator='" + cator + '\'' +
                ", title='" + title + '\'' +
                ", titleContent='" + titleContent + '\'' +
                ", contentAll='" + contentAll + '\'' +
                ", picture=" + picture +
                ", count=" + count +
                ", shoucang=" + shoucang +
                '}';
    }



    public InfoCard(int _id, String cator, String title, String titleContent, String contentAll, int picture, int count, int shoucang) {
        this._id = _id;
        this.cator = cator;
        this.title = title;
        this.titleContent = titleContent;
        this.contentAll = contentAll;
        this.picture = picture;
        this.count = count;
        this.shoucang = shoucang;
    }

    public InfoCard() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCator() {
        return cator;
    }

    public void setCator(String cator) {
        this.cator = cator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getContentAll() {
        return contentAll;
    }

    public void setContentAll(String contentAll) {
        this.contentAll = contentAll;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getShoucang() {
        return shoucang;
    }

    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
    }
}
