package lt.vtmc.kindergarten.dto;

import java.util.Date;

public class HealthFileResponse {
    private String name;
    private String url;
    private String type;
    private long size;
    private String childFullName;
    private Date date;

    public HealthFileResponse(String name, String url, String type, long size, String childFullName, Date date) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.childFullName = childFullName;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getChildFullName() {
        return childFullName;
    }

    public void setChildFullName(String childFullName) {
        this.childFullName = childFullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
