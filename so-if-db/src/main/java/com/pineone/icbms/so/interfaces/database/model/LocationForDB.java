package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by melvin on 2017. 3. 24..
 */

@Entity
@Table(name = "location")
public class LocationForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;

    @Column(name = "uri")
    private String uri;

//    @Column(name= "description")
//    String description;

//    @Temporal(TemporalType.DATE)
//    Date create_date;

//    @Temporal(TemporalType.DATE)
//    Date modified_date;

    public LocationForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public LocationForDB(String id) {
        this();
        this.id = id;
    }

    public LocationForDB(String id, String name) {
        this(id);
        this.name = name;
    }


    public LocationForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }


    public LocationForDB(String id, String name, String description, String uri) {
        this(id, name, description);
        this.uri = uri;
    }

    public LocationForDB(String id, String name, String description, String uri, Date modified_date) {
        this(null, name, description, uri);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LocationForDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", description='" + description + '\'' +
                ", created_date='" + created_date + '\'' +
                ", modified_date='" + modified_date + '\'' +
                '}';
    }
}
