package com.example.barsa.core.entities;

import com.example.barsa.db.entities.User;

import java.sql.Timestamp;

public interface ITicket {
    Long getId();
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    User getAuthor();
    void setAuthor(User author);
    User getAssignee();
    void setAssignee(User assignee);
    
    User getCreatedBy();
    void setCreatedBy(User user);
    Timestamp getCreatedWhen();
    void setCreatedWhen(Timestamp date);
    User getModifiedBy();
    void setModifiedBy(User user);
    Timestamp getModifiedWhen();
    void setModifiedWhen(Timestamp date);
    User getResolvedBy();
    void setResolvedBy(User user);
    Timestamp getResolvedWhen();
    void setResolvedWhen(Timestamp date);
    User getClosedBy();
    void setClosedBy(User user);
    Timestamp getClosedWhen();
    void setClosedWhen(Timestamp date);

}
