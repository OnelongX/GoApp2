package com.ways2u.android.goapp.meizi.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Meizi {

    public Meizi(JSONObject jsonObject){

        error = jsonObject.optBoolean("error");
        results = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("results");
        if(jsonArray!=null&&jsonArray.length()>0){
            for (int i = 0,len = jsonArray.length();i<len;i++){
                results.add(new ResultsBean(jsonArray.optJSONObject(i)));
            }
        }
    }

    /**
     * error : false
     * results : [{"_id":"5796b970421aa90d2fc94b4e","createdAt":"2016-07-26T09:14:24.76Z","desc":"今天两个妹子","publishedAt":"2016-07-26T10:30:11.357Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg","used":true,"who":"代码家"},{"_id":"5794df0e421aa90d39e70939","createdAt":"2016-07-24T23:30:22.399Z","desc":"7.25","publishedAt":"2016-07-25T11:43:57.769Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg","used":true,"who":"代码家"},{"_id":"57918b5c421aa90d2fc94b35","createdAt":"2016-07-22T10:56:28.274Z","desc":"恐龙爪子萌妹子","publishedAt":"2016-07-22T11:04:44.305Z","source":"web","type":"福利","url":"http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg","used":true,"who":"代码家"},{"_id":"578f93c4421aa90de83c1bf4","createdAt":"2016-07-20T23:07:48.480Z","desc":"7.21","publishedAt":"2016-07-20T16:09:07.721Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f60rw11f5mj20iy0sg0u2.jpg","used":true,"who":"daimajia"},{"_id":"578e3d3b421aa90df33fe7e8","createdAt":"2016-07-19T22:46:19.966Z","desc":"7.20","publishedAt":"2016-07-20T17:25:17.522Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f5zlndsr0oj20go0ciq4h.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    /**
     * _id : 5796b970421aa90d2fc94b4e
     * createdAt : 2016-07-26T09:14:24.76Z
     * desc : 今天两个妹子
     * publishedAt : 2016-07-26T10:30:11.357Z
     * source : chrome
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg
     * used : true
     * who : 代码家
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {

        public ResultsBean(JSONObject jsonObject){
            _id = jsonObject.optString("_id");
            createdAt = jsonObject.optString("createdAt");
            desc = jsonObject.optString("desc");
            publishedAt = jsonObject.optString("publishedAt");
            source = jsonObject.optString("source");
            type = jsonObject.optString("type");
            url = jsonObject.optString("url");
            used = jsonObject.optBoolean("used");
            who = jsonObject.optString("who");
        }

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
