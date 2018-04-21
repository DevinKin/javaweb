package cn.devinkin.onlinehd.domain;

public class MyFile {
    private String fid;
    private String filepath;
    private String framename;
    private String uploadtime;
    private Integer cnt;
    private Long filesize;

    public MyFile() {
    }


    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFramename() {
        return framename;
    }

    public void setFramename(String framename) {
        this.framename = framename;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }
}
