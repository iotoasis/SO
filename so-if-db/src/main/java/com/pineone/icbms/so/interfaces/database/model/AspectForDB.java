package com.pineone.icbms.so.interfaces.database.model;

import com.pineone.icbms.so.util.time.DateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

/**
 * aspect model for authoring.<BR/>
 * Created by uni4love on 2017. 1. 16..
 */
//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name = "findRecentAspect",
//                query = "SELECT * FROM aspect ORDER BY created_date DESC LIMIT 1",
//                resultClass = AspectForDB.class
//        )
//})
@Entity
@Table(name = "aspect")
public class AspectForDB extends CommonEntity {

    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "name")
//    private String name;

    @Column(name = "uri")
    String uri;

    public AspectForDB() {
        Date date = Calendar.getInstance().getTime();
        String dateString = DateFormat.dateFormat(date);
        this.created_date = dateString;
        this.modified_date = dateString;
    }

    public AspectForDB(String id) {
        this();
        this.id = id;
    }

    public AspectForDB(String id, String name) {
        this(id);
        this.name = name;
    }

    public AspectForDB(String id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    public AspectForDB(String id, String name, String description, String uri) {
        this(id, name, description);
        this.uri = uri;
    }

    public AspectForDB(String id, String name, String description,  String uri, Date modified_date) {
        this(id, name, description, uri);
        this.modified_date = DateFormat.dateFormat(modified_date);
    }

//    @Column(name= "description")
//    String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "AspectForDB{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", created_date='" + created_date + '\'' +
                ", uri='" + uri + '\'' +
                ", modified_date='" + modified_date + '\'' +
                '}';
    }
}
